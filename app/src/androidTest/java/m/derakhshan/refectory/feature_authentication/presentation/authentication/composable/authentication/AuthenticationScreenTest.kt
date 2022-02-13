package m.derakhshan.refectory.feature_authentication.presentation.authentication.composable.authentication


import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingPolicies
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import m.derakhshan.refectory.MainActivity
import m.derakhshan.refectory.R
import m.derakhshan.refectory.core.data.data_source.Setting
import m.derakhshan.refectory.core.di.AppModule
import m.derakhshan.refectory.core.domain.utils.TestingConstant
import m.derakhshan.refectory.core.domain.utils.TestingConstant.AUTHENTICATION_SCREEN_LOGIN_BUTTON
import m.derakhshan.refectory.core.domain.utils.TestingConstant.AUTHENTICATION_SCREEN_TAX_CODE_EDIT_TEXT
import m.derakhshan.refectory.feature_authentication.di.AuthenticationModule
import m.derakhshan.refectory.feature_authentication.presentation.AuthenticationNavGraph
import m.derakhshan.refectory.feature_authentication.presentation.authentication.composable.AuthenticationScreen
import m.derakhshan.refectory.feature_authentication.presentation.sign_up.composable.SignUpScreen
import m.derakhshan.refectory.feature_credit.presentation.HomeNavGraph
import m.derakhshan.refectory.feature_credit.presentation.home.composable.HomeScreen
import m.derakhshan.refectory.ui.theme.RefectoryTheme
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@HiltAndroidTest
@UninstallModules(AuthenticationModule::class, AppModule::class)
class AuthenticationScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    @Inject
    lateinit var setting: Setting

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            RefectoryTheme {
                NavHost(
                    navController = navController,
                    startDestination = AuthenticationNavGraph.AuthenticationScreen.route
                ) {
                    composable(AuthenticationNavGraph.AuthenticationScreen.route) {
                        AuthenticationScreen(navController = navController, setting = setting)
                    }
                    composable(AuthenticationNavGraph.SignUpScreen.route, arguments = listOf(
                        navArgument("tax_code") {
                            this.type = NavType.StringType
                            this.defaultValue = "ABCDEFGHIJKLMNOP"
                        }
                    )) {
                        SignUpScreen(navController = navController, setting = setting)
                    }
                    composable(HomeNavGraph.Route.route) {
                        HomeScreen(navController = navController)
                    }
                }
            }
        }
    }


    @Test
    fun checkTaxCodeIsShown() {
        composeRule.onNodeWithTag(testTag = AUTHENTICATION_SCREEN_LOGIN_BUTTON)
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun checkLoginButtonIsShown() {
        composeRule.onNodeWithTag(testTag = AUTHENTICATION_SCREEN_LOGIN_BUTTON)
            .assertIsDisplayed()
    }

    @Test
    fun checkTaxCodeEmpty_ShowSnackBar() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(testTag = AUTHENTICATION_SCREEN_TAX_CODE_EDIT_TEXT)
            .performTextInput("")
        composeRule.onNodeWithTag(AUTHENTICATION_SCREEN_LOGIN_BUTTON).performClick()
        composeRule.onNodeWithText(context.getString(R.string.login_error_empty_tax_code))
            .assertIsDisplayed()
    }

    @Test
    fun checkTaxCodeWrongLength_ShowSnackBar() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(testTag = AUTHENTICATION_SCREEN_TAX_CODE_EDIT_TEXT)
            .performTextInput("ABCDEF")
        composeRule.onNodeWithTag(AUTHENTICATION_SCREEN_LOGIN_BUTTON).performClick()
        composeRule.onNodeWithText(context.getString(R.string.login_error_wrong_tax_code_length))
            .assertIsDisplayed()
    }

    @Test
    fun checkMaxTaxCodeLength_16Character() {
        val taxCode = composeRule.onNodeWithTag(testTag = AUTHENTICATION_SCREEN_TAX_CODE_EDIT_TEXT)
        for (i in 'A'..'Z')
            taxCode.performTextInput(i.toString())
        for ((key,value) in taxCode.fetchSemanticsNode().config)
            if (key.name =="EditableText")
                assertEquals("ABCDEFGHIJKLMNOP",value.toString())

    }

    @Test
    fun checkGoingToOtherScreen_afterServerResponse() {
        val taxNode = composeRule.onNodeWithTag(testTag = AUTHENTICATION_SCREEN_TAX_CODE_EDIT_TEXT)
        taxNode.performTextInput("ABCDEFGHIJKLMNOP")
        composeRule.onNodeWithTag(testTag = AUTHENTICATION_SCREEN_LOGIN_BUTTON)
            .performClick()
        taxNode.assertDoesNotExist()
    }
}