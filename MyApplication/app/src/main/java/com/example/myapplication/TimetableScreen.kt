package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimetableScreen(
    classes: List<ClassItem>,
    onBackClick: () -> Unit = {},
    onAddNewClassClick: () -> Unit,
    onDeleteClass: (String) -> Unit
) {
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    val fullDayNames = mapOf(
        "Mon" to "Monday",
        "Tue" to "Tuesday",
        "Wed" to "Wednesday",
        "Thu" to "Thursday",
        "Fri" to "Friday"
    )
    var selectedDay by remember { mutableStateOf("Mon") }

    val filteredClasses = classes.filter { it.day == selectedDay }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF6B4EFF),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Universiti Malaya",
                    color = Color(0xFF6B4EFF),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }

            Surface(
                color = Color(0xFFEDE7F6),
                shape = CircleShape
            ) {
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

        Text(
            text = "Timetable",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF141414)
        )
        Text(
            text = "Organize your weekly lectures and labs.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            days.forEach { day ->
                val isSelected = day == selectedDay
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (isSelected) Color(0xFF141414) else Color(0xFFEEEEEE))
                        .clickable { selectedDay = day }
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day,
                        color = if (isSelected) Color.White else Color.DarkGray,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${fullDayNames[selectedDay]}'s classes",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF141414)
            )
            Text(
                text = "${filteredClasses.size} classes",
                fontSize = 13.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (filteredClasses.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No classes scheduled for ${fullDayNames[selectedDay]}.", color = Color.Gray)
                    }
                }
            } else {
                items(filteredClasses) { item ->
                    ClassCard(item = item, onDelete = { onDeleteClass(item.id) })
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onAddNewClassClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF141414))
        ) {
            Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Add New Class",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun ClassCard(item: ClassItem, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = item.classType.cardBgColor)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${item.startTime} - ${item.endTime} • ${item.classType.label}",
                    fontSize = 12.sp,
                    color = Color(0xFF333333)
                )

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Class",
                        tint = Color(0xFFD32F2F) // Warna merah lembut
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.courseNameAndCode,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF141414)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location",
                    tint = Color(0xFF333333),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = item.venue,
                    fontSize = 13.sp,
                    color = Color(0xFF333333)
                )
            }
        }
    }
}