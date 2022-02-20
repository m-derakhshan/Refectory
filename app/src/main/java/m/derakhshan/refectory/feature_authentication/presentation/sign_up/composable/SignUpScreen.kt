package m.derakhshan.refectory.feature_authentication.presentation.sign_up.composable


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import m.derakhshan.refectory.R
import m.derakhshan.refectory.core.data.data_source.Setting
import m.derakhshan.refectory.core.domain.utils.TestingConstant
import m.derakhshan.refectory.core.presentation.BackSwipeGesture
import m.derakhshan.refectory.core.presentation.LoadingButton
import m.derakhshan.refectory.feature_authentication.presentation.AuthenticationNavGraph
import m.derakhshan.refectory.feature_authentication.presentation.sign_up.SignUpEvent
import m.derakhshan.refectory.feature_authentication.presentation.sign_up.SignUpViewModel
import m.derakhshan.refectory.feature_credit.presentation.HomeNavGraph
import m.derakhshan.refectory.ui.theme.fancyFont
import m.derakhshan.refectory.ui.theme.spacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavController,
    setting: Setting
) {

    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val verticalScroll = rememberScrollState()
    var offset by remember {
        mutableStateOf(0f)
    }

    LaunchedEffect(true) {
        viewModel.snackBar.collectLatest { snackBar ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = snackBar.message
            )
        }
    }

    LaunchedEffect(true) {
        viewModel.navigate.collectLatest { navigate ->
            if (navigate.navigateToHomeScreen)
                navController.navigate(HomeNavGraph.Route.route) {
                    popUpTo(AuthenticationNavGraph.Route.route) {
                        inclusive = true
                    }
                }.also {
                    setting.isUserLoggedIn = true
                }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.draggable(orientation = Orientation.Horizontal,
            state = DraggableState { delta ->
                offset += (delta * 0.2f)
            },
            onDragStopped = {
                if (offset > 90)
                    navController.navigateUp()
                offset = 0f
            }
        )
    ) { innerPadding ->

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
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .shadow(5.dp, CircleShape)
                        .background(MaterialTheme.colors.secondary)
                        .clip(CircleShape)
                        .padding(MaterialTheme.spacing.extraSmall),
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.default_avatar),
                        contentDescription = "default avatar",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                IconButton(
                    onClick = { viewModel.onEvent(SignUpEvent.AddImageClicked) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(
                            MaterialTheme.colors.secondary,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = "add image",
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
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
                    .fillMaxWidth()
                    .testTag(TestingConstant.SIGNUP_SCREEN_TAX_CODE_EDIT_TEXT),
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
                    .fillMaxWidth()
                    .testTag(TestingConstant.SIGNUP_SCREEN_NAME_EDIT_TEXT),
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
                    .fillMaxWidth()
                    .testTag(TestingConstant.SIGNUP_SCREEN_SURNAME_EDIT_TEXT),
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
                    .fillMaxWidth()
                    .testTag(TestingConstant.SIGNUP_SCREEN_PHONE_EDIT_TEXT),
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
                    .fillMaxWidth()
                    .testTag(TestingConstant.SIGNUP_SCREEN_EMAIL_EDIT_TEXT),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                maxLines = 1
            )
            LoadingButton(
                buttonText = stringResource(id = R.string.sign_up),
                isExpanded = state.isSignUpExpanded,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .align(Alignment.CenterHorizontally)
                    .testTag(TestingConstant.SIGNUP_SCREEN_SIGNUP_BUTTON)
            ) {
                viewModel.onEvent(SignUpEvent.SignUp)
            }
        }

        IconButton(
            onClick = { navController.navigateUp() }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }
        BackSwipeGesture(offset = offset)
    }
}