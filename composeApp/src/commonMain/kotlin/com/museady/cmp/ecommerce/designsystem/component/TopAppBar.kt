@file:OptIn(ExperimentalMaterial3Api::class)

package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.museady.cmp.ecommerce.designsystem.icons.AppIcons
import com.museady.cmp.ecommerce.designsystem.theme.AppColors
import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.app_name
import ecommerce_cmp.composeapp.generated.resources.cd_top_appbar_back_button
import ecommerce_cmp.composeapp.generated.resources.cd_top_appbar_cart_button
import ecommerce_cmp.composeapp.generated.resources.cd_top_appbar_menu_button
import ecommerce_cmp.composeapp.generated.resources.ic_cart
import ecommerce_cmp.composeapp.generated.resources.ic_hamburger
import ecommerce_cmp.composeapp.generated.resources.logo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

// Composable for the top app bar component, adjusting for scroll state
@Composable
fun AudioPhileTopAppBar(
    isCompact: Boolean,
    openMenuDrawer: () -> Unit,
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    scrollState: ScrollState,
    scrollBehavior: TopAppBarScrollBehavior,
    categoryNameRes: StringResource? = null,
    isProductDetailsScreen: Boolean = false,
) {
    Column {
        // Switch between mobile and tablet app bars
        if (isCompact) {
            MobileTopAppbar(
                onMenuButtonClick = openMenuDrawer,
                onCartClick = onCartClick,
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick,
                isProductDetailsScreen = isProductDetailsScreen
            )
        } else {
            TabletTopAppbar(
                onMenuButtonClick = openMenuDrawer,
                onCartClick = onCartClick,
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick,
                isProductDetailsScreen = isProductDetailsScreen
            )
        }

        // Creates a transition state with an initial state where visible = false
        val visibleState = remember { MutableTransitionState(true) }

        LaunchedEffect(scrollBehavior) {
            snapshotFlow { scrollBehavior.state.collapsedFraction }
                .distinctUntilChanged()
                .collectLatest {
                    visibleState.targetState = it != 1f
                }
        }

        TopAppBarDivider(
            visibleState = visibleState,
            horizontalPadding = if (isCompact) 0.dp else 40.dp
        )

        // Category header if we're on a category screen
        if (categoryNameRes != null)
            CategoryHeader(
                title = stringResource(categoryNameRes),
                isCompact = isCompact,
                scrollState = scrollState
            )
    }
}

// Mobile version of the top app bar
@Composable
private fun MobileTopAppbar(
    isProductDetailsScreen: Boolean,
    onBackClick: () -> Unit,
    onMenuButtonClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    onCartClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            TopAppBarTitle()  // Display app name or logo
        },
        navigationIcon = {
            NavigationIcon(
                isProductDetailsScreen = isProductDetailsScreen,
                onMenuButtonClick = onMenuButtonClick,
                onBackClick = onBackClick,
                modifier = Modifier.padding(start = 6.dp),
            )
        },
        actions = {
            TopAppBarCartAction(
                onCartClick = onCartClick,
                modifier = Modifier.padding(end = 6.dp)
            )
        },
        colors = TopAppBarColors,  // Define custom colors
        scrollBehavior = scrollBehavior,
    )
}

// Tablet version of the top app bar
@Composable
private fun TabletTopAppbar(
    onMenuButtonClick: () -> Unit,
    onBackClick: () -> Unit,
    onCartClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    isProductDetailsScreen: Boolean,
) {
    TopAppBar(
        title = {
            TopAppBarTitle()  // Display app name or logo
        },
        navigationIcon = {
            NavigationIcon(
                onMenuButtonClick = onMenuButtonClick,
                modifier = Modifier.padding(start = 20.dp),
                isProductDetailsScreen = isProductDetailsScreen,
                onBackClick = onBackClick
            )
        },
        actions = {
            TopAppBarCartAction(
                onCartClick = onCartClick,
                modifier = Modifier.padding(end = 24.dp)
            )
        },
        colors = TopAppBarColors,  // Define custom colors
        expandedHeight = 86.dp,
        scrollBehavior = scrollBehavior
    )
}

// App logo or title for the top app bar
@Composable
private fun TopAppBarTitle() {
    Icon(
        painter = painterResource(Res.drawable.logo),
        contentDescription = stringResource(Res.string.app_name),
    )
}

// Navigation icon (hamburger menu) for the app bar
@Composable
private fun NavigationIcon(
    isProductDetailsScreen: Boolean,
    onBackClick: () -> Unit,
    onMenuButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        if (isProductDetailsScreen) {
            IconButton(onBackClick) {
                Icon(
                    imageVector = AppIcons.ArrowBack,
                    contentDescription = stringResource(Res.string.cd_top_appbar_back_button)
                )
            }
        } else {
            IconButton(onMenuButtonClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_hamburger),
                    contentDescription = stringResource(Res.string.cd_top_appbar_menu_button)
                )
            }
        }
    }
}

// Cart action icon for the top app bar
@Composable
private fun TopAppBarCartAction(
    onCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        IconButton(onCartClick) {
            Icon(
                painter = painterResource(Res.drawable.ic_cart),
                contentDescription = stringResource(Res.string.cd_top_appbar_cart_button)
            )
        }
    }
}

@Composable
fun TopAppBarDivider(
    visibleState: MutableTransitionState<Boolean>,
    horizontalPadding: Dp
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(AppColors.NeutralDark)
    ) {
        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            HorizontalDivider(
                Modifier.padding(horizontal = horizontalPadding),
                thickness = 1.dp,
                color = Color(0xff2d2a29)
            )
        }
    }
}

// Category header component with scroll-based padding animation
@Composable
fun CategoryHeader(
    title: String,
    scrollState: ScrollState,
    isCompact: Boolean,
    fullPadding: Dp = if (isCompact) 32.dp else 76.dp,
    minPadding: Dp = if (isCompact) 16.dp else 24.dp
) {
    // Track scroll direction
    val isScrollingUp = rememberScrollDirection(scrollState)

    // Determine target padding based on scroll direction
    val targetPadding = calculateTargetPadding(isScrollingUp, fullPadding, minPadding)

    // Animate padding change
    val animatedPadding by animateDpAsState(
        targetValue = targetPadding,
        label = "header-padding",
        animationSpec = spring(
            stiffness = Spring.StiffnessMediumLow,
            dampingRatio = Spring.DampingRatioLowBouncy
        ),
    )

    // Display the header with animated padding
    // Select text style based on compact state
    val textStyle = with(MaterialTheme.typography) {
        if (isCompact) headlineLarge else displayMedium.copy(letterSpacing = 1.43.sp)
    }

    // Render the text inside a box with dynamic padding
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.NeutralDark),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = textStyle,
            color = AppColors.PureWhite,
            modifier = Modifier.padding(vertical = animatedPadding)
        )
    }
}

@Composable
private fun rememberScrollDirection(scrollState: ScrollState): State<Boolean?> {
    // Initialize a mutable state to track the scroll direction, starting with null
    // This means the scroll direction has not been determined yet
    val isScrollingUp = remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(scrollState) {
        var lastScrollValue = scrollState.value

        // Listen for changes in the scroll state
        snapshotFlow { scrollState.value }
            .distinctUntilChanged()  // Emit updates only when scroll position changes
            .collectLatest { currentScrollValue ->
                // Check if the scroll direction has changed (scrolling up or down)
                if (currentScrollValue != lastScrollValue) {
                    // Determine if scrolling up (current position < last position)
                    val newDirection = currentScrollValue < lastScrollValue
                    // Update the scroll direction if it differs from the previous value
                    if (isScrollingUp.value != newDirection) {
                        isScrollingUp.value = newDirection
                    }
                }
                lastScrollValue = currentScrollValue
            }
    }

    // Return the current scroll direction state
    return isScrollingUp
}

private fun calculateTargetPadding(
    isScrollingUp: State<Boolean?>,
    fullPadding: Dp,
    minPadding: Dp
): Dp {
    // Return the padding based on the scroll direction
    // If the scroll direction is still null (initial state), assume the header is fully expanded (fullPadding)
    return when (isScrollingUp.value) {
        true -> fullPadding  // If scrolling up, expand the header
        false -> minPadding  // If scrolling down, collapse the header
        null -> fullPadding  // If the scroll direction is unknown (before any scroll), assume full padding
    }
}

// Custom colors for the top app bar
private val TopAppBarColors
    @Composable get() = TopAppBarDefaults.topAppBarColors().copy(
        containerColor = AppColors.NeutralDark,
        titleContentColor = AppColors.PureWhite,
        actionIconContentColor = Color.White,
        navigationIconContentColor = Color.White,
        scrolledContainerColor = AppColors.NeutralDark
    )