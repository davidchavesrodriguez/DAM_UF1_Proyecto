package com.example.gblorchos.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gblorchos.R
import com.example.gblorchos.ui.theme.GBLorchosTheme

@Composable
fun DrawerContent(navController: NavController) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp).background(colorResource(id = R.color.lorchos))
            .fillMaxHeight()
            .padding(16.dp)

    ) {
        Button(onClick = {
            navController.navigate("principal")
        }) {
            Text(stringResource(R.string.main))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("xogadores") }) {
            Text(stringResource(R.string.players))
        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = { navController.navigate("eventos") }) {
//            Text(stringResource(R.string.events))
//         }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("resultados") }) {
            Text(stringResource(R.string.results))
        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = { navController.navigate("tenda") }) {
//            Text(stringResource(R.string.shop))
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawerContent() {
    GBLorchosTheme {
        DrawerContent(navController = rememberNavController())
    }
}