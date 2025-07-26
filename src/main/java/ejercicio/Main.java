package ejercicio;

public class Main {
    public static void main(String[] args) {
        // Crear instancia del GestorAcademico
        GestorAcademico gestor = new GestorAcademico();

        // Crear estudiantes
        Estudiante est1 = new Estudiante(1, "Juan", "Pérez", "2000-01-01", "matriculado");
        Estudiante est2 = new Estudiante(2, "Ana", "González", "2001-02-02", "matriculado");

        // Crear cursos
        Curso curso1 = new Curso(101, "Matemáticas", "Curso de matemáticas avanzadas", 4, "v1.0");
        Curso curso2 = new Curso(102, "Historia", "Curso de historia mundial", 3, "v1.0");

        // Agregar estudiantes y cursos
        gestor.matricularEstudiante(est1);
        gestor.matricularEstudiante(est2);
        gestor.agregarCurso(curso1);
        gestor.agregarCurso(curso2);

        // Inscribir estudiantes en cursos
        try {
            gestor.inscribirEstudianteCurso(est1, 101);
            gestor.inscribirEstudianteCurso(est2, 102);
        } catch (EstudianteYaInscritoException e) {
            System.out.println(e.getMessage());
        }

        // Desinscribir estudiantes
        try {
            gestor.desinscribirEstudianteCurso(1, 101);
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.out.println(e.getMessage());
        }

        // Mostrar el estado final
        System.out.println("Inscripciones finalizadas.");
    }
}
