package es.fplumara.dam1.prestamos.model;

import java.util.Set;

public abstract class Material implements Identificable {
        String id;
        String nombre;
        EstadoMaterial estadoMaterial;
        Set<String> etiquetas;

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
