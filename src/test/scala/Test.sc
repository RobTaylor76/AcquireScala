import aquire.{Corporation, Tile}
object Test {
 val a = Tile(1,'A')
  var gameState : Map[Tile, Corporation] = Map[Tile, Corporation]()
  var tiles : List[Tile] = { for (col <- (1 to 12); row <- ('A' to 'I') ) yield Tile(col,row) } toList


  //for (tile <- tiles )  gameState += (tile -> None)
  gameState += (a -> None)

  gameState filterNot { _ != None } map { case (tile, corp) if corp != None => tile} toList

}