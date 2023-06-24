package com.br.msf.repository;

import com.br.msf.model.Voluntario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface VoluntarioRepository extends CrudRepository<Voluntario,Long> {

    @Query("SELECT v FROM Voluntario v")
    List<Voluntario> list();

    @Query("SELECT v FROM Voluntario v WHERE v.passaporte = :parPassaporte")
    Voluntario findOneByPassaporte(String parPassaporte);

}
