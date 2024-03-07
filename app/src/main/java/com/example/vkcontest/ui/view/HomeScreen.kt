@file:OptIn(ExperimentalFoundationApi::class)

package com.example.vkcontest.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkcontest.ui.viewModel.ProductViewModel


@Composable
fun homeScreen(productViewModel: ProductViewModel) {
    val productList by productViewModel.products.collectAsState()
    val categoryList by productViewModel.listOfCategories.collectAsState()


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween

    ) {

        LazyRow(modifier =Modifier.padding(8.dp)){
            item{
                Button(onClick = { productViewModel.selectCategory("") }) {
                     Text(text = "close")
                }
            }
            items(categoryList){
                Button(onClick = { productViewModel.selectCategory(it) }) {
                    Text(text = it)
                }
            }
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
        ) {

            items(productList.products) {
                productCard(it)
            }
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                productViewModel.prevPage()
            }) {
                Text(text = "Prev")
            }
            Text(text = "Total: ${productList.total}")
            Button(onClick = {
                productViewModel.nextPage()
            }) {
                Text(text = "Next")
            }
        }
    }
}





