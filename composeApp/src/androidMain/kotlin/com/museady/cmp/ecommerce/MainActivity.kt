package com.museady.cmp.ecommerce

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Napier.base(DebugAntilog())

        setContent {
            ChangeStatusBarColor()
            App()
        }
    }
}

@Composable
private fun ChangeStatusBarColor(
) {
    val view = LocalView.current
    val window = (view.context as? Activity)?.window

    LaunchedEffect(Unit) {
        window?.statusBarColor = AppColors.NeutralDark.toArgb()
    }
}