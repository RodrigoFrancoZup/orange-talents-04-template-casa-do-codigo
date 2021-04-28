package br.com.zupacademy.rodrigo.livro;

import br.com.zupacademy.rodrigo.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
