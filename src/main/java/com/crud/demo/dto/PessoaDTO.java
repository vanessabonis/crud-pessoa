package com.crud.demo.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private int id;
    private String nome;

    private LocalDate dataNascimento;
    private List<EnderecoDTO> enderecos;

}
