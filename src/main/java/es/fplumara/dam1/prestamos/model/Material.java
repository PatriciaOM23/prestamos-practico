package es.fplumara.dam1.prestamos.model;

import java.util.Set;

public abstract class Material implements Identificable {
        private String id;
        private String nombre;
        private EstadoMaterial estadoMaterial;
        private int extra;
        private Set<String> etiquetas;

    public Material(String id, String nombre, EstadoMaterial estadoMaterial) {
        this.id = id;
        this.nombre = nombre;
        this.estadoMaterial = estadoMaterial;
    }

    public Material(String id, String nombre, EstadoMaterial estadoMaterial, int extra, Set<String> etiquetas) {
        this.id = id;
        this.nombre = nombre;
        this.estadoMaterial = estadoMaterial;
        this.extra = extra;
        this.etiquetas = etiquetas;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoMaterial getEstadoMaterial() {
        return estadoMaterial;
    }

    public Set<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEstadoMaterial(EstadoMaterial estadoMaterial) {
        this.estadoMaterial = estadoMaterial;
    }

    abstract String getTipo();
}
