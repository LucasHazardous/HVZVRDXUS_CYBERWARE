package lucas.hazardous.hvzvrdxus_cyberware.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListElementSurface(content: @Composable () -> Unit) {
    Surface(
        color = Color.Yellow,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        border = BorderStroke(2.dp, Color.Red)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}