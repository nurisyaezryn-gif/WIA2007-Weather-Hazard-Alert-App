package com.example.myapplication

import androidx.compose.ui.graphics.Color

enum class ClassType(val label: String, val cardBgColor: Color, val accentColor: Color) {
    LECTURE("Lecture", Color(0xFFE7E2FA), Color(0xFF6B4EFF)),
    TUTORIAL("Tutorial", Color(0xFFE2F3E7), Color(0xFF2E7D32)),
    LAB("Lab", Color(0xFFF9EAE1), Color(0xFFD84315)),
    WORKSHOP("Workshop", Color(0xFFF8E1F4), Color(0xFFAD1457))
}

data class ClassItem(
    val id: String = java.util.UUID.randomUUID().toString(),
    val courseNameAndCode: String,
    val classType: ClassType,
    val day: String, // "Mon", "Tue", "Wed", "Thu", "Fri"
    val startTime: String,
    val endTime: String,
    val venue: String
)

fun getInitialClasses(): List<ClassItem> = listOf(
    ClassItem(
        courseNameAndCode = "WIX1002 Data Structures",
        classType = ClassType.LECTURE,
        day = "Mon",
        startTime = "08:00 AM",
        endTime = "10:00 AM",
        venue = "Bilik Kuliah 1, FSKTM"
    ),
    ClassItem(
        courseNameAndCode = "WIA1004 Networking Fundamentals",
        classType = ClassType.TUTORIAL,
        day = "Mon",
        startTime = "10:00 AM",
        endTime = "12:00 PM",
        venue = "Makmal Komputer 3"
    ),
    ClassItem(
        courseNameAndCode = "WIX1003 Computer Systems",
        classType = ClassType.LAB,
        day = "Mon",
        startTime = "02:00 PM",
        endTime = "04:00 PM",
        venue = "Dewan Kuliah F"
    )
)