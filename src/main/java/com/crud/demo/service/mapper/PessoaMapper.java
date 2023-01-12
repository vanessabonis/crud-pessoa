package com.crud.demo.service.mapper;

import com.crud.demo.dto.PessoaDTO;
import com.crud.demo.model.Pessoa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class})
public interface PessoaMapper extends EntityMapper<Pessoa, PessoaDTO> {
}
