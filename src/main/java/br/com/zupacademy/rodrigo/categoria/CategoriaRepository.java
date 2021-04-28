package br.com.zupacademy.rodrigo.categoria;

import br.com.zupacademy.rodrigo.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    public Optional<Categoria> findByNome(String nome);
}
