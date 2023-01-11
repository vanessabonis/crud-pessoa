package com.crud.demo.service.mapper;

import com.crud.demo.dto.EnderecoDTO;
import com.crud.demo.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper extends EntityMapper<Endereco, EnderecoDTO> {
}
