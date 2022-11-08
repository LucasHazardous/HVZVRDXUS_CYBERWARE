package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Product(val id: Int, val name: String, val price: Float, val description: String, val category: Int)

var productList = mutableStateOf(listOf(Product(1, "Sample", 2.20f, "This is a sample description.", 1),
    Product(2, "Samplee", 11.1f, "This is a cool description.", 2),
    Product(3, "Sampleee", 3.34f, "This is a description.", 3)))

@Composable
fun ProductsScreen(goBackToOptions: () -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        item {
            Row(modifier = Modifier.padding(8.dp)) {
                OutlinedButton(onClick = goBackToOptions, colors = ButtonDefaults.buttonColors(
                    Color.Cyan), modifier = Modifier.padding(8.dp)) {
                    Text("Back")
                }
            }
        }
        items(items = productList.value) { product ->
            ProductElement(product)
        }
    }
}

@Composable
fun ProductElement(product: Product) {
    Surface(
        color = Color.Yellow,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        border = BorderStroke(2.dp, if(product.category % 2 == 1) Color.Red else Color.Cyan)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(product.name + " [${product.id}]")
            Text(product.price.toString())
            Text(product.description)
        }
    }
}
