package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.dto.ListagemContaDTO;
import br.com.acabouMony.entity.Conta;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T11:40:07-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class ContaMapperImpl implements ContaMapper {

    @Override
    public Conta toEntity(CadastroContaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Conta conta = new Conta();

        return conta;
    }

    @Override
    public ListagemContaDTO toListagemContaDTO(Conta conta) {
        if ( conta == null ) {
            return null;
        }

        Date dataVencimento = null;
        double limite = 0.0d;
        int agencia = 0;
        int numero = 0;

        ListagemContaDTO listagemContaDTO = new ListagemContaDTO( dataVencimento, limite, agencia, numero );

        return listagemContaDTO;
    }
}
