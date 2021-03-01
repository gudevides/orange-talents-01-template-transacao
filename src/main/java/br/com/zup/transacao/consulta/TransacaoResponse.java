package br.com.zup.transacao.consulta;

import br.com.zup.transacao.transacao.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private String id;
    private String nomeEstabelecimento;
    private BigDecimal valor;
    private LocalDateTime efetivadaEm;

    public TransacaoResponse(Transacao transacao){
        this.id = transacao.getIdTransacao();
        this.nomeEstabelecimento = transacao.getEstabelecimento().getNome();
        this.valor = transacao.getValor();
        this.efetivadaEm = transacao.getEfetivadaEm();
    }

    public String getId() {
        return id;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
