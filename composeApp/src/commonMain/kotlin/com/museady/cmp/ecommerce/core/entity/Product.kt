package com.museady.cmp.ecommerce.core.entity

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int = 0,
    val slug: String ="",
    val name: String="",
    val image: DeviceImages = DeviceImages(),
    val category: String = "",
    val categoryImage: DeviceImages = DeviceImages(),
    val new: Boolean = false,
    val price: Int = 0,
    val description: String = "",
    val features: String = "",
    val includes: List<Include> = emptyList(),
    val gallery: Gallery = Gallery(),
    val others: List<OtherProduct> = emptyList()
)


@Serializable
data class DeviceImages(
    val mobile: String = "",
    val tablet: String =""
)

@Serializable
data class Include(
    val quantity: Int = 0,
    val item: String = ""
)

@Serializable
data class Gallery(
    val first: DeviceImages = DeviceImages(),
    val second: DeviceImages = DeviceImages(),
    val third: DeviceImages = DeviceImages()
)

@Serializable
data class OtherProduct(
    val id: Int = 0,
    val slug: String = "",
    val name: String ="",
    val image: DeviceImages =DeviceImages()
)