package com.museady.cmp.ecommerce

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform