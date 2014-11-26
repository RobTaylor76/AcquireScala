package acquire


import org.scalatest._
import Matchers._

/**
 * Created by rob on 25/11/14.
 */
class BoardSpec  extends FlatSpec with Matchers {

  class TestBoard extends Board {

  }


    it should " store tile in correct slot" in {

      val board = new TestBoard

      board.resetState

      val tile = Tile(1,'A')

      board isTileOccupied tile  should be (false)

      board placeTile(tile , Corporation(CorporationName.WORLDWIDE))

      board isTileOccupied tile  should be (true)

     // board availableTiles  // filter { _ == tile } count should be 0

    }

    it should  "have all the Available Tiles" in {
      val board = new TestBoard

      board.resetState

      val availableTiles : List[Tile]  = board.availableTiles

      availableTiles should have size 108
    }



}
