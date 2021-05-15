package br.com.zupacademy.rodrigo.validator;


import br.com.zupacademy.rodrigo.estado.Estado;
import br.com.zupacademy.rodrigo.estado.EstadoRepository;
import br.com.zupacademy.rodrigo.estado.EstadoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;
@Component
public class ProibeNomeEstadoRepetidoNoMesmoPais implements Validator {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return EstadoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }
        EstadoRequest request = (EstadoRequest) target;

        Optional<Estado> estado = estadoRepository.buscaEstadoPorNomeEPais(request.getNome(), request.getIdPais());
        if (estado.isPresent()) {
            errors.rejectValue("idPais", null, "Já existe esse estado nesse país!");
        }
    }
}
