package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CartaoResumoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartaoResumoMapper {

    @Mapping(source = "numero", target = "numero")
    Cartao toEntity(CartaoResumoDto dto);
    CartaoResumoDto toTransacaoDto(Cartao entity);
}