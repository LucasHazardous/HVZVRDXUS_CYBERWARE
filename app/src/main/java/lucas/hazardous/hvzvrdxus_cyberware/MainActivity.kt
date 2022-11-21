package lucas.hazardous.hvzvrdxus_cyberware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lucas.hazardous.hvzvrdxus_cyberware.ui.theme.HVZVRDXUS_CYBERWARETheme

val maxTextLengthModifier = Modifier.widthIn(0.dp, 200.dp)

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
            when (appState) {
                'o' -> {
                    OptionsScreen(listOf(Option("Products") { appState = 'p' }, Option("Users") { appState = 'u' }, Option("Shop") { appState = 's' }, Option("Order") { appState = 'r' }, Option("About") { appState = 'a' }))
                }
                'u' -> {
                    ApiRequests.loadUserList()
                    UsersScreen(goBackToOptions)
                }
                'p' -> {
                    ApiRequests.loadProductList()
                    ProductsScreen(goBackToOptions)
                }
                's' -> {
                    ApiRequests.loadProductList()
                    ShopScreen(goBackToOptions)
                }
                'r' -> {
                    OrderScreen(goBackToOptions)
                }
                'a' -> {
                    AboutScreen(goBackToOptions)
                }
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