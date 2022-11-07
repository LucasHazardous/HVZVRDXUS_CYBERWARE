package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue

data class User(val id: Int, val name: String, val surname: String)

data class UserToAdd(val name: String, val surname: String, val email: String, val password: String)

var userList = mutableStateOf<List<User>>(emptyList())

@Composable
fun UsersScreen(goBackToOptions: () -> Unit) {
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
                items(items = userList.value) { user ->
                    UserElement(user)
                }
            }
        }
    }
}

@Composable
fun CreateUserSection() {
    var menuOpen by rememberSaveable { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    OutlinedButton(onClick = { menuOpen = !menuOpen }) {
        Text("Create")
    }
    if (menuOpen) {
        Column(modifier = Modifier.padding(8.dp)) {
            TextField(value = name, onValueChange = {v -> name = v}, label = { Text("Name") })
            TextField(value = surname, onValueChange = {v -> surname = v}, label = { Text("Surname") })
            TextField(value = email, onValueChange = {v -> email = v}, label = { Text("Email") })
            TextField(value = password, onValueChange = {v -> password = v}, label = { Text("Password") })
        }
    } else if(!menuOpen && name != "" && surname != "" && email != "" && password != "") {
        ApiRequests.addUser(UserToAdd(name, surname, email, password))
    }
}

@Composable
fun UserElement(user: User) {
    var btnEnabled by remember { mutableStateOf(true) }
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

            OutlinedButton(onClick = { ApiRequests.deleteUser(user.id); btnEnabled = false }, enabled = btnEnabled) {
                Text("Remove")
            }
        }
    }
}
