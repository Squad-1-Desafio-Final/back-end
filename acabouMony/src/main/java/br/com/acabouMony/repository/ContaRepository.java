package br.com.acabouMony.repository;

import br.com.acabouMony.entity.Cartao;
import br.com.acabouMony.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {


}
