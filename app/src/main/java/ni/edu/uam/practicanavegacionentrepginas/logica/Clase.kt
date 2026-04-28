package ni.edu.uam.practicanavegacionentrepginas.logica

import ni.edu.uam.practicanavegacionentrepginas.modelo.Docente
import ni.edu.uam.practicanavegacionentrepginas.modelo.Estudiante

class clase (private var nombre: String,private var apellido: String, private var docentes: Docente){
    private var estudiantes = mutableListOf<Estudiante>()
    fun getNombre(): String {
        return nombre
    }
    fun getApellido(): String {
        return apellido
    }
    fun getDocentes(): Docente {
        return docentes
    }
    fun getEstudiantes(): MutableList<Estudiante> {
        return estudiantes
    }
    fun agregarEstudiante(estudiante: Estudiante) {
        estudiantes.add(estudiante)
    }
    fun eliminarEstudiante(estudiante: Estudiante) {
        estudiantes.remove(estudiante)
    }

    fun conteoEstudiantes(): Int {
        return estudiantes.size
    }
}