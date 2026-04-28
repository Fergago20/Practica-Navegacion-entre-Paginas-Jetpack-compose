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

    val claseState = remember { mutableStateOf<clase?>(null) }
    val version = remember { mutableStateOf(0) }

    fun crearClaseConDocente(docente: Docente) {
        claseState.value = clase(
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
            Ver(
                claseActual = claseState.value,
                onAgregar = { navController.navigate("agregar") },
                onEliminarNav = { navController.navigate("eliminar") }
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

