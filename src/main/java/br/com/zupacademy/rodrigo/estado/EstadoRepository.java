package br.com.zupacademy.rodrigo.estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    @Query("SELECT e FROM Estado e WHERE e.nome = :nomeEstado AND e.pais.id = :idPais")
    Optional<Estado> buscaEstadoPorNomeEPais(@Param("nomeEstado") String nomeEstado, @Param("idPais") Long idPais);

    public List<Estado> findByPaisId(Long id);
}
