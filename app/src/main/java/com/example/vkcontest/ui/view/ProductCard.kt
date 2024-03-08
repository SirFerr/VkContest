package com.example.vkcontest.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.vkcontest.data.model.Product


//@Preview
//@Composable
//fun productCardPreview() {
//    productCard(productViewModel = productViewModel)
//}

@Composable
fun productCard(product: Product? = null) {
    var imageURL by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var title by remember {
        mutableStateOf("")
    }
    imageURL = product?.thumbnail.toString()
    title = product?.title.toString()
    description = product?.description.toString()

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {

            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)

        ) {
            Image(
                painter = rememberAsyncImagePainter(imageURL),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(10.dp)),
//                contentScale = ContentScale.Fit

            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = title, modifier = Modifier, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = description, modifier = Modifier, fontSize = 14.sp)
        }
    }
}