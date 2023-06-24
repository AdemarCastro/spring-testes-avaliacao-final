package com.br.msf;

import com.br.msf.dao.CidadeDao;
import com.br.msf.model.Cidade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class MsfApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsfApplication.class, args);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CidadeDao cidadeDao = new CidadeDao(entityManager);
        List<Cidade> cidades = cidadeDao.findAll();

        for (Cidade cidade : cidades) {
            System.out.println(cidade.getNome());
        }

        entityManager.close();
        entityManagerFactory.close();

    }
}