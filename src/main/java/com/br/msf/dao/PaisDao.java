package com.br.msf.dao;

import java.util.List;
import javax.persistence.EntityManager;

import com.br.msf.model.Cidade;
import com.br.msf.model.Pais;

public class PaisDao {
  
  private final EntityManager entityManager;

    public PaisDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Pais> findAll() {
        return entityManager.createQuery("SELECT p FROM Pais p", Pais.class).getResultList();
    }
}
