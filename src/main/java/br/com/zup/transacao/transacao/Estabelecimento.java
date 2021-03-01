package br.com.zup.transacao.transacao;

import br.com.zup.transacao.config.kafka.listener.EventoEstabelecimento;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cidade;

    @NotNull
    private String endereco;

    @OneToMany(mappedBy = "estabelecimento")
    private Set<Transacao> transacao = new HashSet<>();

    public String getNome() {
        return nome;
    }

    @Deprecated
    public Estabelecimento(){}

    public Estabelecimento(EventoEstabelecimento estabelecimento) {
        this.nome = estabelecimento.getNome();
        this.cidade = estabelecimento.getCidade();
        this.endereco = estabelecimento.getEndereco();
    }


}
