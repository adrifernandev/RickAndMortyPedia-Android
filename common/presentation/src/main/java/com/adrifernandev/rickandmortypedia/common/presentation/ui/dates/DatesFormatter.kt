package com.adrifernandev.rickandmortypedia.common.presentation.ui.dates

import com.adrifernandev.rickandmortypedia.common.presentation.ui.dates.DatesFormatter.DateFormat.DAY_MONTH_YEAR
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object DatesFormatter {

    object DateFormat {
        const val DAY_MONTH_YEAR = "dd MMM yyyy"
    }

    fun formatDateToString(
        date: Date,
        pattern: String = DAY_MONTH_YEAR,
        locale: Locale = Locale.getDefault(),
    ): String {
        val formatter = DateTimeFormatter.ofPattern(pattern, locale)
        val localDate = date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        return formatter.format(localDate)
    }
}