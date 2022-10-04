package tests

import org.scalatest._
import pbd.PaleBlueDot

class LectureObjective2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"

  test("get the country code in lowercase") {

    val testCases: Map[String, String] = Map(
      "Albania" -> "al",
      "Barbados" -> "bb",
      "Belgium" -> "be",
      "BelGium" -> "be",
      "Faroe Islands" -> "fo",
      "France" -> "fr",
      "aabb" -> ""
    )

    for((input, output) <- testCases){
      assert(PaleBlueDot.getCountryCode(countriesFile,input) == output, input)
    }

  }


}
