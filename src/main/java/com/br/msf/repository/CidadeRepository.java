package com.br.msf.repository;

import com.br.msf.model.Cidade;
import com.br.msf.model.Pais;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CidadeRepository extends CrudRepository<Cidade,Long> {

    @Query("SELECT c FROM Cidade c")
    List<Cidade> list();

    @Query("SELECT c FROM Cidade c WHERE c.ibge = :parIbge")
    Cidade findOneByIbge(String parIbge);

    List<Cidade> findByNomeIgnoreCase(String nome); // IgnoreCase acha o valor ignorando o Capslock da pesquisa


}
