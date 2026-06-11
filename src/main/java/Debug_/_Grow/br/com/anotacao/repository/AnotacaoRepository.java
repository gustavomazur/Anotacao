package Debug_._Grow.br.com.anotacao.repository;

import Debug_._Grow.br.com.anotacao.model.Anotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

    @Query("SELECT a FROM Anotacao a WHERE a.titulo LIKE %:termo% OR a.anotacao LIKE %:termo%")
    List<Anotacao> buscarPorTermo(@Param("termo") String termo);

}

