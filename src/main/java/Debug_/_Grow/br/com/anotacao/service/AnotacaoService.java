package Debug_._Grow.br.com.anotacao.service;

import Debug_._Grow.br.com.anotacao.dto.AnotacaoDTO;
import Debug_._Grow.br.com.anotacao.dto.AnotacaoRequestDTO;
import Debug_._Grow.br.com.anotacao.dto.AnotacaoUpdateDTO;
import Debug_._Grow.br.com.anotacao.exception.AnotacaoNotFoundException;
import Debug_._Grow.br.com.anotacao.mapper.AnotacaoMapper;
import Debug_._Grow.br.com.anotacao.model.Anotacao;
import Debug_._Grow.br.com.anotacao.repository.AnotacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnotacaoService {

    private final AnotacaoRepository anotacaoRepository;

    private final AnotacaoMapper anotacaoMapper;

    public AnotacaoService(AnotacaoRepository anotacaoRepository, AnotacaoMapper anotacaoMapper) {
        this.anotacaoRepository = anotacaoRepository;
        this.anotacaoMapper = anotacaoMapper;
    }
    @Transactional
    public AnotacaoDTO salvar(AnotacaoRequestDTO anotacaoRequestDTO) {
        if (anotacaoRequestDTO.titulo() == null) {
            throw new IllegalArgumentException("Titulo não encontrado para salvar");
        }
        if (anotacaoRequestDTO.anotacao() == null) {
            throw new IllegalArgumentException("Anotação não encontrada para salvar");
        }
        Anotacao anotacao = anotacaoMapper.paraEntity(anotacaoRequestDTO);

        Anotacao salvo = anotacaoRepository.save(anotacao);
        return anotacaoMapper.paraDTO(salvo);
    }
    public Page<AnotacaoDTO> listar(int pagina) {
        Pageable pageable = PageRequest.of(pagina, 10);
        return anotacaoRepository.findAll(pageable)
                .map(anotacaoMapper::paraDTO);
    }
    public AnotacaoDTO findById(Long id) {
        return anotacaoRepository.findById(id)
                .map(anotacaoMapper::paraDTO)
                .orElseThrow(() -> new AnotacaoNotFoundException("Anotação não encontrado com o ID: " + id));
        }
    @Transactional
    public void deleteById(Long id) {
        if (!anotacaoRepository.existsById(id)) {
            throw new AnotacaoNotFoundException("Anotação não encontrado para deletar");
        }
        anotacaoRepository.deleteById(id);
    }
    @Transactional
    public AnotacaoDTO atualizaAnotacao(Long id, AnotacaoUpdateDTO dto) {
        Anotacao dados = anotacaoRepository.findById(id)
                .orElseThrow(() -> new AnotacaoNotFoundException("Anotação Não encontrado com o ID para atualizar: " + id));

        dados.setTitulo(dto.titulo());
        dados.setAnotacao(dto.anotacao());

        Anotacao salvo = anotacaoRepository.save(dados);
        return anotacaoMapper.paraDTO(salvo);
        }
}





