package br.com.zup.transacao.transacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByNumeroAndEmail(String id, String email);
}
