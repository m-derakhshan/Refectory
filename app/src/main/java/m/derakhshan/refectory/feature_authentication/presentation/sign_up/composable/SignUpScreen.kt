package m.derakhshan.refectory.feature_authentication.presentation.sign_up.composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    val verticalScroll = rememberScrollState()

    Scaffold(scaffoldState = scaffoldState) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(verticalScroll)
        ) {

            Text(
                text = stringResource(id = R.string.join_us),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                fontFamily = fancyFont,
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .shadow(5.dp, CircleShape)
                    .background(MaterialTheme.colors.secondary)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .padding(MaterialTheme.spacing.extraSmall)
            ) {

                Image(
                    painter = painterResource(id = R.mipmap.default_avatar),
                    contentDescription = "default avatar",
                    modifier = Modifier.fillMaxSize()
                )
            }

            OutlinedTextField(
                value = state.taxCode,
                onValueChange = {},
                label = {
                    Text(text = stringResource(id = R.string.tax_code))
                },
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.medium,
                        vertical = MaterialTheme.spacing.small
                    )
                    .fillMaxWidth(),
                enabled = false
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
                    .fillMaxWidth(),
                maxLines = 1
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
                    .fillMaxWidth(),
                maxLines = 1
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
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                maxLines = 1
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
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                maxLines = 1
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