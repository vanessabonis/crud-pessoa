package com.crud.demo.service.mapper;

import com.crud.demo.dto.PessoaDTO;
import com.crud.demo.model.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper extends EntityMapper<Pessoa, PessoaDTO> {
}
