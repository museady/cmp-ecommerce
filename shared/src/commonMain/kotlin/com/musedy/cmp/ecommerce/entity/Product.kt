package com.musedy.cmp.ecommerce.core.entitiy.entity

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

data class DeviceImages(
    val mobile: String,
    val tablet: String,
    val desktop: String
)

data class Include(
    val quantity: Int,
    val item: String
)

data class Gallery(
    val first: DeviceImages,
    val second: DeviceImages,
    val third: DeviceImages
)

data class OtherProduct(
    val slug: String,
    val name: String,
    val image: DeviceImages
)