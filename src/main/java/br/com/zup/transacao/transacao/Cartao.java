package br.com.zup.transacao.transacao;

import br.com.zup.transacao.config.kafka.listener.EventoCartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String numero;

    @NotNull
    private String email;

    @OneToMany(mappedBy = "cartao")
    private Set<Transacao> transacao = new HashSet<>();

    @Deprecated
    public Cartao(){}

    public Cartao(EventoCartao cartao) {
        this.numero = cartao.getId();
        this.email = cartao.getEmail();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return Objects.equals(numero, cartao.numero) && Objects.equals(email, cartao.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, email);
    }
}

