package Debug_._Grow.br.com.anotacao.controller;


import Debug_._Grow.br.com.anotacao.dto.AnotacaoDTO;
import Debug_._Grow.br.com.anotacao.dto.AnotacaoRequestDTO;
import Debug_._Grow.br.com.anotacao.dto.AnotacaoUpdateDTO;
import Debug_._Grow.br.com.anotacao.service.AnotacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anotacoes")
public class AnotacaoController {

    private final AnotacaoService anotacaoService;

    public AnotacaoController(AnotacaoService anotacaoService) {
        this.anotacaoService = anotacaoService ;
    }

    @PostMapping
    public ResponseEntity<AnotacaoDTO> criarAnotacao(@Valid @RequestBody AnotacaoRequestDTO anotacaoRequestDTO) {
        AnotacaoDTO resultado = anotacaoService.salvar(anotacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);

    }
    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(anotacaoService.findById(id));
    }
    @GetMapping("/listar")
    public ResponseEntity<Page<AnotacaoDTO>> listar(@RequestParam(defaultValue = "0") int pagina) {
        return ResponseEntity.ok(anotacaoService.listar(pagina));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AnotacaoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AnotacaoUpdateDTO dto) {
        AnotacaoDTO responseDTO = anotacaoService.atualizaAnotacao(id, dto);
        return ResponseEntity.ok(responseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        anotacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
