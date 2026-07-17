package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.TagRequestDTO;
import com.webservices.ecommerce.dto.response.TagResponseDTO;
import com.webservices.ecommerce.entities.Tag;
import com.webservices.ecommerce.exceptions.DatabaseException;
import com.webservices.ecommerce.exceptions.ResourceNotFoundException;
import com.webservices.ecommerce.repositories.TagRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional(readOnly = true)
        public Page<TagResponseDTO> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable)
                .map(TagResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public TagResponseDTO findById(Long id) {
        Tag tag =  tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return new TagResponseDTO(tag);
    }

    @Transactional
    public TagResponseDTO create(TagRequestDTO tagRequestDTO) {
        Tag tag = tagRepository.save(converter(tagRequestDTO));
        return new TagResponseDTO(tag);
    }

    @Transactional
    public TagResponseDTO update(TagRequestDTO tagRequestDTO, Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(tagRequestDTO, tag);
        return  new TagResponseDTO(tag);
    }

    @Transactional
    public void delete(Long id) {
        try {
            tagRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private Tag converter(TagRequestDTO tagRequestDTO) {
        Tag tag = new Tag();
        tag.setNome(tagRequestDTO.getNome());
        return tag;
    }

    private void updateData(TagRequestDTO tagRequestDTO, Tag tag) {
        tag.setNome(tagRequestDTO.getNome());
    }
}
