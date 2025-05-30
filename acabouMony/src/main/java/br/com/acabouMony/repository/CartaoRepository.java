package br.com.acabouMony.repository;

import br.com.acabouMony.dto.ListagemCartaoDTO;
import br.com.acabouMony.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, UUID> {


    // trocar no nome do database
    @Query("SELECT new br.com.acabouMony.dto.ListagemCartaoDTO(c.numero, c.tipo) FROM Cartao c")
    List<ListagemCartaoDTO> listarNumETipo();
}
