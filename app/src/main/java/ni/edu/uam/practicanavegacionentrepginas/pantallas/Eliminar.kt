package ni.edu.uam.practicanavegacionentrepginas.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ni.edu.uam.practicanavegacionentrepginas.logica.clase
import ni.edu.uam.practicanavegacionentrepginas.modelo.Estudiante

@Composable
fun Eliminar(
    claseActual: clase?,
    onEliminar: (Estudiante) -> Unit,
    onDone: () -> Unit
) {
    val pastelPink = Color(0xFFFFEAF0)
    val pastelCyan = Color(0xFFDCF7FF)
    val iconCyan = Color(0xFF81D4FA)

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(pastelPink)) {
        Column(modifier = Modifier
            .padding(16.dp)) {
            Text(
                "Eliminar Estudiantes",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFFAD1457) // rosa oscuro para título
            )
            Spacer(modifier = Modifier.height(12.dp))

            if (claseActual == null) {
                Text("No hay profesor / clase creada.", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onDone) { Text("Volver") }
                return@Column
            }

            // Usamos una lista observable para que la UI se refresque inmediatamente
            val estudiantes = remember {
                mutableStateListOf<Estudiante>().apply {
                    addAll(claseActual.getEstudiantes())
                }
            }

            if (estudiantes.isEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = pastelCyan)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("No hay estudiantes para eliminar.", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = onDone, modifier = Modifier.align(Alignment.End)) { Text("Volver") }
                    }
                }
                return@Column
            }

            Text(
                "Pulsa la basura para eliminar. La lista se actualizará al instante.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(estudiantes, key = { it.hashCode() }) { est ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = pastelCyan)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("${est.getNombre()} ${est.getApellido()}", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF263238))
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(est.getCarrera(), style = MaterialTheme.typography.bodySmall, color = Color(0xFF455A64))
                            }

                            IconButton(onClick = {
                                // Llamamos al handler para eliminar en la lógica y además quitamos de la lista local observable
                                onEliminar(est)
                                estudiantes.remove(est)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = iconCyan
                                )
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
