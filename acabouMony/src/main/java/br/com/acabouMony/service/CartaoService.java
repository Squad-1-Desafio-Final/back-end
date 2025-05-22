package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroCartaoDTO;
import br.com.acabouMony.dto.CadastroEnderecoDTO;
import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.dto.ListagemEnderecoDTO;
import br.com.acabouMony.entity.*;
import br.com.acabouMony.exception.CartaoNaoEncontrado;
import br.com.acabouMony.exception.IdNaoEncontradoException;
import br.com.acabouMony.exception.PedidoNaoEncontrado;
import br.com.acabouMony.exception.UsuarioNaoEncontradoException;
import br.com.acabouMony.mapper.CartaoListarMapper;
import br.com.acabouMony.mapper.CartaoMapperStruct;
import br.com.acabouMony.repository.CartaoRepository;
import br.com.acabouMony.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CartaoService {

    @Autowired
    CartaoRepository repository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    CartaoMapperStruct cartaoMapperStruct;

    @Autowired
    CartaoListarMapper cartaoListarMapper;

    private static final Set<String> numerosGerados = new HashSet<>();
    private static final Random random = new Random();
    private static final DateTimeFormatter FORMATADOR_MM_YY = DateTimeFormatter.ofPattern("MM/yy");

    @Transactional
    public CadastroCartaoDTO saveCartao(CadastroCartaoDTO cartaoDTO) {

        var cartao = new Cartao(cartaoDTO);
        repository.save(cartao);
        return cartaoMapperStruct.toCartaoDto(cartao);

    }

    public static String gerarDataValidadeFormatada() {
        long mesesAdicionais = ThreadLocalRandom.current().nextLong(36, 61);
        YearMonth validade = YearMonth.now().plusMonths(mesesAdicionais);
        return validade.format(FORMATADOR_MM_YY);
    }

    public static String gerarNumeroCartao() {
        String numero;
        do {
            numero = String.format("%04d%04d%04d%04d",
                    random.nextInt(10000),
                    random.nextInt(10000),
                    random.nextInt(10000),
                    random.nextInt(10000));
        } while (numerosGerados.contains(numero));
        numerosGerados.add(numero);
        return numero;
    }

    public static int gerarCVV() {
        return 100 + random.nextInt(900);
    }




    public CadastroCartaoDTO criar(CadastroCartaoDTO dto){
        Cartao cartao = cartaoMapperStruct.toEntity(dto);

        Conta conta = contaRepository.findByNumero(dto.numeroConta())
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrado"));


        String numeroCartao = gerarNumeroCartao();
        int cvv = gerarCVV();
        String validade = gerarDataValidadeFormatada();


        cartao.setConta(conta);
        cartao.setTipo(dto.tipo());
        cartao.setSenha(dto.senha());
        cartao.setAtivo(true);
        cartao.setNumero(numeroCartao);
        cartao.setCvv(cvv);
        cartao.setValidade(validade);
        repository.save(cartao);
        System.out.println(cartao.getConta());
        return cartaoMapperStruct.toCartaoDto(cartao);

    }



    public List<ListagemCartaoDTO> listar(){
        var lista = repository.listarNumETipo();

        return lista;
    }


    public ListagemCartaoDTO listarPorId(UUID id){
        Cartao cartao = repository.findById(id) .orElseThrow(() -> new IdNaoEncontradoException("Id não encontrado"));


        return cartaoListarMapper.toCartaoDto(cartao);



    }


    public Cartao editar(UUID id) {
        Cartao cartaoEncontrado = repository.findById(id) .orElseThrow(() -> new CartaoNaoEncontrado("Cartão não encontrado"));

        cartaoEncontrado.setAtivo(false);

        return repository.save(cartaoEncontrado);
    }

}
