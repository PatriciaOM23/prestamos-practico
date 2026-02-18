package es.fplumara.dam1.prestamos.service;


import es.fplumara.dam1.prestamos.exception.MaterialNoDisponibleException;
import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.model.Portatil;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.repository.BaseRepository;
import es.fplumara.dam1.prestamos.repository.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.PrestamoRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrestamosServiceTest {
    @InjectMocks
    PrestamoService prestamoService;
    @Mock
    PrestamoRepositoryImpl prestamoRepository;
    @Mock
    MaterialRepositoryImpl materialRepository;

    @Test
    void crearPrestamo_ok_cambiaEstado_y_guarda(){
        Material material = new Portatil("1","laptop hp", EstadoMaterial.DISPONIBLE);
        when(materialRepository.findById(material.getId())).thenReturn(Optional.of(material));
        Prestamo prestamo = prestamoService.crearPrestamo(material.getId(),"Ivan", LocalDate.now());
        verify(prestamoRepository).save(any(Prestamo.class));
        assertEquals(EstadoMaterial.PRESTADO,material.getEstadoMaterial());
    }

    @Test
    void crearPrestamo_materialNoExiste_lanzaNoEncontrado(){
        Exception exception =  assertThrows(NoEncontradoException.class, () ->
                prestamoService.crearPrestamo("1","Ivan",LocalDate.now()));
    }

    @Test
    void crearPrestamo_materialNoDisponible_lanzaMaterialNoDisponible(){
        Material material = new Portatil("1","laptop hp", EstadoMaterial.BAJA);
        when(materialRepository.findById(material.getId())).thenReturn(Optional.of(material));
        assertThrows(MaterialNoDisponibleException.class, () -> prestamoService.crearPrestamo(material.getId(),"Ivan",LocalDate.now()));
        verifyNoInteractions(prestamoRepository);

    }

    @Test
    void devolverMaterial_ok_cambiaADisponible(){
        Material material = new Portatil("1","laptop hp", EstadoMaterial.PRESTADO);
        when(materialRepository.findById(material.getId())).thenReturn(Optional.of(material));
       prestamoService.devolverMaterial(material.getId());
       assertEquals(EstadoMaterial.DISPONIBLE, material.getEstadoMaterial());
       verify(materialRepository).save(any(Material.class));
    }

}