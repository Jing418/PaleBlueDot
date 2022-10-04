package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective1 extends FunSuite {

  val EPSILON: Double = 0.0000000000000001

  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < EPSILON
  }

  test("test represents a value in degrees will return the true value in radians") {

    val testCases:Map[Double, Double] = Map(
      2.0 -> Math.PI/90,
      3.0 -> Math.PI/60,
      10.0 -> Math.PI/18,
      6.0 -> Math.PI/30
    )

    for ((input, expectedOutput) <- testCases) {
      val radians: Double = PaleBlueDot.degreesToRadians(input)
      assert(compareDoubles(radians, expectedOutput),
        "On input: " + input +
          " | expected: " + expectedOutput +
          " | computed: " + radians
      )

    }
  }
}
