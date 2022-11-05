package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class Option(val text: String, val action: () -> Unit)

@Composable
fun OptionButton(option: Option) {
    Button(
        modifier = Modifier.padding(vertical = 8.dp),
        onClick = option.action
    ) {
        Text(option.text)
    }
}

@Composable
fun OptionsScreen(options: List<Option>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(items = options) { option ->
                OptionButton(option)
            }
        }
    }
}