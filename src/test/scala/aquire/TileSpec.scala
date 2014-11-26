package aquire

import org.scalatest._


class TileSpec extends FlatSpec with Matchers {
  
  "Tile" should "Have 1A as toString" in {
    val tile = Tile(1, 'A')
    tile.toString should equal ("1A")
  }
}