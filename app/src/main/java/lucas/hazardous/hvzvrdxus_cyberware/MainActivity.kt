package lucas.hazardous.hvzvrdxus_cyberware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import lucas.hazardous.hvzvrdxus_cyberware.ui.theme.HVZVRDXUS_CYBERWARETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMain()
        }
    }
}

@Composable
fun AppMain() {
    var appState by rememberSaveable { mutableStateOf('o') }
    val goBackToOptions = { appState = 'o' }

    HVZVRDXUS_CYBERWARETheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)) {
            if (appState == 'o') {
                OptionsScreen(listOf(Option("Products") { appState = 'p' }, Option("Users") { appState = 'u' }))
            } else if (appState == 'u') {
                ApiRequests.getData()
                UsersScreen(goBackToOptions)
            } else if(appState == 'p') {
                ProductsScreen(goBackToOptions)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HVZVRDXUS_CYBERWARETheme {
        AppMain()
    }
}