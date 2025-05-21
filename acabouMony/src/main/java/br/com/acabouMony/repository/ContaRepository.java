package br.com.acabouMony.repository;

import br.com.acabouMony.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {

    boolean existsByNumero(int numero);

    @Modifying
    @Transactional
    @Query("UPDATE Conta c SET c.ativo = false WHERE c.id = :id")
    void delecaoLogica(UUID id);
}
