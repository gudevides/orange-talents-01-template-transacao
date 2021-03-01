package br.com.zup.transacao.config.kafka.listener;

import br.com.zup.transacao.transacao.Transacao;
import br.com.zup.transacao.transacao.TransacaoRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerDeTransacao {

    private final TransacaoRepository transacaoRepository;

    public ListenerDeTransacao(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(EventoDeTransacao eventoDeTransacao){

        Transacao transacao = eventoDeTransacao.toModel();
        transacaoRepository.save(transacao);

        System.out.println("Transação " + transacao.getIdTransacao() + " salva na base com sucesso!");
    }
}
