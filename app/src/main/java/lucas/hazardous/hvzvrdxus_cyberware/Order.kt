package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlin.reflect.KProperty1

data class Order(val user_id: Int, val product_ids: List<Int>, val address: String)

var productsInCart: List<Product> = emptyList()

@Composable
fun OrderScreen(goBackToOptions: () -> Unit) {
    var userId by remember { mutableStateOf(0) }
    var address by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        LazyColumn {
            item {
                OutlinedButton(
                    onClick = goBackToOptions, colors = ButtonDefaults.buttonColors(
                        Color.Cyan
                    ), modifier = Modifier.padding(8.dp)
                ) {
                    Text("Back")
                }
                TextField(
                    value = userId.toString(),
                    onValueChange = { v ->
                        userId = try {
                            v.toInt()
                        } catch (nfe: NumberFormatException) {
                            0
                        }
                    },
                    label = { Text("User Id") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextField(
                    value = address,
                    onValueChange = { v -> address = v },
                    label = { Text("Address") })
            }
            items(items = productsInCart) { product ->
                CartElement(product)
            }
            item {
                OutlinedButton(onClick = {
                    ApiRequests.addOrder(Order(userId, productsInCart.listOfField(Product::id), address))
                }, colors = ButtonDefaults.buttonColors(
                    Color.Red), modifier = Modifier.padding(8.dp)) {
                    Text("Order")
                }
            }
        }
    }
}

inline fun <reified T, Y> List<T>.listOfField(property: KProperty1<T, Y>):List<Y> {
    val yy = ArrayList<Y>()
    this.forEach { t: T ->
        yy += property.get(t)
    }
    return yy
}

@Composable
fun CartElement(product: Product) {
    Surface(
        color = Color.Yellow,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        border = BorderStroke(2.dp, Color.Cyan)
    ) {
        Column(modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(product.name)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.image)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape),
                contentDescription = null
            )
            Text(product.price.toString())
            Text(product.description)
        }
    }
}