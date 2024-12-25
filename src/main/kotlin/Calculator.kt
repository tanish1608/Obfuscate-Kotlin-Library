package com.example

class Calculator {
    private val history: MutableList<String> = mutableListOf()
    private val helper = Helper() // Instance of the internal class

    fun add(a: Int, b: Int): Int {
        val result = a + b
        val operation = "$a + $b = $result"
        history.add(operation)
        helper.logOperation(operation) // Access internal class for logging
        return result
    }

    private fun isubtract(a: Int, b: Int): Int {
        val result = a - b
        val operation = "$a - $b = $result"
        history.add(operation)
        helper.logOperation(operation) // Access internal class for logging
        return result
    }
     fun subtract(a: Int, b: Int): Int {
        return isubtract(a, b)
    }


    private fun multiply(a: Int, b: Int): Int {
        val result = a * b
        val operation = "$a * $b = $result"
        history.add(operation)
        helper.logOperation(operation) // Access internal class for logging
        return result
    }

    fun divide(a: Int, b: Int): Int {
        if (b == 0) {
            throw IllegalArgumentException("Cannot divide by zero")
        }
        val result = a / b
        val operation = "$a / $b = $result"
        history.add(operation)
        helper.logOperation(operation) // Access internal class for logging
        return result
    }

    fun getHistory(): List<String> {
        return history.toList()
    }

    fun clearHistory() {
        history.clear()
        helper.logOperation("History cleared")
    }
}

internal class Helper {
    fun logOperation(operation: String) {
        println("Logged operation: $operation")
    }
}
class AdvancedCalculator {
    fun power(base: Int, exponent: Int): Int {
        return Math.pow(base.toDouble(), exponent.toDouble()).toInt()
    }
}