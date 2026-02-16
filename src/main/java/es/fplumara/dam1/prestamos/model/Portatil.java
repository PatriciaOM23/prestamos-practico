package es.fplumara.dam1.prestamos.model;

public class Portatil extends Material {
    private int ramGB;

    public Portatil(String id, String nombre, EstadoMaterial estadoMaterial) {
        super(id, nombre, estadoMaterial);
    }

    @Override
    String getTipo() {
        return "portatil";
    }
}
