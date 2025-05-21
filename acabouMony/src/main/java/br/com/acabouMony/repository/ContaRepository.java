package br.com.acabouMony.repository;

import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Conta;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Optional<Conta> findByNumero(int numeroConta);

    @Modifying
    @Transactional
    @Query("UPDATE Conta c SET c.ativo = false WHERE c.id = :id")
    void delecaoLogica(UUID id);

    boolean existsByNumero(@NotNull int numero);

}
