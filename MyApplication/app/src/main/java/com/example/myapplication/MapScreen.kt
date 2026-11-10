package com.example.myapplication

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

// Colours from your Figma notes
private val PageBackground = Color.White
private val TitleColor = Color(0xFF070705)      // title and black button
private val SubtitleColor = Color(0xFF74979D)   // check this one
private val MapColor = Color(0xFFE9EEE6)        // my estimate
private val HazardsFill = Color(0xFFFAE4FC)
private val HazardsText = Color(0xFF965E9A)
private val BusFill = Color(0xFFE9E5FA)
private val BusText = Color(0xFF7050BB)
private val NavGrey = Color(0xFF8A8A8A)

@Composable
fun MapScreen(onReportClick: () -> Unit = {}) {
    var selected by remember { mutableStateOf("All") }

    Column(
        Modifier
            .fillMaxSize()
            .background(PageBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Top header row: Location Pin + Universiti Malaya + DEMO badge
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // TODO: Replace location pin placeholder with R.drawable.location_pin when exported from Figma

                Image(
                    painter = painterResource(id = R.drawable.location_pin),
                    contentDescription = "Location Pin",
                    modifier = Modifier
                        .align(Alignment.Top)
                        .offset(x = (0).dp, y = 2.dp)
                        .size(20.dp)
                )
                Text(
                    text = "Universiti Malaya",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TitleColor
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFEEEEEE))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "DEMO",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }

        // Title and subtitle
        Column {
            Text("Campus map", fontSize = 39.sp, fontWeight = FontWeight.Bold, color = TitleColor)
            Text("Illustrative map · Sample reports, not verified", fontSize = 12.sp, color = SubtitleColor)
        }

        // Filter chips
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // TODO: Pass R.drawable.hazard_chip_icon and R.drawable.bus_chip_icon when exported from Figma
            MapChip("All", selected == "All", Color(0xFFF0F0F0), TitleColor) { selected = "All" }
            MapChip("Hazards", selected == "Hazards", HazardsFill, HazardsText) { selected = "Hazards" }
            MapChip("Bus stops", selected == "Bus stops", BusFill, BusText) { selected = "Bus stops" }
        }

        // Map area (takes all the leftover space)
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(MapColor)
        ) {
            // TODO: Add map_background Image here when R.drawable.map_background is exported from Figma

            Text("Map goes here", Modifier.align(Alignment.Center), fontSize = 14.sp, color = SubtitleColor)

            Box(
                Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.White)
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text("UM · Illustrative only", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = SubtitleColor)
            }

            // Map markers
            // 1. Flood marker
            Image(
                painter = painterResource(id = R.drawable.flood_map_marker),
                contentDescription = "Flooded road marker",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(x = (-40).dp, y = 50.dp)
                    .size(45.dp)
            )

            // 2. Blocked drain marker
            Image(
                painter = painterResource(id = R.drawable.blocked_drain_map_marker),
                contentDescription = "Blocked drain marker",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 40.dp, y = (-20).dp)
                    .size(45.dp)
            )

            // 3. Bus stop marker 1
            Image(
                painter = painterResource(id = R.drawable.bus_map_marker),
                contentDescription = "Bus stop marker 1",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = (-50).dp, y = (-30).dp)
                    .size(45.dp)
            )

            // 4. Bus stop marker 2
            Image(
                painter = painterResource(id = R.drawable.bus_map_marker),
                contentDescription = "Bus stop marker 2",
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = 70.dp, y = (-70).dp)
                    .size(45.dp)
            )
            //5. fallen tree marker
            Image(
                painter = painterResource(id = R.drawable.fallen_tree),
                contentDescription = "Fallen Tree Marker",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = 100.dp, y = (-50).dp)
                    .size(45.dp)
            )

            // Location button (Recenter map)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { /* Recenter map */ },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.recenter_map),
                    contentDescription = "Recenter map",
                    modifier = Modifier.size(45.dp)
                )
            }
        }

        // Legend
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(Modifier.fillMaxWidth()) {
                LegendItem("Flooded road", Color(0xFF6B8CC7), Modifier.weight(1f))
                LegendItem("Blocked drain", Color(0xFFC48B4A), Modifier.weight(1f))
            }
            Row(Modifier.fillMaxWidth()) {
                LegendItem("Fallen tree", Color(0xFF6B9B6E), Modifier.weight(1f))
                LegendItem("Bus stops", Color(0xFF9B7FD6), Modifier.weight(1f))
            }
        }

        // Report hazard button
        Button(
            onClick = onReportClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = TitleColor, contentColor = Color.White)
        ) {
            Text("+  Report hazard", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        // Bottom navigation bar
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE3E3E3), RoundedCornerShape(32.dp))
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NavItem(label = "Home", isSelected = false, iconResId = R.drawable.home_icon)
            NavItem(label = "AI Assistant", isSelected = false, iconResId = R.drawable.ai_assistant_icon)
            NavItem(label = "Map", isSelected = true, iconResId = R.drawable.map)
            NavItem(label = "Profile", isSelected = false, iconResId = R.drawable.profile)
        }
    }
}

@Composable
fun MapChip(
    label: String,
    isSelected: Boolean,
    fill: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(50))
            .background(if (isSelected) TitleColor else fill)
            .clickable { onClick() }
            .padding(horizontal = 18.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // TODO: Add Image painterResource(id = chipIconRes) when hazard_chip_icon / bus_chip_icon are available
            Text(
                label, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                color = if (isSelected) Color.White else textColor
            )
        }
    }
}

@Composable
fun LegendItem(label: String, color: Color, modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(10.dp).background(color, CircleShape))
        Spacer(Modifier.width(8.dp))
        Text(label, fontSize = 14.sp, color = TitleColor)
    }
}

@Composable
fun NavItem(label: String, isSelected: Boolean, iconResId: Int? = null) {
    val resolvedIconRes = iconResId ?: when (label) {
        "Home" -> R.drawable.home_icon
        "AI Assistant" -> R.drawable.ai_assistant_icon
        "Map" -> R.drawable.map
        "Profile" -> R.drawable.profile
        else -> null
    }
    val tint = if (isSelected) BusText else NavGrey
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { }
    ) {
        if (resolvedIconRes != null) {
            Image(
                painter = painterResource(id = resolvedIconRes),
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Box(
                Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(tint.copy(alpha = 0.25f))
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            label, fontSize = 11.sp, color = tint,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun MapScreenPreview() {
    MyApplicationTheme(darkTheme = false) { MapScreen() }
}