package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroProdutoDto;
import br.com.acabouMony.dto.CadastroTransacaoDto;
import br.com.acabouMony.entity.Produto;
import br.com.acabouMony.entity.Transacao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransacaoCadastroMapper {
    Transacao toEntity(CadastroTransacaoDto dto);
    CadastroTransacaoDto toTransacaoDto(Transacao entity);
    CadastroTransacaoDto toDadosTransacaoDto(Transacao entity);

}
