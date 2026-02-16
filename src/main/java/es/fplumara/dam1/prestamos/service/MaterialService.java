package es.fplumara.dam1.prestamos.service;

import es.fplumara.dam1.prestamos.exception.DuplicadoException;
import es.fplumara.dam1.prestamos.exception.MaterialNoDisponibleException;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.repository.Repository;

import java.util.List;

public class MaterialService {
    private Repository<Material> materialRepository;
    void registrarMaterial(Material m){
        if(materialRepository.findById(m.getId()).isPresent()){
            throw new DuplicadoException("Ese material ya existe");
        }
        if(m == null || m.getId().isBlank()){
            throw new IllegalArgumentException("El material no puede estar vacío");
        }
    }
    void darDeBaja(String idMaterial) {
        Material m = materialRepository.findById(idMaterial).orElseThrow(() -> new NoEncontradoException("El material indicado no existe"));
        if(m.getEstadoMaterial() == EstadoMaterial.BAJA){
            throw new MaterialNoDisponibleException("El material indicado no está disponible");
        }
        m.setEstadoMaterial(EstadoMaterial.BAJA);
        materialRepository.save(m);
    }

    List<Material> listar(){
        return materialRepository.listAll().stream().toList();
    }
}
