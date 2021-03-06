package br.com.coelho.pedidos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.coelho.pedidos.repository.enties.EnderecoEntity;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@GetMapping // http://localhost:8080/enderecos?cep=*
	public EnderecoEntity getByCep(@RequestParam(name = "cep") String cep) {

		RestTemplate restTemplate = new RestTemplate();
		String urlViaCep = "http://viacep.com.br/ws/" + cep + "/json/";
		ResponseEntity<String> response = restTemplate.getForEntity(urlViaCep, String.class);

		String jsonEnd = response.getBody();

		Gson gson = new Gson();

		EnderecoEntity end = gson.fromJson(jsonEnd, EnderecoEntity.class);

		return end;
	}

}
