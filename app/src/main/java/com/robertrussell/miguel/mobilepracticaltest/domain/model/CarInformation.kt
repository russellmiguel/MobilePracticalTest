package com.robertrussell.miguel.mobilepracticaltest.domain.model

data class CarInformation(
    var id: Long = 0,
    var model: String?,
    var maker: String?,
    var marketPrice: Double,
    var customerPrice: Double,
    var rating: Int = 0,
    var cons: List<String>,
    var pros: List<String>
)
