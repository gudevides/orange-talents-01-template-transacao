package br.com.zup.transacao.consulta;

import br.com.zup.transacao.transacao.TransacaoRepository;
import br.com.zup.transacao.transacao.Transacao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transacoes")
public class ConsultarComprasController {

    private final TransacaoRepository transacaoRepository;

    public ConsultarComprasController(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @GetMapping("/cartoes/{id}")
    public ResponseEntity<List<TransacaoResponse>> consultar(@PathVariable @NotBlank String id){

        List<Transacao> transacoes = transacaoRepository.findTop10ByCartaoNumeroOrderByEfetivadaEmDesc(id);

        if (transacoes.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<TransacaoResponse> transacoesResponse = transacoes.stream()
                .map(TransacaoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(transacoesResponse);
    }
}
