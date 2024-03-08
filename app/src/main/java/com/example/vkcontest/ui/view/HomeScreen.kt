package com.example.vkcontest.ui.view

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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkcontest.ui.viewModel.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun homeScreen(productViewModel: ProductViewModel) {
    val productList by productViewModel.products.collectAsState()
    val categoryList by productViewModel.listOfCategories.collectAsState()
    var searchExtended by remember {
        mutableStateOf(false)
    }
    var searchText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ) {


        LazyRow(modifier = Modifier) {
            if (productViewModel.getCategory().value != "1" || searchExtended)
                item {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            productViewModel.selectCategory()
                            searchExtended = false
                        }) {
                        Text(text = "close")
                    }
                }
            if (!searchExtended && productViewModel.getCategory().value == "1")
                item {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = { searchExtended = true }) {
                        Text(text = "Search")
                    }
                }
            items(categoryList) {
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = { productViewModel.selectCategory(it) }) {
                    Text(text = it)
                }
            }
        }
        if (searchExtended)
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it
                        productViewModel.search(searchText) },
                    modifier = Modifier.fillMaxSize()
                    )
            }


        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .weight(1f),
        ) {

            items(productList.products) {
                productCard(it)
            }
        }
        if (productList.total > 20)
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),

                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    productViewModel.prevPage()
                }) {
                    Text(text = "Prev")
                }
                Text(text = productViewModel.getCurrentPageNum())
                Button(onClick = {
                    productViewModel.nextPage()
                }) {
                    Text(text = "Next")
                }
            }
    }
}





