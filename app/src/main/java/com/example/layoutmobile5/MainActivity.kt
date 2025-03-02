package com.example.layoutmobile5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RatingApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Application", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF3F51B5))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Exibir a hora atual
            val currentTime = remember { SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date()) }
            Text(text = "Time      $currentTime", fontSize = 16.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            // Campos de entrada
            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }

            CustomTextField(label = "First Name", value = firstName, onValueChange = { firstName = it })
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(label = "Last Name", value = lastName, onValueChange = { lastName = it })

            Spacer(modifier = Modifier.height(16.dp))

            // Avaliação por estrelas
            StarRating()

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de envio
            Button(
                onClick = { /* Ação ao clicar */ },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(text = "SUBMIT", fontSize = 16.sp, color = Color.Black)
            }
        }
    }
}

// Componente de campo de entrada com estado
@Composable
fun CustomTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(0.8f)


    )
}

// Componente de avaliação por estrelas
@Composable
fun StarRating() {
    var rating by remember { mutableStateOf(0) }
    Row {
        for (i in 1..5) {
            IconButton(onClick = { rating = i }) {
                Icon(
                    painter = painterResource(id = if (i <= rating) R.drawable.estrelacheia else R.drawable.estrela),
                    contentDescription = "Star",
                    tint = if (i <= rating) Color(0xFFFFD700) else Color.Gray,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRatingApp() {
    RatingApp()
}