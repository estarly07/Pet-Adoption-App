package com.estarly.petadoptionapp.utils

import java.text.DecimalFormat

fun Int.toYear() : String{
    return "${this/12}"
}
fun Double.format(): String = DecimalFormat.getNumberInstance().format(this)
