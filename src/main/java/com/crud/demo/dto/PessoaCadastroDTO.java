package com.crud.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaCadastroDTO {

    private int id;

    @NotBlank(message = "Campo 'Nome' é obrigatório")
    private String nome;

    @NotNull(message = "Campo 'Data de Nascimento' é obrigatório")
    private LocalDate dataNascimento;

    @Valid
    private EnderecoDTO endereco;
}
