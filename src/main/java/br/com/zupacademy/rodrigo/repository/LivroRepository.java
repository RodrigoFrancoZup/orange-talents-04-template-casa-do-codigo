package br.com.zupacademy.rodrigo.repository;

import br.com.zupacademy.rodrigo.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
