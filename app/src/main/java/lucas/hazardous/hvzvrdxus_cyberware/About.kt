package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(goBackToOptions: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        OutlinedButton(
            onClick = goBackToOptions, colors = ButtonDefaults.buttonColors(
                Color.Cyan
            ), modifier = Modifier.padding(8.dp)
        ) {
            Text("Back")
        }
        Text("This is cool company selling cyberware.", modifier = Modifier.padding(8.dp))
    }
}