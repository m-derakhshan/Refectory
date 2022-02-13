package m.derakhshan.refectory.feature_authentication.presentation.sign_up.composable

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import m.derakhshan.refectory.MainActivity
import m.derakhshan.refectory.core.data.data_source.Setting
import m.derakhshan.refectory.core.di.AppModule
import m.derakhshan.refectory.core.domain.utils.TestingConstant
import m.derakhshan.refectory.feature_authentication.di.AuthenticationModule
import m.derakhshan.refectory.feature_authentication.presentation.AuthenticationNavGraph
import m.derakhshan.refectory.ui.theme.RefectoryTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@HiltAndroidTest
@UninstallModules(AuthenticationModule::class, AppModule::class)
class SignUpScreenTest {

    @Inject
    lateinit var setting: Setting

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            RefectoryTheme {
                NavHost(
                    navController = navController,
                    startDestination = AuthenticationNavGraph.SignUpScreen.route
                ) {
                    composable(AuthenticationNavGraph.SignUpScreen.route, arguments = listOf(
                        navArgument("tax_code") {
                            this.type = NavType.StringType
                            this.defaultValue = "ABCDEFGHIJKLMNOP"
                        }
                    )) {
                        SignUpScreen(navController = navController, setting = setting)
                    }
                }

            }
        }
    }

    @Test
    fun taxCodeIsShownAndHasUserTaxCode_Pass() {
        val taxCode =
            composeRule.onNodeWithTag(testTag = TestingConstant.SIGNUP_SCREEN_TAX_CODE_EDIT_TEXT)
        taxCode.assertIsDisplayed()
        taxCode.assertTextContains("ABCDEFGHIJKLMNOP")
    }

    @Test
    fun nameIsShown_Pass() {
        val node =
            composeRule.onNodeWithTag(testTag = TestingConstant.SIGNUP_SCREEN_NAME_EDIT_TEXT)
        node.assertIsDisplayed()
        node.assertIsEnabled()
    }


    @Test
    fun surnameIsShown_Pass() {
        val node =
            composeRule.onNodeWithTag(testTag = TestingConstant.SIGNUP_SCREEN_SURNAME_EDIT_TEXT)
        node.assertIsDisplayed()
        node.assertIsEnabled()
    }

    @Test
    fun phoneNumberIsShown_Pass() {
        val node =
            composeRule.onNodeWithTag(testTag = TestingConstant.SIGNUP_SCREEN_PHONE_EDIT_TEXT)
        node.assertIsDisplayed()
        node.assertIsEnabled()
    }

    @Test
    fun emailIsShown_Pass() {
        val node =
            composeRule.onNodeWithTag(testTag = TestingConstant.SIGNUP_SCREEN_EMAIL_EDIT_TEXT)
        node.assertIsDisplayed()
        node.assertIsEnabled()
    }
}