package Debug_._Grow.br.com.anotacao.controller;

import Debug_._Grow.br.com.anotacao.service.IaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Ianotacao")
public class IaController {

    private final IaService iaService;

    public IaController(IaService iaService) {
        this.iaService = iaService;
    }

    @GetMapping("/{buscar}")
    public ResponseEntity<String> pergunta(@PathVariable String buscar) {
        return ResponseEntity.ok(iaService.pergunta(buscar));
    }
}
