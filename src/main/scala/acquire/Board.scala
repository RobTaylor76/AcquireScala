package acquire

/**
 * Created by rob on 25/11/14.
 */
trait BoardState {
  def availableTiles: List[Tile]
  def isTileOccupied(tile: Tile) : Boolean
}

class Board extends BoardState {

  private val tiles : List[Tile] = { for (col <- (1 to 12); row <- ('A' to 'I') ) yield Tile(col,row) } toList

  private var gameState : Map[Tile, Option[Corporation]] = Map()

  def resetState = {
    gameState = Map()
    for (tile <- tiles ) {
      gameState += (tile -> None)
    }
  }

  resetState

  def isTileOccupied(tile: Tile) : Boolean = gameState(tile) != None

  def placeTile[T <: Corporation](tile: Tile, corp : T) = {
    gameState += (tile -> Some(corp))
  }

  def availableTiles: List[Tile] = {
    gameState collect  { case (tile, corp) if corp == None => tile} toList
  }
}

