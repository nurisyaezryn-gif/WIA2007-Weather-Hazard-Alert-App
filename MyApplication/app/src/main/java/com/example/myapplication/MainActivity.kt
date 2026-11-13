package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainAppContainer()
            }
        }
    }
}

@Composable
fun MainAppContainer() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Timetable") },
                    label = { Text("Schedule") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Place, contentDescription = "Map") },
                    label = { Text("Map") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Warning, contentDescription = "Report Hazard") },
                    label = { Text("Report") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> TimetableFeatureContainer()
                1 -> {
                    MapScreen()
                }
                2 -> {
                    ReportHazardScreen()
                }
            }
        }
    }
}

@Composable
fun TimetableFeatureContainer() {
    val classList = remember { mutableStateListOf(*getInitialClasses().toTypedArray()) }
    var showAddScreen by remember { mutableStateOf(false) }

    if (showAddScreen) {
        AddClassScreen(
            onBackToTimetable = { showAddScreen = false },
            onSaveClass = { newClass ->
                classList.add(newClass)
                showAddScreen = false
            }
        )
    } else {
        TimetableScreen(
            classes = classList,
            onBackClick = { },
            onAddNewClassClick = { showAddScreen = true },
            onDeleteClass = { classId ->
                classList.removeIf { it.id == classId }
            }
        )
    }
}