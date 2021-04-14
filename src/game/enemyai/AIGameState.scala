package game.enemyai

import game.lo4_data_structures.linkedlist.LinkedListNode
import game.maps.GridLocation
import play.api.libs.json.{JsValue, Json}

class AIGameState {

  var levelWidth: Int = 0
  var levelHeight: Int = 0

  var playerLocations: LinkedListNode[PlayerLocation] = _
  var wallLocations: List[GridLocation] = List()



  def parseJsonState(jsonState: String): Unit = {
    val gameState: JsValue = Json.parse(jsonState)

    val levelWidth = (gameState \ "gridSize" \ "x").as[Int]
    val levelHeight = (gameState \ "gridSize" \ "y").as[Int]

    var playerList: LinkedListNode[PlayerLocation] = null

    for(jsonPlayer <- (gameState \ "players").as[List[JsValue]]){
      val currentPlayer = new PlayerLocation(
        (jsonPlayer \ "x").as[Double],
        (jsonPlayer \ "y").as[Double],
        (jsonPlayer \ "id").as[String]
      )
      playerList = new LinkedListNode(currentPlayer, playerList)
    }

    val walls: List[GridLocation] = (gameState \ "walls").as[List[JsValue]].map(
      (jsonWall: JsValue) => new GridLocation((jsonWall \ "x").as[Int], (jsonWall \ "y").as[Int])
    )

    this.levelHeight = levelHeight
    this.levelWidth = levelWidth
    this.playerLocations = playerList
    this.wallLocations = walls
  }

}
