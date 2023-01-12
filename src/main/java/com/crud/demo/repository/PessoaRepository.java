package com.crud.demo.repository;

import com.crud.demo.dto.PessoaDTO;
import com.crud.demo.dto.filtros.PessoaFiltro;
import com.crud.demo.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query("SELECT p FROM Pessoa p WHERE " +
        "(:#{#filtro.nome} IS NULL OR p.nome LIKE :#{#filtro.nome}%) AND " +
        "(:#{#filtro.dataNascimento} IS NULL OR p.dataNascimento = :#{#filtro.dataNascimento}) ")
    Page<Pessoa> listarPorFiltro(@Param("filtro") PessoaFiltro filtro, Pageable pageable);
}
