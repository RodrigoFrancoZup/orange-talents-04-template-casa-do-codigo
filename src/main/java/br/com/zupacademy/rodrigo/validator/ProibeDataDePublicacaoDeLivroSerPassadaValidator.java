package br.com.zupacademy.rodrigo.validator;

import br.com.zupacademy.rodrigo.livro.LivroForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.time.LocalDate;

@Component
public class ProibeDataDePublicacaoDeLivroSerPassadaValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LivroForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        LivroForm form = (LivroForm) target;
        LocalDate hoje = LocalDate.now();
        if(form.getDataDePublicacao().isBefore(hoje)){
            errors.rejectValue("dataDePublicacao", null, "Data Passada!");
        }
    }
}
