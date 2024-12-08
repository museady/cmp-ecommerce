package com.museady.cmp.ecommerce.core.entity

import ecommerce_cmp.composeapp.generated.resources.Res
import ecommerce_cmp.composeapp.generated.resources.cateogry_thumbnail_earphones
import ecommerce_cmp.composeapp.generated.resources.cateogry_thumbnail_headphones
import ecommerce_cmp.composeapp.generated.resources.cateogry_thumbnail_speakers
import ecommerce_cmp.composeapp.generated.resources.earphones
import ecommerce_cmp.composeapp.generated.resources.headphones
import ecommerce_cmp.composeapp.generated.resources.speakers
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Category(
    val nameStringRes: StringResource,
    val thumbnail: DrawableResource
){
    HEADPHONES(
        Res.string.headphones,
        Res.drawable.cateogry_thumbnail_headphones
    ),
    SPEAKRS(
        Res.string.speakers,
        Res.drawable.cateogry_thumbnail_speakers
    ),
    EARPHONES(
        Res.string.earphones,
        Res.drawable.cateogry_thumbnail_earphones
    )
}
