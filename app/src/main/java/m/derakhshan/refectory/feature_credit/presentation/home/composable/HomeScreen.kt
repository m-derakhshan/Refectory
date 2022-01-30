package m.derakhshan.refectory.feature_credit.presentation.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Home Screen")
        }
    }

}