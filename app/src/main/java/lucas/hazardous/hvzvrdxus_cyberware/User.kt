package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class User(val id: Int, val name: String, val surname: String)

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
            Text(user.id.toString())
            Text(user.name)
            Text(user.surname)

            OutlinedButton(onClick = {}) {
                Text("Remove")
            }
        }
    }
}
