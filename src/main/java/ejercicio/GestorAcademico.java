package ejercicio;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico implements ServiciosAcademicosI {
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Curso, ArrayList<Estudiante>> inscripciones;

    // Constructor
    public GestorAcademico() {
        estudiantes = new ArrayList<>();
        cursos = new ArrayList<>();
        inscripciones = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        Curso curso = obtenerCursoPorId(idCurso);
        if (curso == null) {
            System.out.println("Curso no encontrado.");
            return;
        }

        if (inscripciones.containsKey(curso) && inscripciones.get(curso).contains(estudiante)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en este curso.");
        }

        inscripciones.computeIfAbsent(curso, k -> new ArrayList<>()).add(estudiante);
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        Estudiante estudiante = obtenerEstudiantePorId(idEstudiante);
        Curso curso = obtenerCursoPorId(idCurso);

        if (curso == null || estudiante == null) {
            System.out.println("Estudiante o curso no encontrado.");
            return;
        }

        if (!inscripciones.containsKey(curso) || !inscripciones.get(curso).contains(estudiante)) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en este curso.");
        }

        inscripciones.get(curso).remove(estudiante);
    }

    private Estudiante obtenerEstudiantePorId(int id) {
        return estudiantes.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    private Curso obtenerCursoPorId(int id) {
        return cursos.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
