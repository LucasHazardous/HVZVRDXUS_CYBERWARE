package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.round

data class Product(val id: Int, val name: String, val price: Float, val description: String, val category: Int, val image: String)

data class ProductToAdd(val name: String, val price: Float, val description: String, val category: Int, val image: String)

var productList = mutableStateOf<List<Product>>(emptyList())

@Composable
fun ProductsScreen(goBackToOptions: () -> Unit) {
    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        LazyColumn {
            item {
                Row {
                    OutlinedButton(onClick = goBackToOptions, colors = ButtonDefaults.buttonColors(Color.Cyan), modifier = Modifier.padding(8.dp)) {
                        Text("Back")
                    }
                    CreateProductSection()
                }
            }
            items(items = productList.value) { product ->
                ProductElement(product)
            }
        }
    }
}

@Composable
fun CreateProductSection() {
    var menuOpen by rememberSaveable { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf(0f) }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(0) }
    var image by remember { mutableStateOf("") }

    OutlinedButton(
        onClick = { menuOpen = !menuOpen },
        colors = ButtonDefaults.buttonColors(Color.Cyan),
        modifier = Modifier.padding(8.dp)
    ) {
        Text("Create")
    }
    if (menuOpen) {
        Column(modifier = Modifier.padding(8.dp)) {
            TextField(value = name, onValueChange = { v -> name = v }, label = { Text("Name") })
            TextField(
                value = price.toString(),
                onValueChange = { v ->
                    price = try {
                        v.toFloat().round(2)
                    } catch (nfe: NumberFormatException) { 0f }
                },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
            TextField(
                value = description,
                onValueChange = { v -> description = v },
                label = { Text("Description") })
            TextField(
                value = category.toString(),
                onValueChange = { v -> category = try {
                    v.toInt()
                } catch (nfe: NumberFormatException) { 0 } },
                label = { Text("Category") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(value = image, onValueChange = { v -> image = v }, label = { Text("Image") })
        }
    } else if(!menuOpen && name != "" && price > 0f && description != "") {
        ApiRequests.addProduct(ProductToAdd(name, price, description, category, image))
    }
}

fun Float.round(decimals: Int): Float {
    var multiplier = 1
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

@Composable
fun ProductElement(product: Product) {
    var btnEnabled by remember { mutableStateOf(true) }
    Surface(
        color = Color.Yellow,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        border = BorderStroke(2.dp, Color.Red)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("Id: " + product.id)
                Text("Name: " + product.name)
                Text("Price: " + product.price.toString())
                Text("Description: " + product.description)
            }
            Spacer(Modifier.weight(1f))
            OutlinedButton(
                onClick = { ApiRequests.deleteProduct(product.id); btnEnabled = false },
                enabled = btnEnabled,
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("Remove")
            }
        }
    }
}
