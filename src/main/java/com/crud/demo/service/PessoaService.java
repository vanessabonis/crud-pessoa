package com.crud.demo.service;

import com.crud.demo.dto.EnderecoDTO;
import com.crud.demo.dto.PessoaCadastroDTO;
import com.crud.demo.dto.PessoaDTO;
import com.crud.demo.enums.StatusEndereco;
import com.crud.demo.exceptions.HttpStatusException;
import com.crud.demo.model.Pessoa;
import com.crud.demo.repository.PessoaRepository;
import com.crud.demo.service.mapper.PessoaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;
    private final PessoaMapper mapper;

    /**
     * •	Consultar uma pessoa - etapa 3 do desafio
     * */
    public PessoaDTO obterPorId(int id) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new HttpStatusException("Pessoa com id " + id + " não encontrada", HttpStatus.NOT_FOUND));
        return mapper.toDto(pessoa);
    }

    /**
     * •	Criar uma Pessoa - etapa  do desafio
     * */
    public PessoaDTO cadastrar(PessoaCadastroDTO cadastroDTO) {
        //a pessoa precisa se cadastrar com um endereço
        cadastroDTO.getEndereco().setStatusEndereco(StatusEndereco.PRINCIPAL);
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();
        enderecoDTOS.add(cadastroDTO.getEndereco());
        PessoaDTO pessoaDTO = PessoaDTO.builder()
            .nome(cadastroDTO.getNome())
            .dataNascimento(cadastroDTO.getDataNascimento())
            .enderecos(enderecoDTOS)
            .build();
        salvar(mapper.toEntity(pessoaDTO));
        return pessoaDTO;
    }

    /**
     * •	Listar - etapa 4 do desafio
     * */
    public Page<PessoaDTO> listar(Pageable pageable) {
        Page<Pessoa> page = repository.findAll(pageable);
        List<PessoaDTO> listaDTO = mapper.toDto(repository.findAll());
        return new PageImpl<>(listaDTO, pageable, page.getTotalElements());
    }

    /**
     * •	Criar  endereço para uma Pessoa - etapa 5 do desafio
     * */
    public PessoaDTO adicionarEndereco(int id, EnderecoDTO enderecoDTO) {
        PessoaDTO pessoaDTO = obterPorId(id);
        enderecoDTO.setStatusEndereco(StatusEndereco.PRINCIPAL);
        pessoaDTO.getEnderecos().forEach(e -> e.setStatusEndereco(StatusEndereco.SECUNDARIO));

        pessoaDTO.getEnderecos().add(enderecoDTO);
        return atualizar(pessoaDTO);
    }

    /**
     * •	Informar endereço principal - etapa 7 do desafio
     * */
    public void mudarStatusEndereco(int idPessoa, int idEndereco) {
        var pessoaDTO = obterPorId(idPessoa);
        pessoaDTO.getEnderecos().forEach(e -> {
            e.setStatusEndereco(StatusEndereco.SECUNDARIO);
            if (e.getId() == idEndereco) e.setStatusEndereco(StatusEndereco.PRINCIPAL);
        });

        salvar(mapper.toEntity(pessoaDTO));
    }

    /**
     * •	Editar Pessoa - etapa 2 do desafio
     * */
    public PessoaDTO atualizar(PessoaDTO dto) {
        obterPorId(dto.getId()); // garantir que existe
        Pessoa pessoa = salvar(mapper.toEntity(dto));
        return mapper.toDto(pessoa);
    }
    
    public void excluirPorId(int id) {
        repository.deleteById(id);
    }

    public Pessoa salvar(Pessoa p) { return repository.saveAndFlush(p); }

    public void validaApenasUmPrincipal(List<EnderecoDTO> enderecos) {
        if (enderecos.stream().filter(enderecoDTO -> enderecoDTO.getStatusEndereco().equals(StatusEndereco.PRINCIPAL))
            .collect(Collectors.toList()).size() > 0)
            throw new HttpStatusException("Você só pode ter um endereço principal", HttpStatus.CONFLICT);
    }
}
