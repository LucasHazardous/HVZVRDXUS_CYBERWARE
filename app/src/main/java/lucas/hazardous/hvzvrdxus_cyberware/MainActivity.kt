package lucas.hazardous.hvzvrdxus_cyberware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import lucas.hazardous.hvzvrdxus_cyberware.ui.theme.HVZVRDXUS_CYBERWARETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMain()
        }
    }
}

class User (val id: Int, val name: String, val surname: String)

@Composable
fun AppMain() {
    var appState by rememberSaveable{mutableStateOf('o')}

    HVZVRDXUS_CYBERWARETheme {
        if(appState == 'o') {
            OptionsScreen(listOf({ appState = 'p' }, { appState = 'u' }))
        } else if(appState == 'u') {
            UsersScreen(listOf(User(0, "Lucvs", "Hvzv3dxus"), User(1, "Jxhn", "Sm1th")))
        }
    }
}

@Composable
fun OptionButton(onContinueClicked : () -> Unit) {
    Button(
        modifier = Modifier.padding(vertical = 8.dp),
        onClick = onContinueClicked
    ) {
        Text("Proceed")
    }
}

@Composable
fun OptionsScreen(buttonActions: List<() -> Unit>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(items = buttonActions) { buttonAction ->
                OptionButton (buttonAction)
            }
        }
    }
}

@Composable
fun UsersScreen(users: List<User>) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            LazyColumn {
                items(items = users) { user ->
                    UserElement(user)
                }
            }
        }
    }
}

@Composable
fun UserElement(user: User) {
    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(modifier = Modifier.padding(24.dp).fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Text(user.id.toString())
            Text(user.name)
            Text(user.surname)

            OutlinedButton(onClick = {}) {
                Text("Remove")
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