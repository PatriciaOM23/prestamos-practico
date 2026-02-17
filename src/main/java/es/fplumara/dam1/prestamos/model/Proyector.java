package es.fplumara.dam1.prestamos.model;

public class Proyector extends Material{
    private int lumens;

    public Proyector(String id, String nombre, EstadoMaterial estadoMaterial) {
        super(id, nombre, estadoMaterial);
    }

    @Override
    String getTipo() {
        return "proyector";
    }
}
