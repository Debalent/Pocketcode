package com.pocketcode.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pocketcode.*

@Composable
fun GitPanel() {
    var commitMessage by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RichBlack)
            .padding(16.dp)
    ) {
        Text(
            "SOURCE CONTROL",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray,
            letterSpacing = 2.sp
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("STAGED CHANGES", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(listOf("App.kt", "Theme.kt")) { file ->
                GitFileItem(file, isStaged = true)
            }
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("UNSTAGED CHANGES", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            }
            
            items(listOf("README.md")) { file ->
                GitFileItem(file, isStaged = false)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = commitMessage,
            onValueChange = { commitMessage = it },
            placeholder = { Text("Commit message...", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.DarkGray,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = DeepCharcoal,
                focusedContainerColor = DeepCharcoal
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { /* Commit action */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(Icons.Default.Check, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("COMMIT TO MAIN", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun GitFileItem(name: String, isStaged: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (isStaged) SuccessGreen else WarningYellow)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(name, color = Color.White, fontSize = 14.sp)
        }
        Text(if (isStaged) "M" else "U", color = if (isStaged) SuccessGreen else WarningYellow, fontWeight = FontWeight.Bold)
    }
}
