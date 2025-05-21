package br.com.acabouMony.repository;

import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Optional<Conta> findByNumero(int numeroConta);


}
