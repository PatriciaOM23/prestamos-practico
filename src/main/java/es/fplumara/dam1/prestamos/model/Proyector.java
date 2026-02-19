package es.fplumara.dam1.prestamos.model;

import java.util.Set;

public class Proyector extends Material{
    private int lumens;

    public Proyector(String id, String nombre, EstadoMaterial estadoMaterial) {
        super(id, nombre, estadoMaterial);
    }

    public Proyector(String id, String nombre, EstadoMaterial estadoMaterial, int lumens) {
        super(id, nombre, estadoMaterial);
        this.lumens = lumens;
    }

    public Proyector(String id, String nombre, EstadoMaterial estadoMaterial, int lumens ,Set<String> etiquetas) {
        super(id, nombre, estadoMaterial, lumens, etiquetas);
    }



    @Override
    public String getTipo() {
        return "proyector";
    }
}
