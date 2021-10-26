package com.dattilio.acmescience


val destinations = listOf<String>(
    "215 Osinski Manors",
    "9856 Marvin Stravenue",
    "7127 Kathlyn Ferry",
    "987 Champlin Lake",
    "63187 Volkman Garden Suite 447",
    "75855 Dessie Lights",
    "1797 Adolf Island Apt. 744",
    "2431 Lindgren Corners",
    "8725 Aufderhar River Suite 859",
    "79035 Shanna Light Apt. 322"
)
val drivers = listOf<String>(
    "Izaiah Lowe",
    "Monica Hermann",
    "Ellis Wisozk",
    "Noemie Murphy",
    "Cleve Durgan",
    "Orval Mayert",
    "Howard Emmerich",
    "Everardo Welch",
)

data class Result(val score:Double,val destination:String,val driver:String)

fun naive(destinations: List<String>, drivers: List<String>) {
    val results = mutableListOf<List<Result>>()
    drivers.forEachIndexed { i, driver ->
        val destinationList = mutableListOf<Result>()
        destinations.forEachIndexed { j, destination ->
            val score = getSuitabilityScore(destination, driver)
            val string = "Score for [$i,$j]: $score ($destination, $driver)"
            destinationList.add(Result(score,destination, driver))
            println(string)
        }
        results.add(i,destinationList.sortedBy{it.score})
    }

    results.reversed().forEach {

    }
}

fun getSuitabilityScore(streetName: String, driverName: String): Double {
    return getBaseSuitabilityScore(streetName, driverName) *
            getCommonFactorMultiplier(streetName.length, driverName.length)

}

// Assumes streetname counts spaces and numbers.
private fun getBaseSuitabilityScore(streetName: String, driverName: String): Double {
    //Even
    return if (streetName.length % 2 == 0) {
        driverName.replace(" ", "")
            .lowercase()
            .getVowelCount() * SS_VOWEL_MULTIPLIER
    } else //Odd
    {
        driverName.replace(" ", "")
            .lowercase()
            .getConsonantCount() * SS_CONSONANT_MULTIPLIER
    }

}

private fun getCommonFactorMultiplier(streetNameLength: Int, driverNameLength: Int): Double {
    return if (hasCommonFactor(streetNameLength, driverNameLength)) {
        COMMON_FACTOR_MULTIPILER
    } else {
        NO_COMMON_FACTOR_MULTIPLIER
    }
}

private fun hasCommonFactor(streetNameLength: Int, driverNameLength: Int): Boolean {
    return gcd(streetNameLength, driverNameLength) > 1
}

//Eucclid's GCD, assumes a and b are positive.
private tailrec fun gcd(a: Int, b: Int): Int {
    return if (b == 0) {
        a
    } else {
        gcd(b, a % b)
    }
}


const val SS_VOWEL_MULTIPLIER = 1.5
const val SS_CONSONANT_MULTIPLIER = 1.0
const val COMMON_FACTOR_MULTIPILER = 1.5
const val NO_COMMON_FACTOR_MULTIPLIER = 1.0


fun String.getVowelCount(): Int {
    return this.count { character ->
        when (character) {
            'a', 'e', 'i', 'o', 'u' -> true
            else -> false
        }
    }
}

fun String.getConsonantCount(): Int = this.length - this.getVowelCount()


fun main() {
    naive(destinations, drivers)
}
