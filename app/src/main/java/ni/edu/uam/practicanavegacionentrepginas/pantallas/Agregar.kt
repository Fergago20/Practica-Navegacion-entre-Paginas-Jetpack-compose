package ni.edu.uam.practicanavegacionentrepginas.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ni.edu.uam.practicanavegacionentrepginas.modelo.Estudiante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Agregar(onAgregar: (Estudiante) -> Unit, onCancel: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("") }
    var carreraExpanded by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("Agregar Estudiante", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("Datos personales", style = MaterialTheme.typography.titleSmall)
                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(value = apellido, onValueChange = { apellido = it }, label = { Text("Apellido") }, modifier = Modifier.fillMaxWidth())
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("Carrera", style = MaterialTheme.typography.titleSmall)
                            Spacer(modifier = Modifier.height(10.dp))
                            ExposedDropdownMenuBox(
                                expanded = carreraExpanded,
                                onExpandedChange = { carreraExpanded = !carreraExpanded }
                            ) {
                                OutlinedTextField(
                                    value = carrera,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Selecciona una carrera") },
                                    trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
                                )
                                ExposedDropdownMenu(
                                    expanded = carreraExpanded,
                                    onDismissRequest = { carreraExpanded = false }
                                ) {
                                    Estudiante.carrerasDisponibles.forEach { opcion ->
                                        DropdownMenuItem(
                                            text = { Text(opcion) },
                                            onClick = {
                                                carrera = opcion
                                                carreraExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(onClick = onCancel) { Text("Cancelar") }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            if (nombre.isNotBlank() && apellido.isNotBlank() && carrera.isNotBlank()) {
                                onAgregar(Estudiante(nombre.trim(), apellido.trim(), carrera.trim()))
                            }
                        }) {
                            Text("Agregar")
                        }
                    }
                }
            }
        }
    }
}
