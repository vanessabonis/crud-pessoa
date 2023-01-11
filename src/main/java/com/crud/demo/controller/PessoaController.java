package com.crud.demo.controller;

import com.crud.demo.dto.PessoaCadastroDTO;
import com.crud.demo.dto.PessoaDTO;
import com.crud.demo.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaDTO> cadastrar(@Valid @RequestBody PessoaCadastroDTO dto) {
        log.info("Salvando Nova Pessoa");
        return ResponseEntity.ok(service.cadastrar(dto));
    }
}
