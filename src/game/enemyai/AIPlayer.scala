package game.enemyai

import game.enemyai.decisiontree.{ActionNode, DecisionNode, DecisionTreeValue}
import game.lo4_data_structures.linkedlist.LinkedListNode
import game.lo4_data_structures.trees.BinaryTreeNode
import game.maps.GridLocation
import game.physics.PhysicsVector
import game.{AIAction, Fire, MovePlayer}

class AIPlayer(val id: String) {


  // TODO: LT1
  def locatePlayer(playerId: String, playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    new PlayerLocation(0, 0, "")
  }

  def closestPlayer(playerLocations: LinkedListNode[PlayerLocation]): PlayerLocation = {
    new PlayerLocation(0, 0, "")
  }


  // TODO: LT2
  def pathToDirection(playerLocations: LinkedListNode[PlayerLocation], path: LinkedListNode[GridLocation]): PhysicsVector = {
    new PhysicsVector(0.0, 0.0)
  }


  // TODO: LT3
  def computePath(start: GridLocation, end: GridLocation): LinkedListNode[GridLocation] = {
    new LinkedListNode[GridLocation](new GridLocation(0, 0), null)
  }


  // TODO: LT4
  def makeDecision(gameState: AIGameState, decisionTree: BinaryTreeNode[DecisionTreeValue]): AIAction = {
    MovePlayer(this.id, Math.random() - 0.5, Math.random() - 0.5)
  }

  // TODO: LT5
  // TODO: LT6


  // TODO: AO1

  // TODO: AO2
  def decisionTree(referencePlayer: AIPlayer): BinaryTreeNode[DecisionTreeValue] = {
    val huntClosestPlayer = new BinaryTreeNode[DecisionTreeValue](new ActionNode((gameState: AIGameState) => {
      val myLocation: PlayerLocation = referencePlayer.locatePlayer(referencePlayer.id, gameState.playerLocations)
      val closestPlayer: PlayerLocation = referencePlayer.closestPlayer(gameState.playerLocations)
      val path = referencePlayer.computePath(myLocation.asGridLocation(), closestPlayer.asGridLocation())
      val direction: PhysicsVector = referencePlayer.pathToDirection(gameState.playerLocations, path)
      MovePlayer(referencePlayer.id, direction.x, direction.y)
    }), null, null)

    val fire = new BinaryTreeNode[DecisionTreeValue](new ActionNode((gameState: AIGameState) => {
      Fire(referencePlayer.id)
    }), null, null)

    val fireProbability = 0.1
    val decider = new BinaryTreeNode[DecisionTreeValue](
      new DecisionNode((gameState: AIGameState) => if (Math.random() < fireProbability) -1 else 1), fire, huntClosestPlayer
    )

    decider
  }


}

