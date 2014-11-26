import aquire.{RealCorporation, NoCorporation, Corporation, Tile}

object Test {
 val a = Tile(1,'A')
  val b = Tile(1,'B')
  var gameState : Map[Tile, Corporation] = Map[Tile, Corporation]()
  var tiles : List[Tile] = { for (col <- (1 to 12); row <- ('A' to 'I') ) yield Tile(col,row) } toList

  //for (tile <- tiles )  gameState += (tile -> None)
  gameState += (a -> NoCorporation)
  gameState += (b -> RealCorporation)
  gameState

  gameState collect { case (tile, corp) if corp != NoCorporation => tile} toList

}