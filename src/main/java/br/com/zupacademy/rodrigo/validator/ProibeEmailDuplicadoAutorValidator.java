package br.com.zupacademy.rodrigo.validator;

import br.com.zupacademy.rodrigo.autor.Autor;
import br.com.zupacademy.rodrigo.autor.AutorForm;
import br.com.zupacademy.rodrigo.autor.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AutorForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        AutorForm form = (AutorForm) target;
        Optional<Autor> possivelAutor = autorRepository.findByEmail(form.getEmail());

        if(possivelAutor.isPresent()){
            errors.rejectValue("email", null, "O e-mail: "+  form.getEmail() + " já está em uso!" );
        }
    }
}
