package tests

import org.scalatest._
import pbd.PaleBlueDot

class ProgrammingObjective1 extends FunSuite {

  val citiesFilename: String = "data/cities.csv"

  test("test 1") {
    val testCasePoint1: List[Double] = List(35.634,69.262)
    val testCasePoint2: List[Double] = List(35.633484,69.260195)
    val testCaseResult: List[String] = List("af", "andarab", "03")

    val value1 = PaleBlueDot.closestCity(citiesFilename, testCasePoint1)
    assert(value1 == testCaseResult, value1)

    val value2 = PaleBlueDot.closestCity(citiesFilename, testCasePoint2)
    assert(value2 == testCaseResult, value2)


    val testCasePoint3: List[Double] = List(41.747,-87.92)
    val testCaseResult3: List[String] = List("us","burr ridge","IL")

    val value3 = PaleBlueDot.closestCity(citiesFilename, testCasePoint3)
    assert(value3 == testCaseResult3, value3)



    val testCasePoint4: List[Double] = List(37.7272222,-89.2166667)
    val testCaseResult4: List[String] = List("us","carbondale","IL")

    val value4 = PaleBlueDot.closestCity(citiesFilename, testCasePoint4)
    assert(value4 == testCaseResult4, value4)


    val testCasePoint5: List[Double] = List(44.1400,023.5700)
    val testCaseResult5: List[String] = List("ro", "radovan", "26")
    val value5 = PaleBlueDot.closestCity(citiesFilename, testCasePoint5)
    assert(value5 == testCaseResult5, value5)

  }

}
