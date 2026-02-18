package es.fplumara.dam1.prestamos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Prestamo implements Identificable{
    private UUID id;
    private String idMaterial;
    private String profesor;
    private LocalDate fecha;

    public Prestamo(UUID id, String idMaterial, String profesor, LocalDate fecha) {
        this.id = id;
        this.idMaterial = idMaterial;
        this.profesor = profesor;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", idMaterial='" + idMaterial + '\'' +
                ", profesor='" + profesor + '\'' +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public String getId() {
        return id.toString();
    }
}
