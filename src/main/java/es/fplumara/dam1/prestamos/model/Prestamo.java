package es.fplumara.dam1.prestamos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Prestamo implements Identificable{
    private String id;
    private String idMaterial;
    private String profesor;
    private LocalDate fecha;

    @Override
    public String getId() {
        return "";
    }
}
