package ni.edu.uam.practicanavegacionentrepginas.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ni.edu.uam.practicanavegacionentrepginas.logica.clase
import ni.edu.uam.practicanavegacionentrepginas.modelo.Estudiante

@Composable
fun Ver(
    claseActual: clase?,
    version: Int,
    onAgregar: () -> Unit,
    onEliminar: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Información de la Clase", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(12.dp))

            if (claseActual == null) {
                Text("Aún no hay profesor creado. Ve a Inicio para crear uno.", style = MaterialTheme.typography.bodyLarge)
                return@Column
            }

            val docente = claseActual.getDocentes()
            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Profesor: ${docente.getNombre()} ${docente.getApellido()}", style = MaterialTheme.typography.titleMedium)
                    Text("Clase: ${docente.getClase()}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = onAgregar) { Text("Agregar Estudiante") }
                OutlinedButton(onClick = onEliminar) { Text("Eliminar Estudiante") }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Estudiantes:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            val estudiantes = claseActual.getEstudiantes()
            if (estudiantes.isEmpty()) {
                Text("No hay estudiantes aún.", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f, fill = false)) {
                    items(estudiantes) { est: Estudiante ->
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                                Column {
                                    Text("${est.getNombre()} ${est.getApellido()}", style = MaterialTheme.typography.bodyLarge)
                                    Text(est.getCarrera(), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
