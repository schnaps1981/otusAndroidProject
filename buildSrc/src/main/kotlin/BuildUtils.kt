import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getDate(): String {
    val current = LocalDateTime.now()

    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    return current.format(formatter)
}

enum class Market(val marketName: String) {
    PLAYMARKET("gms"),
    APPGALLERY("hms"),
    RUSTORE("rustore"),
    APPBAZAR("mts")
}
