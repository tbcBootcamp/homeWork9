package com.example.homwork9

data class FilterItem(
    val img: Int,
    val filterName: String,
    var bgColor: Int,
    var txtColor: Int,
    var isActive: Boolean = false
)
