package ni.edu.uam.practicanavegacionentrepginas.pantallas

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ni.edu.uam.practicanavegacionentrepginas.modelo.Docente

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inicio(onCrear: (Docente) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var claseName by remember { mutableStateOf("") }
    var claseExpanded by remember { mutableStateOf(false) }
    var fotoUri by remember { mutableStateOf<Uri?>(null) }

    // lanzador para seleccionar imagen (tipo image/*)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            fotoUri = uri
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {

            Card(
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(modifier = Modifier
                    .padding(20.dp)
                ) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
                                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f)
                                )
                            )
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Crear Profesor", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier
                                .size(88.dp)
                                .clip(CircleShape)
                                .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                if (fotoUri != null) {
                                    AsyncImage(
                                        model = fotoUri,
                                        contentDescription = "Foto del docente",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(CircleShape)
                                    )
                                } else {
                                    IconButton(onClick = { launcher.launch("image/*") }) {
                                        Icon(Icons.Default.AddAPhoto, contentDescription = "Subir foto", tint = MaterialTheme.colorScheme.primary)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text("Foto del profesor", style = MaterialTheme.typography.titleMedium)
                                Text("Pulsa el ícono para cargar una imagen", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("Datos personales", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedTextField(
                                value = nombre,
                                onValueChange = { nombre = it },
                                label = { Text("Nombre") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = apellido,
                                onValueChange = { apellido = it },
                                label = { Text("Apellido") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("Clase que impartirá", style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(10.dp))
                            ExposedDropdownMenuBox(
                                expanded = claseExpanded,
                                onExpandedChange = { claseExpanded = !claseExpanded }
                            ) {
                                OutlinedTextField(
                                    value = claseName,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Selecciona una clase") },
                                    trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
                                )
                                ExposedDropdownMenu(
                                    expanded = claseExpanded,
                                    onDismissRequest = { claseExpanded = false }
                                ) {
                                    Docente.clasesDisponibles.forEach { opcion ->
                                        DropdownMenuItem(
                                            text = { Text(opcion) },
                                            onClick = {
                                                claseName = opcion
                                                claseExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(
                            onClick = {
                                if (nombre.isNotBlank() && apellido.isNotBlank() && claseName.isNotBlank()) {
                                    val docente = Docente(nombre.trim(), apellido.trim(), claseName.trim())
                                    if (fotoUri != null) {
                                        docente.fotoUri = (fotoUri.toString())
                                    }
                                    onCrear(docente)
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Crear y Ver", color = MaterialTheme.colorScheme.onPrimary)
                        }
                    }
                }
            }
        }
    }
}
