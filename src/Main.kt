/**
 * Kotlin Assignment 2
 * Course: MWD3B Android Development
 *
 * This program demonstrates solutions for:
 * A. Caesar-style string encryption
 * B. Anagram comparison
 * C. Manual substring detection
 * D. Longest-word analysis
 */

/**
 * Challenge A
 *
 * Encrypts a string by shifting every alphabetic character by the supplied key.
 * Uppercase and lowercase letters retain their original case.
 * Spaces, numbers, and punctuation remain unchanged.
 */
fun encryptText(text: String, key: Int): String {
    val encryptedText = StringBuilder()

    // Normalize the key so values above 26 and negative values work correctly.
    val normalizedKey = ((key % 26) + 26) % 26

    for (character in text) {
        val encryptedCharacter = when (character) {
            in 'a'..'z' -> {
                val shiftedPosition =
                    (character.code - 'a'.code + normalizedKey) % 26

                ('a'.code + shiftedPosition).toChar()
            }

            in 'A'..'Z' -> {
                val shiftedPosition =
                    (character.code - 'A'.code + normalizedKey) % 26

                ('A'.code + shiftedPosition).toChar()
            }

            else -> character
        }

        encryptedText.append(encryptedCharacter)
    }

    return encryptedText.toString()
}

/**
 * Challenge B
 *
 * Determines whether two single words are anagrams.
 * The comparison ignores uppercase and lowercase differences.
 */
fun areAnagrams(firstWord: String, secondWord: String): Boolean {
    val normalizedFirstWord = firstWord.lowercase()
    val normalizedSecondWord = secondWord.lowercase()

    // Words with different lengths cannot be anagrams.
    if (normalizedFirstWord.length != normalizedSecondWord.length) {
        return false
    }

    return normalizedFirstWord.toCharArray().sorted() ==
            normalizedSecondWord.toCharArray().sorted()
}

/**
 * Challenge C
 *
 * Determines whether the second string is a substring of the first string.
 * It performs a manual character-by-character comparison and does not use
 * String.contains().
 */
fun isSubstring(firstText: String, secondText: String): Boolean {
    // An empty string is considered a substring of every string.
    if (secondText.isEmpty()) {
        return true
    }

    // A longer string cannot be a substring of a shorter string.
    if (secondText.length > firstText.length) {
        return false
    }

    // Check every possible starting position in the first string.
    for (startIndex in 0..(firstText.length - secondText.length)) {
        var charactersMatch = true

        // Compare the second string with the current section of the first.
        for (secondIndex in secondText.indices) {
            if (firstText[startIndex + secondIndex] != secondText[secondIndex]) {
                charactersMatch = false
                break
            }
        }

        if (charactersMatch) {
            return true
        }
    }

    return false
}

/**
 * Challenge D
 *
 * Examines a string and returns its longest word.
 * A word is treated as a continuous sequence of letters.
 * If the supplied string has no words, an empty string is returned.
 */
fun findLongestWord(text: String): String {
    var longestWord = ""
    val currentWord = StringBuilder()

    for (character in text) {
        if (character.isLetter()) {
            currentWord.append(character)
        } else {
            // Compare the completed word with the longest word found so far.
            if (currentWord.length > longestWord.length) {
                longestWord = currentWord.toString()
            }

            currentWord.clear()
        }
    }

    // Check the final word in case the text does not end with punctuation.
    if (currentWord.length > longestWord.length) {
        longestWord = currentWord.toString()
    }

    return longestWord
}

/**
 * Runs test examples for all four assignment challenges.
 */
fun main() {
    println("Challenge A: String Encryption")
    println("--------------------------------")

    val originalText = "Attack at Dawn!"
    val encryptionKey = 1

    println("Original text: $originalText")
    println("Encryption key: $encryptionKey")
    println("Encrypted text: ${encryptText(originalText, encryptionKey)}")

    println()
    println("Challenge B: Anagram Checker")
    println("--------------------------------")

    val firstWord = "dusty"
    val secondWord = "study"

    println("First word: $firstWord")
    println("Second word: $secondWord")
    println("Are they anagrams? ${areAnagrams(firstWord, secondWord)}")
    println("Are hello and world anagrams? ${areAnagrams("hello", "world")}")

    println()
    println("Challenge C: Substring Checker")
    println("--------------------------------")

    val firstText = "Kotlin programming"
    val secondText = "program"

    println("First string: $firstText")
    println("Second string: $secondText")
    println("Is the second string a substring? ${isSubstring(firstText, secondText)}")
    println(
        "Is Java a substring of Kotlin programming? " +
                isSubstring("Kotlin programming", "Java")
    )

    println()
    println("Challenge D: Longest Word")
    println("--------------------------------")

    val sentence = "Kotlin programming is enjoyable and educational."

    println("Sentence: $sentence")
    println("Longest word: ${findLongestWord(sentence)}")

    println()
    println("Additional test:")
    println(
        "Longest word in 'Android development requires practice': " +
                findLongestWord("Android development requires practice")
    )
}