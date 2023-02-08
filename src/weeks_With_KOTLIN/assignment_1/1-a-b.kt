package weeks_With_KOTLIN.assignment_1

const val newTape = 120
const val usedTimeOfTape = 20
const val showToRecordStartHour = 20
const val showToRecordStopHour = 21
const val showToRecordStartMin = 0
const val showToRecordStopMin = 30


fun main() {

    fun timeLeftOnTape(newTape: Int, usedTime: Int) = newTape - usedTime

    when (val usedTape = timeLeftOnTape(newTape, usedTimeOfTape)) {
        useTapeToRecord(
            usedTape,
            showToRecordStartHour,
            showToRecordStartMin,
            showToRecordStopHour,
            showToRecordStopMin
        ) -> println("not enough time on the tape to record the show")

        else -> println(
            "Show recorded. time left on tape ${
                useTapeToRecord(
                    usedTape,
                    showToRecordStartHour,
                    showToRecordStartMin,
                    showToRecordStopHour,
                    showToRecordStopMin
                )
            }"
        )
    }
}

fun useTapeToRecord(
    tapeToUse: Int,
    showStartH: Int,
    showStartM: Int,
    showStopH: Int,
    showStopM: Int
): Int {
    val minute = when (showStopH - showStartH) {
        1 -> 60
        2 -> 120
        3 -> 180
        else -> 0
    }
    return tapeToUse - minute - (showStopM - showStartM).takeIf { it >= 0 }!!
}
