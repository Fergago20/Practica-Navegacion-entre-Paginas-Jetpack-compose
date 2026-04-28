package ni.edu.uam.practicanavegacionentrepginas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ni.edu.uam.practicanavegacionentrepginas.navegacion.AppNavegacion
import ni.edu.uam.practicanavegacionentrepginas.ui.theme.PracticaNavegacionEntrePáginasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaNavegacionEntrePáginasTheme {
                AppNavegacion()
            }
        }
    }
}
