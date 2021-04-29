package br.com.zupacademy.rodrigo.pais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PaisResponse> cadastro(@RequestBody @Valid PaisRequest paisRequest) {
        Pais pais = paisRequest.convertePaisRequestParaPais();
        paisRepository.save(pais);
        return ResponseEntity.ok(new PaisResponse(pais));
    }
}
