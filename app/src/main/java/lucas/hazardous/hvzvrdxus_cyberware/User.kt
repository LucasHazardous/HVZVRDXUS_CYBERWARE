package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import lucas.hazardous.hvzvrdxus_cyberware.api.ApiRequests
import lucas.hazardous.hvzvrdxus_cyberware.ui.components.ListElementSurface

data class User(val id: Int, val name: String, val surname: String, val role: Int)

data class UserToAdd(val name: String, val surname: String, val email: String, val password: String)

var userList = mutableStateOf<List<User>>(emptyList())

@Composable
fun UsersScreen(goBackToOptions: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        LazyColumn {
            item {
                Row {
                    OutlinedButton(onClick = goBackToOptions, colors = ButtonDefaults.buttonColors(Color.Cyan), modifier = Modifier.padding(8.dp)) {
                        Text("Back")
                    }
                    CreateUserSection()
                }
            }
            items(items = userList.value) { user ->
                UserElement(user)
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

    OutlinedButton(
        onClick = { menuOpen = !menuOpen },
        colors = ButtonDefaults.buttonColors(Color.Cyan),
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Create")
    }
    if (menuOpen) {
        Column(modifier = Modifier.padding(8.dp)) {
            TextField(value = name, onValueChange = {v -> name = v}, label = { Text("Name") })
            TextField(value = surname, onValueChange = {v -> surname = v}, label = { Text("Surname") })
            TextField(value = email, onValueChange = {v -> email = v}, label = { Text("Email") })
            TextField(value = password, onValueChange = {v -> password = v}, label = { Text("Password") })
        }
    } else if(!menuOpen && name.trim() != "" && surname.trim() != "" && email.trim() != "" && password.trim() != "") {
        ApiRequests.addUser(UserToAdd(name, surname, email, password))
    }
}

@Composable
fun UserElement(user: User) {
    var btnEnabled by remember { mutableStateOf(true) }
    var editing by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf(user.name) }
    var surname by remember { mutableStateOf(user.surname) }
    var role by remember { mutableStateOf(user.role) }

    ListElementSurface {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Id: " + user.id, modifier = maxTextLengthModifier)
            if (!editing) {
                Text("Name: " + user.name, modifier = maxTextLengthModifier)
                Text("Surname: " + user.surname, modifier = maxTextLengthModifier)
                Text("Role: " + user.role, modifier = maxTextLengthModifier)
            } else {
                TextField(
                    value = name,
                    onValueChange = { v -> name = v },
                    label = { Text("Name") },
                    modifier = maxTextLengthModifier
                )
                TextField(
                    value = surname,
                    onValueChange = { v -> surname = v },
                    label = { Text("Surname") },
                    modifier = maxTextLengthModifier
                )
                TextField(value = role.toString(), onValueChange = { v ->
                    role = try {
                        v.toInt()
                    } catch (nfe: NumberFormatException) {
                        0
                    }
                }, label = { Text("Role") }, modifier = maxTextLengthModifier)
            }
        }
        OutlinedButton(
            onClick = {
                if (editing && name.trim() != "" && surname.trim() != "") {
                    val newUser = User(user.id, name, surname, role)
                    ApiRequests.patchUser(user.id, newUser)
                }
                editing = !editing
            },
            enabled = btnEnabled,
            colors = ButtonDefaults.buttonColors(Color.Cyan)
        ) {
            Text("Edit")
        }
        OutlinedButton(
            onClick = { ApiRequests.deleteUser(user.id); btnEnabled = false },
            enabled = btnEnabled,
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Remove")
        }
    }
}
