package com.museady.cmp.ecommerce.core.json

import com.museady.cmp.ecommerce.core.entity.Product
import ecommerce_cmp.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json

suspend fun loadData(): List<Product> {
    val readBytes = Res.readBytes("files/data.json")
    val jsonString = io.ktor.utils.io.core.String(readBytes)
    return Json.decodeFromString(jsonString)
}
