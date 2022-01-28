package m.derakhshan.refectory.feature_authentication.presentation.authentication.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import m.derakhshan.refectory.R
import m.derakhshan.refectory.core.presentation.LoadingButton
import m.derakhshan.refectory.feature_authentication.presentation.authentication.AuthenticationEvent
import m.derakhshan.refectory.feature_authentication.presentation.authentication.AuthenticationViewModel
import m.derakhshan.refectory.ui.theme.instagramFont
import m.derakhshan.refectory.ui.theme.spacing


@Composable
fun AuthenticationScreen(
    viewModel: AuthenticationViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
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
                    fontFamily = instagramFont
                )
                OutlinedTextField(
                    value = state.taxCode,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Characters),
                    onValueChange = { string ->
                        viewModel.onEvent(
                            AuthenticationEvent.TaxCodeChanged(
                                string
                            )
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
                    isExpanded = true,
                    modifier = Modifier.padding(MaterialTheme.spacing.medium)
                ) {
                }
            }

            Version(version = state.version)
        }
    }
}