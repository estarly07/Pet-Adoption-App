package com.estarly.petadoptionapp.utils

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

fun Int.toYear() : String{
    return "${this/12}"
}
fun Double.format(): String = DecimalFormat.getNumberInstance().format(this)

/**
 *
 *
 * @param id El identificador del recurso de dimensión.
 * @return El valor de la dimensión en sp.
 *
 * Recupera una dimensión de los recursos y la convierte a sp para componentes de texto en Compose.
 *
 * Esta función obtiene un valor de dimensión definido en los recursos de la aplicación (normalmente en archivos XML bajo `res/values`).
 * Luego, convierte este valor a sp  utilizando la función `dimensionResource`
 * y la propiedad `.value.sp`.
 */
@Composable
@ReadOnlyComposable
fun fontDimensionResource(@DimenRes id: Int) = dimensionResource(id = id).value.sp