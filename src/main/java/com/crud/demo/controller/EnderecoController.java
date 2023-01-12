package com.crud.demo.controller;

import com.crud.demo.dto.EnderecoDTO;
import com.crud.demo.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnderecoDTO>> listarPorPessoaId(@RequestParam Integer idPessoa) {
        log.info("listando endereços de pessoa com id {}", idPessoa);
        return ResponseEntity.ok(service.listarPorIdPessoa(idPessoa));
    }
}
