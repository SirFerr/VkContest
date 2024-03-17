package com.example.vkcontest.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.vkcontest.data.model.Product
import com.example.vkcontest.ui.viewModel.ProductViewModel

@Composable
fun productScreen(id: Int, productViewModel: ProductViewModel) {
    var product by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(id) {
        product = productViewModel.getById(id)
    }

    if (product != null) {
        Column(
            Modifier

                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyRow(Modifier) {
                items(product!!.images) {
                    Image(
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = product!!.title)
                Text(text = product!!.brand)
            }


            Spacer(modifier = Modifier.padding(8.dp))
            Text(modifier = Modifier, text = product!!.description)

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "cost: "+product!!.price.toString())
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = product!!.rating.toString())
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null)
                }
            }
        }
    } else {
    }
}
