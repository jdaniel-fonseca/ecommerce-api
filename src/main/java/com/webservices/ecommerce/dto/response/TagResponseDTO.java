package com.webservices.ecommerce.dto.response;

import com.webservices.ecommerce.entities.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagResponseDTO {

    private Long id;
    private String nome;

    public TagResponseDTO(Tag tag) {
        this.id = tag.getId();
        this.nome = tag.getNome();
    }
}
