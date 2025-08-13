package dev.cruzfabio.forumhub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    Page<Topico> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND YEAR(t.dataCriacao) = :ano")
    Page<Topico> findByCursoAndAno(String curso, Integer ano, Pageable paginacao);

}
