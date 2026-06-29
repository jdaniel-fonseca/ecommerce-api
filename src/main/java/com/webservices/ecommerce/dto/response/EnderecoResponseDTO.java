package com.webservices.ecommerce.dto.response;
import com.webservices.ecommerce.entities.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoResponseDTO {

    private Long id;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;


    public EnderecoResponseDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.cep = endereco.getCep();
    }

}
