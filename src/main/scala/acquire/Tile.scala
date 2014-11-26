package acquire

/**
 * Created by rob on 25/11/14.
 */
case class Tile(col :Int, row: Char) {
  override def toString: String = s"${col}${row}"
}



