package com.crud.demo.controller;

import com.crud.demo.dto.EnderecoDTO;
import com.crud.demo.dto.PessoaCadastroDTO;
import com.crud.demo.dto.PessoaDTO;
import com.crud.demo.dto.filtros.PessoaFiltro;
import com.crud.demo.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaDTO> obterPorId(@PathVariable int id) {
        log.info("obtendo pessoa com id {}", id);
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PessoaDTO>> listar(@RequestParam(required = false) String nome,
                                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataNascimento,
                                                  Pageable pageable) {
        log.info("listando pessoas");
        PessoaFiltro filtro = new PessoaFiltro(nome, dataNascimento);
        return ResponseEntity.ok(service.listar(filtro, pageable));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaDTO> cadastrar(@Valid @RequestBody PessoaCadastroDTO dto) {
        log.info("Salvando Nova Pessoa");
        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @PostMapping(path = "/{idPessoa}/adiciona-endereco", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaDTO> adicionaEndereco(@Valid @RequestBody EnderecoDTO dto, @PathVariable Integer idPessoa ) {
        log.info("Adicionando novo endere??o a pessoa com id {}", idPessoa);
        return ResponseEntity.ok(service.adicionarEndereco(idPessoa, dto));
    }

    @PatchMapping(path = "/{idPessoa}/alterar-status-endereco")
    public ResponseEntity tornarPrincipal(@PathVariable Integer idPessoa, @RequestParam Integer idEndereco) {
        log.info("Alterando para PRINCIPAL endere??o com id {}", idEndereco);
        service.mudarStatusEndereco(idPessoa, idEndereco);
        return ResponseEntity.ok().body("Endere??o alterado para principal com Sucesso!");
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaDTO> editar(@Valid @RequestBody PessoaDTO dto) {
        log.info("Atualizando Pessoa com id {} ", dto.getId());
        return ResponseEntity.ok(service.atualizar(dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity excluir(@PathVariable Integer id) {
        log.info("excluindo pessoa com id {}", id);
        service.excluirPorId(id);
        return ResponseEntity.ok().body("Pessoa exclu??da com Sucesso!");
    }
}
