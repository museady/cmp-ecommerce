package com.museady.cmp.ecommerce.core.entity

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val slug: String,
    val name: String,
    val image: DeviceImages,
    val category: String,
    val categoryImage: DeviceImages,
    val new: Boolean,
    val price: Int,
    val description: String,
    val features: String,
    val includes: List<Include>,
    val gallery: Gallery,
    val others: List<OtherProduct>
)

@Serializable
data class DeviceImages(
    val mobile: String,
    val tablet: String
)

@Serializable
data class Include(
    val quantity: Int,
    val item: String
)

@Serializable
data class Gallery(
    val first: DeviceImages,
    val second: DeviceImages,
    val third: DeviceImages
)

@Serializable
data class OtherProduct(
    val slug: String,
    val name: String,
    val image: DeviceImages
)