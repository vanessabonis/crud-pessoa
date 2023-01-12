package com.crud.demo.service;

import com.crud.demo.dto.EnderecoDTO;

import com.crud.demo.repository.EnderecoRepository;
import com.crud.demo.service.mapper.EnderecoMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;

    /**
     * • Listar Endereços da Pessoa - etapa 6 do desafio
     * */
    public List<EnderecoDTO> listarPorIdPessoa(int idPessoa) {
        return mapper.toDto(repository.findAllByPessoaId(idPessoa));
    }

}
