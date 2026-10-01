package com.example.samplepr

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {
  private val calculator = Calculator()

  @Test
  fun `add returns sum`() {
    assertEquals(5, calculator.add(2, 3))
  }

  @Test
  fun `subtract returns difference`() {
    assertEquals(1, calculator.subtract(3, 2))
  }
}
