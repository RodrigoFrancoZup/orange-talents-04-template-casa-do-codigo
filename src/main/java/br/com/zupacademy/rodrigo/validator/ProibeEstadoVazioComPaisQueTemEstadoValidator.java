package br.com.zupacademy.rodrigo.validator;

import br.com.zupacademy.rodrigo.cliente.ClienteRequest;
import br.com.zupacademy.rodrigo.estado.Estado;
import br.com.zupacademy.rodrigo.estado.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class ProibeEstadoVazioComPaisQueTemEstadoValidator implements Validator {

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        ClienteRequest request = (ClienteRequest) target;
        List<Estado> estado = estadoRepository.findByPaisId(request.getIdPais());
        if(estado.size() >= 1 && request.getIdEstado() == null) {
            errors.rejectValue("idEstado", null, "Para o seu Páis o estado é obrigatório!");
        }
    }
}
