package tests

import org.scalatest._
import pbd.PaleBlueDot

class ApplicationObjective extends FunSuite {

  val citiesFilename: String = "data/cities.csv"

  test("test where to meet"){
    val city1: List[String] = List("us","rochester","MI")//(42.6805556,-83.1338889)
    val city2: List[String] = List("us","buffalo", "MN")//(45.1719444,-93.8744444)

    val destiny: List[String] = List("us","oshkosh","WI")
    val actual = PaleBlueDot.whereToMeet(citiesFilename, city1, city2)

    assert(actual == destiny)

    val actual2 = PaleBlueDot.whereToMeet(citiesFilename, city2, city2)
    assert(actual2 == city2)
  }

}
