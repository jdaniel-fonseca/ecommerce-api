package com.webservices.ecommerce.services;

import com.webservices.ecommerce.dto.request.TagRequestDTO;
import com.webservices.ecommerce.dto.response.TagResponseDTO;
import com.webservices.ecommerce.entities.Tag;
import com.webservices.ecommerce.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagResponseDTO> findAll() {
        List<Tag> tags  = tagRepository.findAll();
        List<TagResponseDTO> tagResponseDTOs = new ArrayList<>();
        for (Tag tag : tags) {
            tagResponseDTOs.add(new TagResponseDTO(tag));
        }
        return tagResponseDTOs;
    }

    public TagResponseDTO findById(Long id) {
        Tag tag =  tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada."));
        return new TagResponseDTO(tag);
    }

    public TagResponseDTO insert(TagRequestDTO tagRequestDTO) {
        Tag tag = tagRepository.save(converter(tagRequestDTO));
        return new TagResponseDTO(tag);
    }

    public TagResponseDTO update(TagRequestDTO tagRequestDTO, Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag não encontrada"));
        updateData(tagRequestDTO, tag);
        return  new TagResponseDTO(tag);
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
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
