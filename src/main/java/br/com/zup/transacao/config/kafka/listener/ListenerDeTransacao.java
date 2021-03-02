package br.com.zup.transacao.config.kafka.listener;

import br.com.zup.transacao.transacao.Cartao;
import br.com.zup.transacao.transacao.CartaoRepository;
import br.com.zup.transacao.transacao.Transacao;
import br.com.zup.transacao.transacao.TransacaoRepository;
import org.springframework.data.domain.Example;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ListenerDeTransacao {

    private final TransacaoRepository transacaoRepository;
    private final CartaoRepository cartaoRepository;

    public ListenerDeTransacao(TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void ouvir(EventoDeTransacao eventoDeTransacao){

        Optional<Cartao> cartao = cartaoRepository
                .findByNumeroAndEmail(eventoDeTransacao.getCartao().getId(), eventoDeTransacao.getCartao().getEmail());

        Transacao transacao;
        if (cartao.isEmpty()){
            transacao = eventoDeTransacao.toModel();
            transacaoRepository.save(transacao);
        } else {
            transacao = eventoDeTransacao.toModelWithCard(cartao.get());
            transacaoRepository.save(transacao);
        }

        System.out.println("Transação " + transacao.getIdTransacao() + " salva na base com sucesso!");
    }
}
