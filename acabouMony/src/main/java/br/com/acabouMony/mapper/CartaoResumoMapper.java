package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CartaoResumoDto;
import br.com.acabouMony.dto.UsuarioResumoDto;
import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartaoResumoMapper {
    Cartao toEntity(CartaoResumoDto dto);
    CartaoResumoDto toTransacaoDto(Cartao entity);
}