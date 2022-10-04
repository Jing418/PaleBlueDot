package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective5 extends FunSuite {

  val EPSILON: Double = 0.0001

  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < EPSILON
  }

  test("measure the circle distance") {
    val testCase1d1: List[Double] = List(45.0,1.2)
    val testCase1d2: List[Double] =List(9.6,66.0)
    val testCase1Result: Double = 7284
    val testCase2Result: Double = 7282.670461028828

    val actual = PaleBlueDot.greaterCircleDistance(testCase1d1, testCase1d2)
    println(actual)
    assert(compareDoubles(actual, testCase1Result)==false)
    assert(compareDoubles(actual, testCase2Result)==true)
  }

}
