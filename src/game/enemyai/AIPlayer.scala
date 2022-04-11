package game.enemyai

import game.enemyai.decisiontree.DecisionTreeValue
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.lo4_data_structures.trees.BinaryTreeNode
import game.maps.GridLocation
import game.{AIAction, MovePlayer}

class AIPlayer(val id: String) {


  // TODO: Replace this placeholder code with your own
  def locatePlayer(playerId: String, playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    new PlayerLocation(0, 0, "")
  }

  // TODO: Replace this placeholder code with your own
  def closestPlayer(playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    new PlayerLocation(0, 0, "")
  }


  // TODO: Replace this placeholder code with your own
  def computePath(start: GridLocation, end: GridLocation): LinkedListNode[GridLocation] = {
    new LinkedListNode[GridLocation](new GridLocation(0, 0), null)
  }


  // TODO: Replace this placeholder code with your own
  def makeDecision(gameState: AIGameState, decisionTree: BinaryTreeNode[DecisionTreeValue]): AIAction = {
    MovePlayer(this.id, Math.random() - 0.5, Math.random() - 0.5)
  }


  // TODO: Replace this placeholder code with your own
  def closestPlayerAvoidWalls(gameState: AIGameState): PlayerLocation = {
    closestPlayer(gameState.playerLocations)
  }

  // TODO: Replace this placeholder code with your own
  def getPath(gameState: AIGameState): LinkedListNode[GridLocation] = {
    computePath(locatePlayer(this.id, gameState.playerLocations).asGridLocation(), closestPlayerAvoidWalls(gameState).asGridLocation())
  }




}

