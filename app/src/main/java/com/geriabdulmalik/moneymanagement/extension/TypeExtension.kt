package com.geriabdulmalik.moneymanagement.extension

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat

/**
 * Use the given value if null.
 */
fun Int?.orDefault(value: Int = 0): Int = this ?: value

/**
 * This extensions helps you format a small or a large number with it's decimal and group separator.
 * You can use prefix to format it as currency or else.
 *
 * @param groupSeparator
 * @param decimalSeparator
 * @param prefix                a string that placed at front of the digit
 * @param suffix                a string that placed at behind of the digit
 */
fun Number.asDigit(
    groupSeparator: Char = '.',
    decimalSeparator: Char = ',',
    prefix: String = "",
    suffix: String = ""
): String {
    val decimalFormat = DecimalFormat().apply {
        decimalFormatSymbols = DecimalFormatSymbols.getInstance().apply {
            this.groupingSeparator = groupSeparator
            this.decimalSeparator = decimalSeparator
        }
    }
    return "$prefix${decimalFormat.format(this)}$suffix"
}

/**
 * Convert a date string to a new date string format.
 */
@SuppressLint("SimpleDateFormat")
fun String.asDate(
    newFormat: String = "dd MMM yyyy HH:mm",
    oldFormat: String = "yyyy-MM-dd HH:mm:ss"
): String {
    val inSdf = SimpleDateFormat(oldFormat)
    val outSdf = SimpleDateFormat(newFormat)
    val date = inSdf.parse(this)
    return outSdf.format(date!!)
}
