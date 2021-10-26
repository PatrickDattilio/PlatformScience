package com.dattilio.acmescience

import org.junit.Test

class SecretAlgorithmKtTest {

    @Test
    fun testAllVowelCount() {
        assert("aeiou".getVowelCount() == 5)
    }

    @Test
    fun testEmptyVowelCount() {
        assert("".getVowelCount() == 0)
    }

    @Test
    fun testDoubleVowelCount() {
        assert("aabbccddee".getVowelCount() == 4)
    }

    @Test
    fun testUppercaseVowelCount() {
        assert("AEIOUaeiou".getVowelCount() == 10)
    }
}