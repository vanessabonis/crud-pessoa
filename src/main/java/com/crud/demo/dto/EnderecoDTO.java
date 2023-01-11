package com.crud.demo.dto;

import com.crud.demo.enums.StatusEndereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private int id;

    @NotBlank(message = "Campo 'Logradouro' é obrigatório")
    private String logradouro;

    @Size(min = 8, max = 9, message = "O CEP é inválido.")
    @NotBlank(message = "Campo 'CEP' é obrigatório")
    private String cep;

    @NotBlank(message = "Campo 'Número' é obrigatório")
    private String numero;

    @NotBlank(message = "Campo 'Cidade' é obrigatório")
    private String cidade;

    private StatusEndereco statusEndereco;
}
