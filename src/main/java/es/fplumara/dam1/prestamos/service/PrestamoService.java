package es.fplumara.dam1.prestamos.service;

import es.fplumara.dam1.prestamos.exception.DuplicadoException;
import es.fplumara.dam1.prestamos.exception.MaterialNoDisponibleException;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.repository.Repository;

import java.security.Permission;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PrestamoService {
    public Repository<Material> materialRepository;
    private Repository<Prestamo> prestamoRepository;

    public PrestamoService(Repository<Prestamo> prestamoRepository, Repository<Material> materialRepository) {
        this.prestamoRepository = prestamoRepository;
        this.materialRepository = materialRepository;
    }

    Prestamo crearPrestamo(String idMaterial, String profesor, LocalDate fecha){
        if(idMaterial.isBlank() || profesor.isBlank() || fecha == null){
            throw new IllegalArgumentException("Los datos son nulos");
        }
        Material m = materialRepository.findById(idMaterial).orElseThrow(() -> new NoEncontradoException("Material no encontrado"));

        if(m.getEstadoMaterial() != EstadoMaterial.DISPONIBLE){
            throw new MaterialNoDisponibleException("El material indicado no está disponible");
        }
        Prestamo prestamo = new Prestamo(UUID.randomUUID(),idMaterial,profesor,fecha);
        m.setEstadoMaterial(EstadoMaterial.PRESTADO);
        materialRepository.save(m);
        prestamoRepository.save(prestamo);
        return prestamo;
    }
    void devolverMaterial(String idMaterial){
        if(idMaterial == null || idMaterial.isBlank()){
            throw new IllegalArgumentException("Es necesario aportar la id del material a devolver");
        }
        Material m = materialRepository.findById(idMaterial).orElseThrow(() -> new NoEncontradoException("Material no encontrado"));
        if(m.getEstadoMaterial() != EstadoMaterial.PRESTADO){
            throw new MaterialNoDisponibleException("Este material no ha sido prestado");
        }
        m.setEstadoMaterial(EstadoMaterial.DISPONIBLE);
        materialRepository.save(m);
    }
    List<Prestamo> listarPrestamos(){
        return prestamoRepository.listAll().stream().toList();
    }

}
