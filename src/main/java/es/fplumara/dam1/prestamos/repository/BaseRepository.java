package es.fplumara.dam1.prestamos.repository;

import es.fplumara.dam1.prestamos.model.Identificable;
import es.fplumara.dam1.prestamos.model.Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BaseRepository<T extends Identificable> implements Repository<T> {
    Map<String,T> datos = new HashMap<>();
    @Override
    public void save(T element) {
    datos.put(element.getId(),element);
    }

    @Override
    public Optional findById(String id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List listAll() {
        return List.of(datos.values());
    }

    @Override
    public void delete(String id) {
        datos.remove(id);
    }
}
