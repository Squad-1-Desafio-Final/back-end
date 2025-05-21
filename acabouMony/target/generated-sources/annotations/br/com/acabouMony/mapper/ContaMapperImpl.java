package br.com.acabouMony.mapper;

import br.com.acabouMony.dto.CadastroContaDTO;
import br.com.acabouMony.dto.ListagemContaDTO;
import br.com.acabouMony.entity.Conta;
import java.time.ZoneOffset;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-21T14:01:54-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class ContaMapperImpl implements ContaMapper {

    @Override
    public Conta toEntity(CadastroContaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Conta conta = new Conta();

        conta.setNumero( dto.numero() );
        conta.setDataVencimento( dto.dataVencimento() );
        conta.setLimite( dto.limite() );
        conta.setAgencia( dto.agencia() );
        conta.setBanco( dto.banco() );

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

        if ( conta.getDataVencimento() != null ) {
            dataVencimento = Date.from( conta.getDataVencimento().atStartOfDay( ZoneOffset.UTC ).toInstant() );
        }
        limite = conta.getLimite();
        agencia = conta.getAgencia();
        numero = conta.getNumero();

        ListagemContaDTO listagemContaDTO = new ListagemContaDTO( dataVencimento, limite, agencia, numero );

        return listagemContaDTO;
    }
}
