package com.br.msf.repository;

import com.br.msf.model.Voluntario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class VoluntarioRespotirotyImpl implements VoluntarioRepository{

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<Voluntario> list() {
        return null;
    }

    @Override
    public Voluntario findOneByPassaporte(String parPassaporte) {
        return null;
    }

    @Override
    public <S extends Voluntario> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Voluntario> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Voluntario> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Voluntario> findAll() {
        return null;
    }

    @Override
    public Iterable<Voluntario> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Voluntario entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Voluntario> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
