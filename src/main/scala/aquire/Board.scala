package aquire

/**
 * Created by rob on 25/11/14.
 */


abstract class Board {


  private var tiles : List[Tile] = { for (col <- (1 to 12); row <- ('A' to 'I') ) yield Tile(col,row) } toList

  private var gameState : Map[Tile, Corporation] = Map()

  //private var unplacedTiles :List[Tile]


  def resetState = {
    gameState = Map()
    for (tile <- tiles ) gameState += (tile -> None)
  }

  def isTileOccupied(tile: Tile) = gameState(tile) != None

  def placeTile[T <: Corporation](tile: Tile, corp : T) = {
    //placedTiles = tile :: placedTiles
    //unplacedTiles = unplacedTiles filterNot { _ == tile }
    gameState += (tile -> corp)
  }

  def availableTiles: List[Tile] = {
    //for ((tile, corp) <- gameState; if corp != None ) yield tile

    gameState collect  { case (tile, corp) if corp != None => tile} toList

  }
}
