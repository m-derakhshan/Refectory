package m.derakhshan.refectory.feature_authentication.presentation.authentication.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import m.derakhshan.refectory.R
import m.derakhshan.refectory.core.data.data_source.Setting
import m.derakhshan.refectory.core.presentation.LoadingButton
import m.derakhshan.refectory.feature_authentication.presentation.AuthenticationNavGraph
import m.derakhshan.refectory.feature_authentication.presentation.authentication.AuthenticationEvent
import m.derakhshan.refectory.feature_authentication.presentation.authentication.AuthenticationViewModel
import m.derakhshan.refectory.feature_credit.presentation.HomeNavGraph
import m.derakhshan.refectory.ui.theme.fancyFont
import m.derakhshan.refectory.ui.theme.spacing

@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    navController: NavController,
    setting: Setting
) {

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val verticalScroll = rememberScrollState()

    LaunchedEffect(key1 = state.taxCode, block = {
        viewModel.navigate.collectLatest { navigate ->
            if (navigate.navigateToHomeScreen)
                navController.navigate(HomeNavGraph.Route.route) {
                    popUpTo(AuthenticationNavGraph.Route.route) {
                        inclusive = true
                    }
                }.also {
                    setting.isUserLoggedIn = true
                }
            else if (navigate.navigateToSignUpScreen) {
                navController.navigate(AuthenticationNavGraph.SignUpScreen.route + "/tax_code=${state.taxCode}")
            }
        }
    })

    LaunchedEffect(key1 = true, block = {
        viewModel.snackBar.collectLatest { snackBar ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = snackBar.message
            )
        }
    })


    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(verticalScroll),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column {
                LottieBanner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                Text(
                    text = stringResource(id = R.string.welcome),
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = MaterialTheme.spacing.small),
                    fontFamily = fancyFont
                )
                OutlinedTextField(
                    value = state.taxCode,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
                    onValueChange = { string ->
                        viewModel.onEvent(
                            AuthenticationEvent.TaxCodeChanged(string)
                        )
                    },
                    label = {
                        Text(text = stringResource(id = R.string.tax_code))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = MaterialTheme.spacing.small,
                            horizontal = MaterialTheme.spacing.medium
                        )
                )
                LoadingButton(
                    buttonText = stringResource(id = R.string.login),
                    isExpanded = state.isLoginExpanded,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.medium)
                        .align(Alignment.CenterHorizontally)
                ) {
                    viewModel.onEvent(AuthenticationEvent.Login)
                }
            }
            Version(version = state.version)
        }
    }
}