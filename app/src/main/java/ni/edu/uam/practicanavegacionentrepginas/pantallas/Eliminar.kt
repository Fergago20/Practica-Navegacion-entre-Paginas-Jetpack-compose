package ni.edu.uam.practicanavegacionentrepginas.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ni.edu.uam.practicanavegacionentrepginas.logica.clase
import ni.edu.uam.practicanavegacionentrepginas.modelo.Estudiante

@Composable
fun Eliminar(
    claseActual: clase?,
    onEliminar: (Estudiante) -> Unit,
    onDone: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Eliminar Estudiantes", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(12.dp))
            Spacer(modifier = Modifier.height(8.dp))

            if (claseActual == null) {
                Text("No hay profesor / clase creada.", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onDone) { Text("Volver") }
                return@Column
            }

            var lista : MutableList<Estudiante> = claseActual.getEstudiantes()
            if (lista.isEmpty()) {
                Text("No hay estudiantes para eliminar.", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onDone) { Text("Volver") }
                return@Column
            }

            Text("Pulsa la basura para eliminar y refrescar al instante.", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(lista) { est ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("${est.getNombre()} ${est.getApellido()}", style = MaterialTheme.typography.bodyLarge)
                                Text(est.getCarrera(), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                            }
                            IconButton(onClick = {
                                onEliminar(est)
                                lista.remove(est)
                            }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onDone, modifier = Modifier.align(Alignment.End)) { Text("Hecho") }
        }
    }
}
