package acquire


import org.scalatest._
import Matchers._

/**
 * Created by rob on 25/11/14.
 */
class BoardSpec  extends FlatSpec with Matchers {

  val tile = Tile(1,'A')

    it should " store tile in correct slot" in {

      val board = new Board

      val boardState : BoardState = board

      boardState isTileOccupied tile  should be (false)

      board placeTile(tile , Corporation(CorporationName.WORLDWIDE))

      boardState isTileOccupied tile  should be (true)

     // board availableTiles  // filter { _ == tile } count should be 0

    }

    it should  "have all the Available Tiles" in {
      val board = new Board
      val boardState = board

      val availableTiles : List[Tile]  = boardState.availableTiles

      availableTiles should contain(tile)

      availableTiles should have size 108

      board placeTile(tile , Corporation(CorporationName.WORLDWIDE))

      boardState.availableTiles should not contain(tile)
    }

  it should "list all placed tiles" in {


  }


}
