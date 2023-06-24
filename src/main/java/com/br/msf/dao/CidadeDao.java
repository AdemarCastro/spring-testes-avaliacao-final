package com.br.msf.dao;

import com.br.msf.model.Cidade;
import com.br.msf.repository.CidadeRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CidadeDao {

    private final EntityManager entityManager;

    public CidadeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Cidade> findAll() {
        return entityManager.createQuery("SELECT c FROM Cidade c", Cidade.class).getResultList();
    }

}
