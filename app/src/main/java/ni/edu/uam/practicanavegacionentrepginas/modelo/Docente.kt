package ni.edu.uam.practicanavegacionentrepginas.modelo

class Docente(private var nombre: String,
              private var apellido: String,
              private var clase : String){
    var fotoUri: String? = null
    fun getNombre(): String {
        return nombre
    }
    fun getApellido(): String {
        return apellido
    }
    fun getClase(): String {
        return clase
    }

}