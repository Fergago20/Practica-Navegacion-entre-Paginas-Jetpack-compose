package ni.edu.uam.practicanavegacionentrepginas.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ni.edu.uam.practicanavegacionentrepginas.logica.clase

@Composable
fun Ver(
    claseActual: clase?,
    onAgregar: () -> Unit,
    onEliminarNav: () -> Unit
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

            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), shape = RoundedCornerShape(12.dp)) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    // Avatar del docente (si existe)
                    val foto = docente.fotoUri
                    if (foto != null) {
                        AsyncImage(
                            model = foto,
                            contentDescription = "Foto docente",
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RoundedCornerShape(36.dp))
                        )
                    } else {
                        // placeholder circular con color pastel
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RoundedCornerShape(36.dp))
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(docente.getNombre().firstOrNull()?.toString() ?: "P", color = MaterialTheme.colorScheme.primary)
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text("${docente.getNombre()} ${docente.getApellido()}", style = MaterialTheme.typography.titleMedium)
                        Text("Clase: ${docente.getClase()}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = onAgregar, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                    Text("Agregar Estudiante", color = MaterialTheme.colorScheme.onPrimary)
                }
                OutlinedButton(onClick = onEliminarNav) { Text("Eliminar Estudiantes") }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Estudiantes:", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            val estudiantes = claseActual.getEstudiantes()
            if (estudiantes.isEmpty()) {
                Text("No hay estudiantes aún.", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(estudiantes) { est ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
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
