package com.museady.cmp.ecommerce.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.museady.cmp.ecommerce.designsystem.theme.AppShapes.DefaultCardShape
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

/**
 * A reusable composable for displaying a full-width image that is used throughout the app.
 *
 * This component simplifies the use of images with common styling, such as full-width scaling,
 * rounded corners, and optional content descriptions for accessibility.
 *
 * @param imageRes The drawable resource ID of the image to be displayed.
 * @param modifier [Modifier] to be applied to the image. Defaults to [Modifier.fillMaxWidth].
 * @param contentDescription A description of the image for accessibility purposes. Pass `null` if not required.
 * @param contentScale Defines the scaling of the image within the bounds. Defaults to [ContentScale.FillWidth].
 */
@Composable
fun FillWidthImage(
    imageRes: DrawableResource,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.FillWidth,
) {
    Image(
        painter = painterResource(imageRes),
        modifier = modifier
            .fillMaxWidth()
            .clip(DefaultCardShape),
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}