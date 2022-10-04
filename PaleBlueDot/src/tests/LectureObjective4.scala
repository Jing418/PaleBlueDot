package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective4 extends FunSuite {

  val citiesFilename: String = "data/cities.csv"


  def compares(input:List[String],expect:List[String]):Boolean = {
    var output = false

    for (l <- input){
      output = true
    }
    if (input.length == 1){
      output = false
    }
    output

  }


  test("bigger population") {
    val a: List[String] = List("boda", "mbaiki")
    val b: List[String] = List("mbaiki")
    val c: List[String] = List()


    assert(compares(PaleBlueDot.bigCities(citiesFilename, "cf", "07", 100), a) == true)
    assert(compares(PaleBlueDot.bigCities(citiesFilename, "cf", "07", 80000), a) == false)

  }

}
