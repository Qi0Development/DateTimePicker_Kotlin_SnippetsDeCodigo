import java.util.Date
import java.util.concurrent.TimeUnit

class TimeStampFormatter {

    /**
     * For use with java.util.Date
     */
    fun format(timestamp: Date): String {
        val millisFromNow = getMillisFromNow(timestamp)

        val minutesFromNow = TimeUnit.MILLISECONDS.toMinutes(millisFromNow)
        if (minutesFromNow < 1) {
            return "Hoje"
        }
        val hoursFromNow = TimeUnit.MILLISECONDS.toHours(millisFromNow)
        if (hoursFromNow < 1) {
            return formatMinutes(minutesFromNow)
        }
        val daysFromNow = TimeUnit.MILLISECONDS.toDays(millisFromNow)
        if (daysFromNow < 1) {
            return formatHours(hoursFromNow)
        }
        val weeksFromNow = TimeUnit.MILLISECONDS.toDays(millisFromNow) / 7
        if (weeksFromNow < 1) {
            return formatDays(daysFromNow)
        }
        val monthsFromNow = TimeUnit.MILLISECONDS.toDays(millisFromNow) / 30
        if (monthsFromNow < 1) {
            return formatWeeks(weeksFromNow)
        }
        /*val yearsFromNow = TimeUnit.MILLISECONDS.toDays(millisFromNow) / 365
        return if (yearsFromNow < 1) {
            formatMonths(monthsFromNow)
        } else formatYears(yearsFromNow)*/

        val yearsFromNow = TimeUnit.MILLISECONDS.toDays(millisFromNow) / 365
        val remMonthsFromNow = monthsFromNow.rem(12)
        return if (yearsFromNow < 1) {
            formatMonths(monthsFromNow)
        } else {
            val year = formatYears(yearsFromNow)
            year  + if (remMonthsFromNow > 0)  " e " + formatMonths(remMonthsFromNow) else " atrás"
        }
    }

    private fun getMillisFromNow(commentedAt: Date): Long {
        val commentedAtMillis = commentedAt.time
        val nowMillis = System.currentTimeMillis()
        return nowMillis - commentedAtMillis
    }

    private fun formatMinutes(minutes: Long): String {
        return format(minutes, " minuto atrás", " minutos atrás")
    }

    private fun formatHours(hours: Long): String {
        return format(hours, " hora atrás", " horas atrás")
    }

    private fun formatDays(days: Long): String {
        return format(days, " dia atrás", " dias atrás")
    }

    private fun formatWeeks(weeks: Long): String {
        return format(weeks, " semana atrás", " semanas atrás")
    }

    private fun formatMonths(months: Long): String {
        return format(months, " mês atrás", " meses atrás")
    }

    private fun formatYears(years: Long): String {
        return format(years, " ano", " anos ")
    }

    private fun format(hand: Long, singular: String, plural: String): String {
        return if (hand == 1L) {
            hand.toString() + singular
        } else {
            hand.toString() + plural
        }
    }

}