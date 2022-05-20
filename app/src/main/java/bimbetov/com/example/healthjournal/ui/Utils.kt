package bimbetov.com.example.healthjournal.ui

fun Pair<Int, Int>.getTimeText(): String {
    return String.format("%02d:%02d", first, second)
}