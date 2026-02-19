package es.fplumara.dam1.prestamos.app;

import es.fplumara.dam1.prestamos.csv.CSVMaterialExporter;
import es.fplumara.dam1.prestamos.csv.CSVMaterialImporter;
import es.fplumara.dam1.prestamos.csv.RegistroMaterialCsv;
import es.fplumara.dam1.prestamos.exception.CsvInvalidoException;
import es.fplumara.dam1.prestamos.model.*;
import es.fplumara.dam1.prestamos.repository.MaterialRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.PrestamoRepositoryImpl;
import es.fplumara.dam1.prestamos.repository.Repository;
import es.fplumara.dam1.prestamos.service.MaterialService;
import es.fplumara.dam1.prestamos.service.PrestamoService;
import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Main de ejemplo para demostrar el flujo mínimo del examen (sin menú complejo).
 * La idea es que este método ejecute una "demo" por consola.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Examen DAM1 - Préstamo de Material (Java 21)");
        Repository<Material> materialRepository = new MaterialRepositoryImpl();
        Repository<Prestamo> prestamoRepository = new PrestamoRepositoryImpl();
        MaterialService materialService = new MaterialService(materialRepository);
        PrestamoService prestamoService = new PrestamoService(prestamoRepository,materialRepository);
        CSVMaterialExporter writer = new CSVMaterialExporter();
        CSVMaterialImporter reader = new CSVMaterialImporter();

        //    public RegistroMaterialCsv {
        //        tipo = noVacio(tipo, "tipo");
        //        id = noVacio(id, "id");
        //        nombre = noVacio(nombre, "nombre");
        //        estado = noVacio(estado, "estado");
        //        etiquetas = etiquetas == null ? Set.of() : Collections.unmodifiableSet(new HashSet<>(etiquetas));
        //    }
        List<RegistroMaterialCsv> registroMaterial = reader.leer("data/materiales.csv");
        registroMaterial.forEach(System.out::println);
        for(RegistroMaterialCsv r : registroMaterial){
            //REGISTRAR ESTADO MATERIAL A EstadoMaterial.class
            EstadoMaterial estadoMaterial = EstadoMaterial.BAJA;
            switch (r.estado()){
                case "DISPONIBLE" -> estadoMaterial = EstadoMaterial.DISPONIBLE;
                case "PRESTADO" -> estadoMaterial = EstadoMaterial.PRESTADO;
                }
            // REGISTRAR SI ES PORTATIL O PROYECTOR Y REGISTRAR EN FUNCION
            Material material;
            switch (r.tipo().trim()){
                case "PORTATIL" ->
                        {
                             material = new Portatil(r.id(),r.nombre(),estadoMaterial,r.extra(),r.etiquetas());

                        }

                case "PROYECTOR" -> material = new Proyector(r.id(),r.nombre(),estadoMaterial,r.extra(),r.etiquetas());

                default -> throw new CsvInvalidoException("Material no válido");
            }
          materialService.registrarMaterial(material);
        }
        List<Material> materiales = materialService.listar();
            prestamoService.crearPrestamo(materiales.get(1).getId(),"Ivan",LocalDate.now());
            //Comprobar que el material pasa a estado PRESTADO
        System.out.println(materiales.get(1).getEstadoMaterial().toString());
            System.out.println("-------------------");
            List<Prestamo> prestamos = prestamoService.listarPrestamos();
            prestamos.forEach(System.out::println);

        System.out.println(" Llamar a PrestamoService.devolverMaterial(indice 1)\n" +
                "         *    - Comprobar que vuelve a estado DISPONIBLE ");
        prestamoService.devolverMaterial(materiales.get(1).getId());
        System.out.println(materiales.get(1).getEstadoMaterial().toString());

         // 7) Exportar a CSV
         List<RegistroMaterialCsv> registros = new ArrayList<>();
        for(Material m : materiales){
        registros.add( new RegistroMaterialCsv(m.getTipo(),m.getId(),m.getNombre(),m.getEstadoMaterial().toString(),m.getExtra(),m.getEtiquetas()));
        }
        //for que recorra materiales e ir sacando las variables y creando la lista de registros

        writer.escribir("data/salida_materiales.csv",registros);

    }}