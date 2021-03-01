package br.com.zup.transacao.transacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao,Long> {

    List<Transacao> findByCartaoId(Long id);

    List<Transacao> findTop10ByCartaoNumeroOrderByEfetivadaEmAsc(String id);
}
