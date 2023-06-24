package com.br.msf.repository;

import com.br.msf.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaisRepository extends JpaRepository<Pais,Long> {

    @Query("SELECT p FROM Pais p")
    List<Pais> list();

    @Query("SELECT p FROM Pais p WHERE p.ibge = :parIbge")
    Pais findOneByIbge(String parIbge);

    List<Pais> findByNomeIgnoreCase(String nome); // IgnoreCase acha o valor ignorando o Capslock da pesquisa
}
