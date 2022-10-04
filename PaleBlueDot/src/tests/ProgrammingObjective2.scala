package tests

import org.scalatest._
import pbd.PaleBlueDot

class ProgrammingObjective2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("test 1") {
    val testCaseResult1: Int = 33638
    val value1: Int = PaleBlueDot.countryPopulation("data/countries.txt", "data/cities.csv", "Andorra")
    assert(value1 == testCaseResult1, value1)

    val testCaseResult2: Int = 1379
    val value2: Int = PaleBlueDot.countryPopulation("data/countries.txt", "data/cities.csv", "Anguilla")
    assert(value2 == testCaseResult2, value2)


  }
}
