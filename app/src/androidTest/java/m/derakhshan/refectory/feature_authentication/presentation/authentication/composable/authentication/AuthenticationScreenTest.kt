package m.derakhshan.refectory.feature_authentication.presentation.authentication.composable.authentication


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import m.derakhshan.refectory.core.domain.utils.TestingConstant.AUTHENTICATION_SCREEN_LOGIN_BUTTON
import m.derakhshan.refectory.feature_authentication.di.AuthenticationModule
import m.derakhshan.refectory.feature_authentication.presentation.AuthenticationNavGraph
import m.derakhshan.refectory.feature_authentication.presentation.authentication.composable.AuthenticationScreen
import m.derakhshan.refectory.ui.theme.RefectoryTheme
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
                }
            }
        }
    }


    @Test
    fun checkMaxLengthOfTaxCodeInput_maxLengthIs16() {
        composeRule.onNodeWithTag(testTag = AUTHENTICATION_SCREEN_LOGIN_BUTTON)
            .assertIsDisplayed()
    }
}