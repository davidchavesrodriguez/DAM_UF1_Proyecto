package com.example.gblorchos.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gblorchos.R
import com.example.gblorchos.ui.theme.GBLorchosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(onMenuClick: () -> Unit, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.menu),
                )
            }
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.escudo),
                contentDescription = stringResource(R.string.shield_description),
                modifier = Modifier
                    .size(56.dp)
                    .padding(8.dp)
            )
        },
        modifier = modifier
            .background(colorResource(id = R.color.lorchos))
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAppTopBar() {
    GBLorchosTheme {
        AppTopBar(onMenuClick = {})
    }
}