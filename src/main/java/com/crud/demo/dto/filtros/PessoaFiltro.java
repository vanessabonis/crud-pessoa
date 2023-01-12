package com.crud.demo.dto.filtros;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class PessoaFiltro {

    public String nome;
    public LocalDate dataNascimento;
}
