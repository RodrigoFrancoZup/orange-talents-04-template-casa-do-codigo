package br.com.zupacademy.rodrigo.repository;

import br.com.zupacademy.rodrigo.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
