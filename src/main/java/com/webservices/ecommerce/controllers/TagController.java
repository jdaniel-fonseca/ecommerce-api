package com.webservices.ecommerce.controllers;

import com.webservices.ecommerce.dto.request.TagRequestDTO;
import com.webservices.ecommerce.dto.response.TagResponseDTO;
import com.webservices.ecommerce.services.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {


    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDTO>> findAll() {
        List<TagResponseDTO> tags = tagService.findAll();
        return ResponseEntity.ok().body(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDTO> findById(@PathVariable Long id) {
        TagResponseDTO tag = tagService.findById(id);
        return ResponseEntity.ok().body(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDTO> update(@PathVariable Long id, @RequestBody TagRequestDTO tagRequestDTO) {
        TagResponseDTO tagResponseDTO = tagService.update(tagRequestDTO, id);
        return ResponseEntity.ok().body(tagResponseDTO);
    }

    @PostMapping
    public ResponseEntity<TagResponseDTO> save(@RequestBody TagRequestDTO tagRequestDTO) {
        TagResponseDTO tagResponseDTO = tagService.insert(tagRequestDTO);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tagResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(tagResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
