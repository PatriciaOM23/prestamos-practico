package es.fplumara.dam1.prestamos.model;

import java.util.List;
import java.util.Set;

public class Portatil extends Material {
    private int ramGB;

    public Portatil(String id, String nombre, EstadoMaterial estadoMaterial) {
        super(id, nombre, estadoMaterial);
    }


    public Portatil(String id, String nombre, EstadoMaterial estadoMaterial, int ramGB, Set<String> etiquetas) {
        super(id, nombre, estadoMaterial, ramGB, etiquetas);
    }

    @Override
    public String getTipo() {
        return "portatil";
    }
}
