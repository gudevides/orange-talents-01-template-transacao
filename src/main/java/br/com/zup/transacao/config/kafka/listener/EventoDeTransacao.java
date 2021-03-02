package br.com.zup.transacao.config.kafka.listener;

import br.com.zup.transacao.transacao.Cartao;
import br.com.zup.transacao.transacao.Estabelecimento;
import br.com.zup.transacao.transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class EventoDeTransacao {

    private String id;

    private BigDecimal valor;

    private EventoEstabelecimento estabelecimento;

    private EventoCartao cartao;

    private LocalDateTime efetivadaEm;

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EventoEstabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public EventoCartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao toModel() {
        Estabelecimento estabelecimento = new Estabelecimento(this.estabelecimento);
        Cartao cartao = new Cartao(this.cartao);
        return new Transacao(this.id, this.valor, estabelecimento, cartao, this.efetivadaEm);
    }

    public Transacao toModelWithCard(Cartao cartao) {
        Estabelecimento estabelecimento = new Estabelecimento(this.estabelecimento);
        return new Transacao(this.id, this.valor, estabelecimento, cartao, this.efetivadaEm);
    }
}
