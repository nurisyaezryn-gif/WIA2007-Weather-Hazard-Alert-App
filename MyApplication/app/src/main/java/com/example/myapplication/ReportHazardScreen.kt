package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

// Colours: the first ones come from your notes, the fills are my estimates from the screenshot
private val PageBg = Color.White
private val Ink = Color(0xFF070705)
private val Grey = Color(0xFF74979D)
private val Purple = Color(0xFF7050BB)
private val LavenderFill = Color(0xFFE9E5FA)
private val PinkFill = Color(0xFFFAE4FC)
private val PeachFill = Color(0xFFF5E6DF)
private val GreenFill = Color(0xFFE3F0E1)

@Composable
fun ReportHazardScreen(onBack: () -> Unit = {}, onSubmit: () -> Unit = {}) {
    var category by remember { mutableStateOf("Flooded road") }
    // Sample text so the preview matches Figma. Change it to "" when the real app is built.
    var description by remember {
        mutableStateOf("Water is pooling beside the library kerb after rain. The walkway is partly covered.")
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(PageBg)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Scrollable middle part
        Column(
            Modifier.weight(1f).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column {
                Text("Report hazard", fontSize = 39.sp, fontWeight = FontWeight.Bold, color = Ink)
                Text("A small report. A safer campus.", fontSize = 12.sp, color = Grey)
            }

            SectionLabel("Category")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CategoryCard("Flooded road", LavenderFill, category, Modifier.weight(1f)) { category = it }
                CategoryCard("Blocked drain", PeachFill, category, Modifier.weight(1f)) { category = it }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                CategoryCard("Fallen tree", GreenFill, category, Modifier.weight(1f)) { category = it }
                CategoryCard("Other", PinkFill, category, Modifier.weight(1f)) { category = it }
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                SectionLabel("Photo")
                Text("Optional", fontSize = 12.sp, color = Grey)
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(LavenderFill)
                    .border(1.5.dp, Purple.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                    .clickable { },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Take or upload a photo", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Ink)
                Text("JPG or PNG · Up to 10 MB", fontSize = 11.sp, color = Grey)
            }

            SectionLabel("Location")
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(GreenFill)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(10.dp).background(Purple, CircleShape)) // icon placeholder
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Near UM Library", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Ink)
                    Text("Auto-filled · Sample location", fontSize = 11.sp, color = Grey)
                }
                Text("Edit", Modifier.clickable { }, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Purple)
            }

            SectionLabel("Short description")
            OutlinedTextField(
                value = description,
                onValueChange = { if (it.length <= 240) description = it },
                modifier = Modifier.fillMaxWidth().height(140.dp),
                shape = RoundedCornerShape(20.dp),
                supportingText = {
                    Text(
                        "${description.length} / 240",
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End, fontSize = 11.sp, color = Grey
                    )
                }
            )

            Row(verticalAlignment = Alignment.Top) {
                Box(Modifier.padding(top = 3.dp).size(10.dp).border(1.dp, Grey, CircleShape)) // icon placeholder
                Spacer(Modifier.width(8.dp))
                Text(
                    "Demo only. No report will be sent. In immediate danger, contact campus security.",
                    fontSize = 11.sp, color = Grey
                )
            }
        }

        // Submit button
        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Ink, contentColor = Color.White)
        ) {
            Text("Submit report", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        // Bottom navigation bar (NavItem comes from MapScreen.kt)
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE3E3E3), RoundedCornerShape(32.dp))
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NavItem("Home", false)
            NavItem("AI Assistant", false)
            NavItem("Map", true)
            NavItem("Profile", false)
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(text, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Ink)
}

@Composable
private fun CategoryCard(
    label: String,
    fill: Color,
    selected: String,
    modifier: Modifier = Modifier,
    onPick: (String) -> Unit
) {
    val isSelected = selected == label
    Box(
        modifier
            .clip(RoundedCornerShape(20.dp))
            .background(fill)
            .then(if (isSelected) Modifier.border(2.dp, Purple, RoundedCornerShape(20.dp)) else Modifier)
            .clickable { onPick(label) }
            .padding(16.dp)
    ) {
        Text(
            if (isSelected) "$label ✓" else label,
            fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = Ink
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun ReportHazardScreenPreview() {
    MyApplicationTheme(darkTheme = false) { ReportHazardScreen() }
}