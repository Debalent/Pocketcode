package com.pocketcode

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.pocketcode.ui.*

@Composable
fun App() {
    PocketCodeTheme {
        var isSidebarOpen by remember { mutableStateOf(true) }
        var currentScreen by remember { mutableStateOf("project_list") }
        
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when (currentScreen) {
                    "project_list" -> ProjectListScreen(onProjectSelect = { currentScreen = "editor" })
                    else -> {
                        Row(modifier = Modifier.fillMaxSize()) {
                            // Sidebar
                            AnimatedVisibility(
                                visible = isSidebarOpen,
                                enter = slideInHorizontally() + fadeIn(),
                                exit = slideOutHorizontally() + fadeOut()
                            ) {
                                FileTreeSidebar(
                                    onClose = { isSidebarOpen = false },
                                    onSettingsClick = { currentScreen = "settings" },
                                    onGitClick = { currentScreen = "git" }
                                )
                            }

                            // Main Content
                            Column(modifier = Modifier.fillMaxSize()) {
                                EditorHeader(
                                    isSidebarOpen = isSidebarOpen,
                                    onMenuClick = { isSidebarOpen = !isSidebarOpen },
                                    onHomeClick = { currentScreen = "project_list" }
                                )
                                
                                Box(modifier = Modifier.weight(1f)) {
                                    when (currentScreen) {
                                        "editor" -> CodeEditorScreen()
                                        "settings" -> SettingsScreen(onBack = { currentScreen = "editor" })
                                        "git" -> GitPanel()
                                    }
                                }
                                
                                OfflineBanner(visible = true)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FileTreeSidebar(onClose: () -> Unit, onSettingsClick: () -> Unit, onGitClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(280.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "POCKETCODE",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("MENU", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        SidebarItem(icon = Icons.Default.Source, label = "Source Control", onClick = onGitClick)
        SidebarItem(icon = Icons.Default.Settings, label = "Settings", onClick = onSettingsClick)

        Spacer(modifier = Modifier.height(24.dp))
        
        Text("FILES", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(listOf("src", "composeApp", "build.gradle.kts", "settings.gradle.kts")) { item ->
                FileItem(name = item, isDirectory = !item.contains("."))
            }
        }
    }
}

@Composable
fun FileItem(name: String, isDirectory: Boolean) {
    var isExpanded by remember { mutableStateOf(false) }
    
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                if (isDirectory) Icons.Default.KeyboardArrowRight else Icons.Default.Description,
                contentDescription = null,
                tint = if (isDirectory) MaterialTheme.colorScheme.tertiary else Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(name, color = Color.White, fontSize = 14.sp)
        }
        
        if (isDirectory && isExpanded) {
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text("Subfile.kt", color = Color.Gray, fontSize = 13.sp, modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}

@Composable
fun SidebarItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.width(12.dp))
        Text(label, color = Color.White)
    }
}

@Composable
fun EditorHeader(isSidebarOpen: Boolean, onMenuClick: () -> Unit, onHomeClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(if (isSidebarOpen) Icons.Default.MenuOpen else Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
        }
        
        IconButton(onClick = onHomeClick) {
            Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White)
        }
        
        Row(modifier = Modifier.weight(1f)) {
            EditorTab("App.kt", active = true)
            EditorTab("Theme.kt", active = false)
        }
        
        IconButton(onClick = {}) {
            Icon(Icons.Default.PlayArrow, contentDescription = "Run", tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun EditorTab(name: String, active: Boolean) {
    val backgroundColor = if (active) MaterialTheme.colorScheme.background else Color.Transparent
    val textColor = if (active) MaterialTheme.colorScheme.primary else Color.Gray
    
    Box(
        modifier = Modifier
            .padding(top = 8.dp, end = 4.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(backgroundColor)
            .clickable { }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(name, color = textColor, fontWeight = if (active) FontWeight.Bold else FontWeight.Normal)
    }
}

@Composable
fun CodeEditorScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            "fun main() {\n    println(\"Hello PocketCode!\")\n}",
            fontFamily = FontFamily.Monospace,
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        IconButton(onClick = onBack) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
        }
        Text(
            "Settings",
            style = MaterialTheme.typography.displayLarge,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text("Theme", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        Text("Dark (Energetic)", color = Color.White, modifier = Modifier.padding(top = 8.dp))
    }
}

@Composable
fun GitPanel() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Source Control", style = MaterialTheme.typography.titleLarge, color = Color.White)
    }
}

@Composable
fun OfflineBanner(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(vertical = 4.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "OFFLINE MODE - 3 COMMITS READY TO PUSH",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
