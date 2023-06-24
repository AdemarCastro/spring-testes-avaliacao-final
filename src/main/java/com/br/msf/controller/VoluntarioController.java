package com.br.msf.controller;

import com.br.msf.model.Voluntario;
import com.br.msf.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voluntarios")
public class VoluntarioController {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @GetMapping
    public List<Voluntario> list(){
        List<Voluntario> voluntarios = voluntarioRepository.list();
        return voluntarios;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voluntario> findById(@PathVariable Long id){
        Optional<Voluntario> voluntario = voluntarioRepository.findById(id);
        if(voluntario.isPresent()){
            return ResponseEntity.ok(voluntario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Voluntario> create(@RequestBody Voluntario novoVoluntario,
                                             UriComponentsBuilder uriComponentsBuilder){
        Voluntario voluntario = voluntarioRepository.save(novoVoluntario);

        URI uri = uriComponentsBuilder.path("/api/voluntarios/{id}")
                .buildAndExpand(voluntario.getId()).toUri();

        return ResponseEntity.created(uri).body(voluntario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voluntario> update(@PathVariable Long id, @RequestBody Voluntario voluntario){
        if(!voluntarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        voluntario.setId(id);
        voluntario = voluntarioRepository.save(voluntario);

        return ResponseEntity.ok(voluntario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!voluntarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        voluntarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
