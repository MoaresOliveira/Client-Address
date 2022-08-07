package io.github.moaresoliveira.api.service;

import io.github.moaresoliveira.api.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ViaCepService", url = "https://viacep.com.br/ws/")
public interface ViaCepService {

    @RequestMapping(value = "{cep}/json", method = RequestMethod.GET)
    Endereco buscarPorCep(@PathVariable("cep") String cep);

}
