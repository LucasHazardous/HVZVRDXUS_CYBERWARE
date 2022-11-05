package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class User(val id: Int, val name: String, val surname: String)

@Composable
fun UsersScreen(users: List<User>, goBackToOptions: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(vertical = 24.dp)) {
            LazyColumn {
                item {
                    Surface(modifier = Modifier.padding(8.dp)) {
                        Row {
                            OutlinedButton(onClick = goBackToOptions) {
                                Text("Back")
                            }
                            CreateUserSection()
                        }
                    }
                }
                items(items = users) { user ->
                    UserElement(user)
                }
            }
        }
    }
}

@Composable
fun CreateUserSection() {
    var menuOpen by rememberSaveable { mutableStateOf(false) }

    OutlinedButton(onClick = { menuOpen = !menuOpen }) {
        Text("Create")
    }
    if (menuOpen) {
        Column(modifier = Modifier.padding(8.dp)) {
            TextField(value = "name", onValueChange = {})
            TextField(value = "surname", onValueChange = {})
            TextField(value = "email", onValueChange = {})
            TextField(value = "password", onValueChange = {})
        }
    }
}

@Composable
fun UserElement(user: User) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Id: " + user.id)
                Text("Name: " + user.name)
                Text("Surname: " + user.surname)
            }

            OutlinedButton(onClick = {}) {
                Text("Remove")
            }
        }
    }
}
