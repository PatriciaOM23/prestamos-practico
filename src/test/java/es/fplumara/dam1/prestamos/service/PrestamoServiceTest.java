package es.fplumara.dam1.prestamos.service;


import es.fplumara.dam1.prestamos.exception.NoEncontradoException;
import es.fplumara.dam1.prestamos.model.EstadoMaterial;
import es.fplumara.dam1.prestamos.model.Material;
import es.fplumara.dam1.prestamos.model.Portatil;
import es.fplumara.dam1.prestamos.model.Prestamo;
import es.fplumara.dam1.prestamos.repository.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.PrestamoRepositoryImpl;
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


    // TODO (alumnos): añadir JUnit 5 y Mockito en el pom.xml y completar:
    //
    // - crearPrestamo_ok_cambiaEstado_y_guarda()
    // - crearPrestamo_materialNoExiste_lanzaNoEncontrado()
    // - crearPrestamo_materialNoDisponible_lanzaMaterialNoDisponible()
    // - devolverMaterial_ok_cambiaADisponible()
    //
    // Requisito: usar mocks de repositorios y verify(...)
    @Test
    void crearPrestamo_ok_cambiaEstado_y_guarda(){
        Material material = new Portatil("1","laptop hp", EstadoMaterial.DISPONIBLE);
        when(materialRepository.findById("1")).thenReturn(Optional.of(material));
        Prestamo prestamo = prestamoService.crearPrestamo(material.getId(),"Ivan",LocalDate.now());
        verify(prestamoRepository).save(any(Prestamo.class));
        assertEquals(EstadoMaterial.PRESTADO,material.getEstadoMaterial());
    }

    @Test
    void crearPrestamo_materialNoExiste_lanzaNoEncontrado(){
        Material material = new Portatil("1","laptop hp", EstadoMaterial.BAJA);
        Exception exception =  assertThrows(NoEncontradoException.class, () ->
                prestamoService.crearPrestamo(material.getId(),"Ivan",LocalDate.now()));
    }
}