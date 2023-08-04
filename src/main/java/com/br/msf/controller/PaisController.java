package com.br.msf.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.msf.model.Pais;
import com.br.msf.repository.PaisRepository;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping
    public List<Pais> list(){
        List<Pais> paises = paisRepository.list();
        return paises;
    }

    public ResponseEntity<Pais> buscarPorNomeEIBGE(@RequestParam String nome, @RequestParam String ibge) {
        Pais pais = paisRepository.buscarPais(nome, ibge);
        if (pais != null) {
            return ResponseEntity.ok(pais);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Pais find(@PathVariable("id") Long id){
        return paisRepository.findById(id).get();
    }

    @ResponseBody
    @GetMapping(value = "/ibge/{ibge}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Pais findByIbge(@PathVariable("ibge") String ibge){
        return paisRepository.findOneByIbge(ibge);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pais> create(@RequestBody Pais pais,
                                       UriComponentsBuilder uriComponentsBuilder){

        Pais paisRetorno = paisRepository.save(pais);

        URI uri = uriComponentsBuilder.path("/api/paises/{id}")
                .buildAndExpand(paisRetorno.getId()).toUri();

        return ResponseEntity.created(uri).body(paisRetorno);
    }
}