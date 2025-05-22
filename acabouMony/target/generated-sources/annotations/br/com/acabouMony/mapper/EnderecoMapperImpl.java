package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroEnderecoDTO;
import br.com.acabouMony.dto.ListagemEnderecoDTO;
import br.com.acabouMony.entity.Endereco;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T13:40:29-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public Endereco toEntity(CadastroEnderecoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        return endereco;
    }

    @Override
    public ListagemEnderecoDTO toListagemEnderecoDTO(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        String logradouro = null;
        int numero = 0;
        String complemento = null;
        String bairro = null;
        String cidade = null;
        String estado = null;
        String cep = null;

        ListagemEnderecoDTO listagemEnderecoDTO = new ListagemEnderecoDTO( logradouro, numero, complemento, bairro, cidade, estado, cep );

        return listagemEnderecoDTO;
    }
}
