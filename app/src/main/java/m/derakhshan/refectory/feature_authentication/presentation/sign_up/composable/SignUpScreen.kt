package m.derakhshan.refectory.feature_authentication.presentation.sign_up.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import m.derakhshan.refectory.R
import m.derakhshan.refectory.core.presentation.LoadingButton
import m.derakhshan.refectory.feature_authentication.presentation.sign_up.SignUpEvent
import m.derakhshan.refectory.feature_authentication.presentation.sign_up.SignUpViewModel
import m.derakhshan.refectory.ui.theme.fancyFont
import m.derakhshan.refectory.ui.theme.spacing

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController
) {

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value

    Scaffold(scaffoldState = scaffoldState) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            Text(
                text = stringResource(id = R.string.join_us),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                fontFamily = fancyFont,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.name,
                onValueChange = { viewModel.onEvent(SignUpEvent.NameChanged(it)) },
                label = {
                    Text(text = stringResource(id = R.string.name))
                },
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.small
                    )
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = state.surname,
                onValueChange = { viewModel.onEvent(SignUpEvent.SurnameChanged(it)) },
                label = {
                    Text(text = stringResource(id = R.string.surname))
                },
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.small
                    )
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = state.phoneNumber,
                onValueChange = { viewModel.onEvent(SignUpEvent.PhoneChanged(it)) },
                label = {
                    Text(text = stringResource(id = R.string.phone))
                },
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.small
                    )
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(SignUpEvent.EmailChanged(it)) },
                label = {
                    Text(text = stringResource(id = R.string.email))
                },
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.small
                    )
                    .fillMaxWidth()
            )

            LoadingButton(
                buttonText = stringResource(id = R.string.sign_up),
                isExpanded = state.isSignUpExpanded,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .align(Alignment.CenterHorizontally)
            ) {
                viewModel.onEvent(SignUpEvent.SignUp)
            }
        }

    }

}