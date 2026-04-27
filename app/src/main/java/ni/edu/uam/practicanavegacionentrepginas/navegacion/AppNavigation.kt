package ni.edu.uam.practicanavegacionentrepginas.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavegacion () {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {


    }
}