package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective3 extends FunSuite {

  val citiesFilename: String = "data/cities.csv"

  test("the population of a region") {
    val A: (String, String, String) = (citiesFilename, "ad", "04")
    val B: (String, String, String) = (citiesFilename, "ae", "03")
    val C: (String, String, String) = (citiesFilename, "ae", "01")
    val D: (String, String, String) = (citiesFilename, "ae", "06")
    val E: (String, String, String) = (citiesFilename, "ad", "05")
    val F: (String, String, String) = (citiesFilename, "ad", "06")
    val G: (String, String, String) = (citiesFilename, "cf", "07")
    assert(PaleBlueDot.cityPopulations(citiesFilename, "ad", "04") == Map("la massana" -> 7211), A)

    assert(PaleBlueDot.cityPopulations(citiesFilename, "ae", "03") == Map("dubai" -> 1137376), B)

    assert(PaleBlueDot.cityPopulations(citiesFilename, "ae", "01") == Map("abu dhabi" -> 603687), C)

    assert(PaleBlueDot.cityPopulations(citiesFilename, "ae", "06") == Map("sharjah" -> 543942), D)

    assert(PaleBlueDot.cityPopulations(citiesFilename, "ad", "05") == Map("ordino" -> 2553), E)

    assert(PaleBlueDot.cityPopulations( citiesFilename, "cf", "07") == Map("boda" -> 20621,"mbaiki" -> 76350) , G)
  }
}
