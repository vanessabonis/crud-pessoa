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

    public PessoaDTO obterPorId(int id) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new HttpStatusException("Pessoa com id " + id + " não encontrada", HttpStatus.NOT_FOUND));
        return mapper.toDto(pessoa);
    }

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

    public void adicionarEndereco(int id, EnderecoDTO enderecoDTO) {
        PessoaDTO pessoaDTO = obterPorId(id);
        enderecoDTO.setStatusEndereco(StatusEndereco.PRINCIPAL);
        pessoaDTO.getEnderecos().forEach(e -> e.setStatusEndereco(StatusEndereco.SECUNDARIO));

        pessoaDTO.getEnderecos().add(enderecoDTO);
        atualizar(pessoaDTO);
    }

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
