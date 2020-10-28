import kotlin.random.Random

val range = 1..50
const val maxTries = 5

fun main() {
    evaluation(playGuessingGame())
}

fun playGuessingGame(): Boolean {
    val numberToGuess = Random.nextIntInRange(range)
    var tries = 0
    while (true) {
        val guess = getUserGuess(maxTries - tries)
        when {
            guess == numberToGuess -> return true
            guess < numberToGuess -> {
                println("Your guess is too low")
            }
            guess > numberToGuess -> {
                println("Your guess is too high")
            }
        }
        tries++
        if (tries >= maxTries) return false
    }
}

fun evaluation(b: Boolean) {
    if (b) println("Congratulations, your guess was correct") else println("Sorry, you ran out of tries")
}

fun getUserGuess(triesLeft: Int): Int {
    do {
        println("Enter your guess between ${range.first} and ${range.last} -- you have $triesLeft tries left")
        val guess = readLine()
        if (guess != null && guess.isValidInput()) {
            return guess.toInt()
        } else {
            println("Invalid input")
        }
    } while (true)
}

fun String.isValidInput() : Boolean = this.toIntOrNull() != null && this.toInt() in range

fun Random.nextIntInRange(range: IntRange): Int = this.nextInt(range.last - range.first + 1) + range.first
