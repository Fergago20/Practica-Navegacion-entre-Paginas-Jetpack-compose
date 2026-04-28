package ni.edu.uam.practicanavegacionentrepginas.navegacion

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ni.edu.uam.practicanavegacionentrepginas.logica.clase
import ni.edu.uam.practicanavegacionentrepginas.modelo.Docente
import ni.edu.uam.practicanavegacionentrepginas.modelo.Estudiante
import ni.edu.uam.practicanavegacionentrepginas.pantallas.Agregar
import ni.edu.uam.practicanavegacionentrepginas.pantallas.Eliminar
import ni.edu.uam.practicanavegacionentrepginas.pantallas.Inicio
import ni.edu.uam.practicanavegacionentrepginas.pantallas.Ver

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()

    // Estado compartido: la instancia de 'clase' (puede ser null hasta crear profesor)
    val claseState = remember { mutableStateOf<clase?>(null) }
    // Version para forzar recomposición cuando cambia la lista interna
    val version = remember { mutableStateOf(0) }

    fun crearClaseConDocente(docente: Docente) {
        // aquí usamos el nombre/apellido del docente para inicializar la clase
        claseState.value = ni.edu.uam.practicanavegacionentrepginas.logica.clase(
            docente.getNombre(),
            docente.getApellido(),
            docente
        )
        version.value++
    }

    fun agregarEstudiante(est: Estudiante) {
        claseState.value?.agregarEstudiante(est)
        version.value++
    }

    fun eliminarEstudiante(est: Estudiante) {
        claseState.value?.eliminarEstudiante(est)
        version.value++
    }

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            Inicio(
                onCrear = { docente ->
                    crearClaseConDocente(docente)
                    navController.navigate("ver")
                }
            )
        }
        composable("agregar") {
            Agregar(
                onAgregar = { estudiante ->
                    agregarEstudiante(estudiante)
                    navController.popBackStack() // vuelve a "ver"
                },
                onCancel = { navController.popBackStack() }
            )
        }
        composable("ver") {
            // pasamos la referencia y la versión para que la pantalla se recomporte al cambiar
            Ver(
                claseActual = claseState.value,
                version = version.value,
                onAgregar = { navController.navigate("agregar") },
                onEliminar = { navController.navigate("eliminar") }
            )
        }
        composable("eliminar") {
            Eliminar(
                claseActual = claseState.value,
                onEliminar = { estudiante ->
                    eliminarEstudiante(estudiante)
                },
                onDone = { navController.popBackStack() }
            )
        }
    }
}
