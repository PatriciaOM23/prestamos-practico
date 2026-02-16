package es.fplumara.dam1.prestamos.repository;

import es.fplumara.dam1.prestamos.model.Identificable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BaseRepository<T extends Identificable> implements Repository {
    Map<String,T> datos = new HashMap<>();
    @Override
    public void save(Identificable element) {

    }

    @Override
    public Optional findById(String id) {
        return Optional.empty();
    }

    @Override
    public List listAll() {
        return List.of();
    }

    @Override
    public void delete(String id) {

    }
}
