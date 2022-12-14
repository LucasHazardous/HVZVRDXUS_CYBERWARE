package lucas.hazardous.hvzvrdxus_cyberware

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ShopScreen(goBackToOptions: () -> Unit) {
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
            ShopProductElement(product)
        }
    }
}

@Composable
fun ShopProductElement(product: Product) {
    Surface(
        color = Color.Yellow,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        border = BorderStroke(2.dp, if(product.category % 2 == 1) Color.Red else Color.Cyan)
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
            OutlinedButton(
                onClick = { productsInCart = productsInCart + product },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("Cart")
            }
        }
    }
}
