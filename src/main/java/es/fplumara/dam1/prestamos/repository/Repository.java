package es.fplumara.dam1.prestamos.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void save(T element);
    Optional<T> findById(String id);
    List<T> listAll();
    void delete(String id);
}