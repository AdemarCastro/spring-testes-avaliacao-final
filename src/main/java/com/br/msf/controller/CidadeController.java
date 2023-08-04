package com.br.msf.controller;

import com.br.msf.dto.CidadeInputDto;
import com.br.msf.model.Cidade;
import com.br.msf.model.Pais;
import com.br.msf.repository.CidadeRepository;
import com.br.msf.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {


    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping
    public List<Cidade> list(){
        List<Cidade> cidades = cidadeRepository.list();
        return cidades;
    }

    public ResponseEntity<Cidade> buscarPorNomeEIBGE(@RequestParam String nome, @RequestParam String ibge) {
        Cidade cidade = cidadeRepository.buscarCidade(nome, ibge);
        if (cidade != null) {
            return ResponseEntity.ok(cidade);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @ResponseBody
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade find(@PathVariable("id") Long id){

        return cidadeRepository.findById(id).get();

    }

    @ResponseBody
    @GetMapping(value = "/ibge/{ibge}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Cidade findByIbge(@PathVariable("ibge") String ibge){

        return cidadeRepository.findOneByIbge(ibge);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> create(@RequestBody CidadeInputDto cidadeInputDto,
                                         UriComponentsBuilder uriComponentsBuilder){

        Cidade cidade = cidadeInputDto.build(paisRepository);

        Cidade cidadeRetorno = cidadeRepository.save(cidade);

//       status de retorno: 201 (ok)
//        path: uri da entidade
//        body: conteúdo do objeto em json

        URI uri = uriComponentsBuilder.path("/api/cidades/{id}")
                .buildAndExpand(cidadeRetorno.getId()).toUri();

        return ResponseEntity.created(uri).body(cidadeRetorno);

    }
}