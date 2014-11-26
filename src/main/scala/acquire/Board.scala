package acquire

/**
 * Created by rob on 25/11/14.
 */


abstract class Board {


  private val tiles : List[Tile] = { for (col <- (1 to 12); row <- ('A' to 'I') ) yield Tile(col,row) } toList

  private var gameState : Map[Tile, Corporation] = Map()

  def resetState = {
    gameState = Map()
    for (tile <- tiles ) {
      gameState += (tile -> UNINCORPORATED)
    }
  }

  def isTileOccupied(tile: Tile) = gameState(tile) != UNINCORPORATED

  def placeTile[T <: Corporation](tile: Tile, corp : T) = {
    gameState += (tile -> corp)
  }

  def availableTiles: List[Tile] = {
    gameState collect  { case (tile, corp) if corp == UNINCORPORATED => tile} toList
  }
}
