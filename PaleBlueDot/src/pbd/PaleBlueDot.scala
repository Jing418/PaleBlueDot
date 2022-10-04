package pbd

import java.awt.Desktop
import java.net.URI
import scala.io.{BufferedSource, Source}
import scala.util.control.Breaks.break

object PaleBlueDot {


  /**
   * Lecture Objective 1
   *
   * Converts degrees into radians
   *
   * @param degrees A value provided in degrees
   * @return The radian equivalent of the input value
   */
  def degreesToRadians(degrees: Double): Double = {
    degrees * Math.PI/180
  }


  /**
   * Lecture Objective 2
   *
   * Given a country name using and case (upper/lower), return the country code in all lowercase letters
   *
   * Ex. If "Heard Island and McDonald Islands#HM" is a line countriesFilename
   * and countryName is "hEaRd IsLaNd AnD mCdOnAlD iSlAnDs" the returned value is "hm"
   *
   * If countryName is not in the file, return the empty String: ""
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param countryName       The name of the country to lookup in the file with any mix of upper/lower-case
   * @return The two letter country code for countryName
   */


  def getCountryCode(countriesFilename: String, countryName: String): String = {
    val countriesFile: BufferedSource = Source.fromFile(countriesFilename)

    var output: String = ""

    for (l <- countriesFile.getLines())  {
      val array = l.split('#')

      val csvName = array(0).toLowerCase()
      val inputName = countryName.toLowerCase()

      if (csvName == inputName){
        output = array(1).toLowerCase()
      }
    }
    output
  }



  /**
   * Lecture Objective 3
   *
   * Returns a Map[cityName -> population] for all cities in the given county and region. The name of each
   * city should match exactly how it appears in citiesFilename and the population is read from the file
   * and converted to an Int.
   *
   * Ex: PaleBlueDot.cityPopulations(citiesFilename, "ad", "04") returns Map("la massana" -> 7211) since
   * "la massana" is the only city in region "04" of Andorra (code "ad") and its population is 7211
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param countryCode    A two character country code
   * @param region         A two character region code
   * @return A Map containing the name and population of every city matching both the countryCode and region
   */


  def cityPopulations(citiesFilename: String, countryCode: String, region: String): Map[String, Int] = {
    var output:Map[String,Int] = Map()
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    for (l <- citiesFile.getLines().drop(1)) {
      var array:Array[String] = l.split(",")

      if(countryCode == array(0) && region == array(2)){
        var keyy = array(1)
        var value = array(3).toInt
        println(keyy)
        output += keyy -> value
      }
    }

    output
  }




  /**
   * Lecture Objective 4
   *
   * Returns a List of city names in the given county and region with a population at least minPopulation.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param countryCode    A two character country code
   * @param region         A two character region code
   * @param minPopulation  the minimum population that could be returned
   * @return All city names in countryCode/region with a population >= minPopulation
   */

  def bigCities(citiesFilename: String, countryCode: String, region: String, minPopulation: Int): List[String] = {

    var output:List[String] = List()
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    for (l <- citiesFile.getLines().drop(1)) {
      var array: Array[String] = l.split(",")

      var value = array(3).toInt
      if (countryCode == array(0) && region == array(2) && minPopulation <= value) {
        output = output :+ array(1)
      }

    }
    output

  }


  /**
   * Lecture Objective 5
   *
   * Computes the grater circle distance ("As the crow flies") between two locations on Earth in kilometers.
   * The input locations are given as Lists of Double containing the latitude and longitude coordinates of that
   * location. For example, if the location is latitude: 35.685 and longitude: 139.751389 the input would be
   * List(35.685, 139.751389).
   *
   * @param location1 A location on Earth given as a List containing latitude and longitude coordinates
   * @param location2 A location on Earth given as a List containing latitude and longitude coordinates
   * @return The greater circle distance between the two input locations
   */
  def greaterCircleDistance(location1: List[Double], location2: List[Double]): Double = {
    val earthRadius: Double = 6371.0 // km

    val lat1: Double = location1.head
    val lat2: Double = location2.head
    val lon1: Double = location1(1)
    val lon2: Double = location2(1)

    val φ1 = lat1 * Math.PI/180
    val φ2 = lat2 * Math.PI/180
    val Δφ = (lat2-lat1) * Math.PI/180;
    val Δl = (lon2-lon1) * Math.PI/180;

    val a = Math.sin(Δφ/2) * Math.sin(Δφ/2) + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δl/2) * Math.sin(Δl/2);
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    val d = earthRadius * c;

    d

  }



  /**
   * Programming Objective 1
   *
   * You find yourself stranded in an unfamiliar place with no signs of civilization. You don't have much with you,
   * but you do have a locator that gives your current latitude/longitude, a csv file of cities, and your final
   * submission to the PaleBlueDot assignment from CSE116 (What luck!). You decide that finding and walking
   * directly to the closest city will give you the best chance to survive.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param location       A location on Earth given as a List containing latitude and longitude coordinates
   * @return The city closest to the given location as a List containing country code, city name, and region
   *         exactly as they appear in the cities file
   */
  def closestCity(citiesFilename: String, location: List[Double]): List[String] = {
    //List("Country Code", "City Name", "Region")

    var minDistance = Double.MaxValue
    var output: List[String] = List()
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)

    for (l <- citiesFile.getLines().drop(1))  {
      var array = l.split(',')

      var latitude = array(4).toDouble
      var longitude = array(5).toDouble

      var distance = greaterCircleDistance(List(location(0), location(1)),List(latitude, longitude))

      if(distance < minDistance){
        output = List(array(0), array(1), array(2))
        minDistance = distance
      }
    }
    output
  }


  /**
   * Programming Objective 2
   *
   * Find the population of a country by name. Not quite a life or death situation, but interesting information
   * regardless.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return The total population of the given country
   */
  def countryPopulation(countriesFilename: String, citiesFilename: String, countryName: String): Int = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    val countriesCode:String = getCountryCode(countriesFilename, countryName)
    var output: Int = 0
    for (l <- citiesFile.getLines().drop(1)) {
      var array: Array[String] = l.split(",")
      var countries = array(0).toString

      if (countries == countriesCode){
        output = output + array(3).toInt
      }
    }
    output
  }

  /**
   * Application Objective
   *
   * You're in a city. I'm in a city. We want to meet in a city with a fair split of travel distance for each of us.
   * We happen to both own helicopters so we'll travel "as the crow flies" and we're not concerned about roads or
   * oceans. We just need to find the city closest to the midpoint between our two cities and we'll meet there.
   *
   * Each city is provided to this method as a List containing the country code, name, and region exactly as they
   * appear in the cities file (ie. Don't do anything with upper/lower-case in this method.) The returned city should
   * follow the same formatting (Don't modify the upper/lower-case of any Strings).
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param city1          A city as a List containing country code, name, and region exactly as they appear in the
   *                       cities file
   * @param city2          A city as a List containing country code, name, and region exactly as they appear in the
   *                       cities file
   * @return The city closest to the midpoint of the two input cities as a List containing country code, city name,
   *         and region exactly as they appear in the cities file
   */

  def whereToMeet(citiesFilename: String, city1: List[String], city2: List[String]): List[String] = { //List("Country Code", "City Name", "Region")
    var lat1: Double = 0
    var lat2: Double = 0
    var lon1: Double = 0
    var lon2: Double = 0
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    def abc: Unit ={
      for (l <- citiesFile.getLines().drop(1)) {
        var array = l.split(',')
        if(city1(0) == array(0) && city1(1) == array(1) && city1(2) == array(2)){
          lat1 = array(4).toDouble
          lon1 = array(5).toDouble
        }
        if(city2(0) == array(0) && city2(1) == array(1) && city2(2) == array(2)){
          lat2 = array(4).toDouble
          lon2 = array(5).toDouble
        }
      }
    }
    val epsilon : Double = 0.0001
    if ((Math.abs(lat1-lat2)<epsilon) && (Math.abs(lon1-lon2)<epsilon)){
      return city1
    }
    val φ1 = lat1 * Math.PI/180 // in radians
    val φ2 = lat2 * Math.PI/180
    val λ1 = lon1 * Math.PI/180 // in radians
    val λ2 = lon2 * Math.PI/180
    var Bx = Math.cos(φ2) * Math.cos(λ2-λ1)
    var By = Math.cos(φ2) * Math.sin(λ2-λ1)
    var φ3 = Math.atan2(Math.sin(φ1) + Math.sin(φ2),
             Math.sqrt((Math.cos(φ1)+Bx)*(Math.cos(φ1)+Bx) + By*By))
    var λ3 = λ1 + Math.atan2(By, Math.cos(φ1) + Bx)
    var lat3 = φ3 * 180/Math.PI
    var lon3 = λ3 * 180/Math.PI
    var destiny = closestCity(citiesFilename, List(lat3, lon3))
    println(destiny(1))
    destiny
  }


  /**
   * Helper Method
   *
   * Opens Google Maps at a specific location. The location is a List containing the latitude then longitude as Doubles
   *
   * @param location The location to open in the format List(Latitude, Longitude)
   */
  def openMap(location: List[Double]): Unit = {
    if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE)) {
      val url: String = "http://maps.google.com/maps?t=m&q=loc:" + location.head.toString + "+" + location(1).toString
      Desktop.getDesktop.browse(new URI(url))
    } else {
      println("Opening the browser not supported")
    }
  }


  def main(args: Array[String]): Unit = {
    openMap(List(43.002743, -78.7874136))
  }

}
