package Debug_._Grow.br.com.anotacao.service;

import Debug_._Grow.br.com.anotacao.model.Anotacao;
import Debug_._Grow.br.com.anotacao.repository.AnotacaoRepository;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IaService {

    private final AnotacaoRepository anotacaoRepository;
    private final ChatLanguageModel chatLanguageModel;

    public IaService(AnotacaoRepository anotacaoRepository, ChatLanguageModel chatLanguageModel) {
        this.anotacaoRepository = anotacaoRepository;
        this.chatLanguageModel = chatLanguageModel;
    }

    public String pergunta(String pergunta) {
        List<Anotacao> anotacoes = anotacaoRepository.buscarPorTermo(pergunta);

        if (anotacoes.isEmpty()) {
            return "Você não tem nada anotado sobre isso ainda!";
        }

        String contexto = anotacoes.stream()
                .map(a -> "Título: " + a.getTitulo() + "\nAnotação: " + a.getAnotacao())
                .collect(Collectors.joining("\n\n"));

        String prompt = """
                Com base nessas anotações:
                %s
                
                Responda de forma organizada: %s
                """.formatted(contexto, pergunta);

        return chatLanguageModel.generate(prompt);
    }
}