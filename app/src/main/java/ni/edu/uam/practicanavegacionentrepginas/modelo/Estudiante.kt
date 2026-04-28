package ni.edu.uam.practicanavegacionentrepginas.modelo

class Estudiante(private var nombre: String,private var apellido: String,private var carrera : String){
    fun getNombre(): String {
        return nombre
    }
    fun getApellido(): String {
        return apellido
    }
    fun getCarrera(): String {
        return carrera
    }
}