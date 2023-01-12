package com.crud.demo.repository;

import com.crud.demo.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Query("SELECT p.enderecos " +
        "FROM Pessoa p " +
        "WHERE p.id = ?1")
    List<Endereco> findAllByPessoaId(int idPessoa);
}
