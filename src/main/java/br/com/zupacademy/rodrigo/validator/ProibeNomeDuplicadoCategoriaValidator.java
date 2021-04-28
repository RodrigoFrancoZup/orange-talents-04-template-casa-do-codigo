package br.com.zupacademy.rodrigo.validator;


import br.com.zupacademy.rodrigo.categoria.Categoria;
import br.com.zupacademy.rodrigo.categoria.CategoriaForm;
import br.com.zupacademy.rodrigo.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeNomeDuplicadoCategoriaValidator implements Validator {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoriaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        CategoriaForm form = (CategoriaForm) target;
        Optional<Categoria> possivelCategoria = categoriaRepository.findByNome(form.getNome());

        if(possivelCategoria.isPresent()){
            errors.rejectValue("Nome", null, "Já existe categoria com o nome: " + form.getNome());
        }

    }
}
