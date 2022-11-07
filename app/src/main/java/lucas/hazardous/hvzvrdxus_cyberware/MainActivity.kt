package lucas.hazardous.hvzvrdxus_cyberware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
        if (appState == 'o') {
            OptionsScreen(listOf(Option("Products") { appState = 'p' }, Option("Users") { appState = 'u' }))
        } else if (appState == 'u') {
            ApiRequests.getData()
            UsersScreen(goBackToOptions)
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