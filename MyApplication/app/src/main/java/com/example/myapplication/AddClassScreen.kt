package com.example.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddClassScreen(
    onBackToTimetable: () -> Unit,
    onSaveClass: (ClassItem) -> Unit
) {
    var selectedType by remember { mutableStateOf(ClassType.LECTURE) }
    var courseName by remember { mutableStateOf("") }
    var selectedDayLabel by remember { mutableStateOf("Monday") }
    var expandedDayMenu by remember { mutableStateOf(false) }
    var startTime by remember { mutableStateOf("08:00 AM") }
    var endTime by remember { mutableStateOf("10:00 AM") }
    var venue by remember { mutableStateOf("Bilik Kuliah 1, FSKTM") }
    var isEditingVenue by remember { mutableStateOf(false) }

    val dayOptions = listOf(
        "Monday" to "Mon",
        "Tuesday" to "Tue",
        "Wednesday" to "Wed",
        "Thursday" to "Thu",
        "Friday" to "Fri"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onBackToTimetable() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF6B4EFF),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Timetable",
                    color = Color(0xFF6B4EFF),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }

            Surface(color = Color(0xFFEDE7F6), shape = CircleShape) {
                Text(
                    text = "DEMO",
                    color = Color(0xFF6B4EFF),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Add Class", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF141414))
        Text("Add course details to your semester schedule.", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(20.dp))

        // 1. Class Type Chips
        Text("Class type", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ClassType.entries.forEach { type ->
                val isSelected = type == selectedType
                Surface(
                    shape = CircleShape,
                    color = type.cardBgColor,
                    border = if (isSelected) BorderStroke(1.5.dp, type.accentColor) else null,
                    modifier = Modifier.clickable { selectedType = type }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = type.label,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) type.accentColor else Color.DarkGray
                        )
                        if (isSelected) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                tint = type.accentColor,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text("Course Name & Code", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = courseName,
            onValueChange = { courseName = it },
            placeholder = { Text("e.g. WIX1002 Data Structures", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text("Day", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expandedDayMenu = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.LightGray)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(selectedDayLabel, color = Color(0xFF141414), fontSize = 15.sp)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Gray)
                }
            }

            DropdownMenu(
                expanded = expandedDayMenu,
                onDismissRequest = { expandedDayMenu = false }
            ) {
                dayOptions.forEach { (fullName, _) ->
                    DropdownMenuItem(
                        text = { Text(fullName) },
                        onClick = {
                            selectedDayLabel = fullName
                            expandedDayMenu = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Start Time", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = startTime,
                    onValueChange = { startTime = it },
                    trailingIcon = { Icon(Icons.Outlined.AccessTime, contentDescription = null) },
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text("End Time", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = endTime,
                    onValueChange = { endTime = it },
                    trailingIcon = { Icon(Icons.Outlined.AccessTime, contentDescription = null) },
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Surface(
            color = Color(0xFFE2F3E7),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.LocationOn, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Venue / Location", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                    Surface(
                        color = Color.White,
                        shape = CircleShape,
                        modifier = Modifier.clickable { isEditingVenue = !isEditingVenue }
                    ) {
                        Text(
                            text = if (isEditingVenue) "Done" else "Edit",
                            color = Color(0xFF6B4EFF),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (isEditingVenue) {
                    OutlinedTextField(
                        value = venue,
                        onValueChange = { venue = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                } else {
                    Text(venue, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF141414))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val shortDay = dayOptions.first { it.first == selectedDayLabel }.second
                val finalCourse = courseName.ifBlank { "WIX1002 Data Structures" }
                onSaveClass(
                    ClassItem(
                        courseNameAndCode = finalCourse,
                        classType = selectedType,
                        day = shortDay,
                        startTime = startTime,
                        endTime = endTime,
                        venue = venue
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF141414))
        ) {
            Text(
                text = "↗ Save to Schedule",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

