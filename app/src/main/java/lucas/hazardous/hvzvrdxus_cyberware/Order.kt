package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Order(val id: Int, val user_id: Int, val product_id: Int, val address: String, val sent: Boolean)

var orderList = mutableStateOf<List<Order>>(emptyList())

@Composable
fun OrdersScreen(goBackToOptions: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        LazyColumn {
            item {
                Row {
                    OutlinedButton(onClick = goBackToOptions, colors = ButtonDefaults.buttonColors(
                        Color.Cyan), modifier = Modifier.padding(8.dp)) {
                        Text("Back")
                    }
                    CreateUserSection()
                }
            }
            items(items = orderList.value) { order ->
                OrderElement(order)
            }
        }
    }
}

@Composable
fun OrderElement(order: Order) {
    Surface(
        color = Color.Yellow,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        border = BorderStroke(2.dp, Color.Cyan)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Id: " + order.id, modifier = maxTextLengthModifier)
                Text("User Id: " + order.user_id, modifier = maxTextLengthModifier)
                Text("Product Id: " + order.product_id, modifier = maxTextLengthModifier)
                Text("Address: " + order.address, modifier = maxTextLengthModifier)
            }
        }
    }
}
