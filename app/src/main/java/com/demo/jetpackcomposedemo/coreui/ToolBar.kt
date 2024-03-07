package com.demo.jetpackcomposedemo.coreui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.demo.jetpackcomposedemo.R

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbarScreen(navController: NavHostController, title: String, isBack: Boolean){

    TopAppBar(
        colors = topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                )
        },
        modifier = Modifier.background(color = Color.Black),
        navigationIcon = {
            if (isBack){
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            }
        }
    )
}