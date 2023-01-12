package com.crud.demo.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private int id;

    @NotBlank(message = "Campo 'Nome' é obrigatório")
    private String nome;

    @NotNull(message = "Campo 'Data de Nascimento' é obrigatório")
    private LocalDate dataNascimento;

    @Valid
    private List<EnderecoDTO> enderecos;

}
