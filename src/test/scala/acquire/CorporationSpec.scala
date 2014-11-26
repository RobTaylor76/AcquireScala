package acquire

import acquire.Tier.Tier
import org.scalatest.{Matchers, FlatSpec}

/**
 * Created by rob on 26/11/14.
 */
class CorporationSpec   extends FlatSpec with Matchers {

  "new Corporation" should " be dormant" in {

    val corporation = Corporation(CorporationName.WORLDWIDE)

    corporation.status should equal (Status.DORMANT)
    corporation.tier should equal (Tier.BRONZE)
    corporation.noOfShares should equal (25)
  }

  "Share price" should "be correct" in {

  }

  "share holder bonus" should "be correct" in {

  }
}

class TierSpeac extends FlatSpec with Matchers {

  def sharePriceTest( tier : Tier, uplift : Int): Unit = {
    (2 to 6 ) map { noOfTiles =>  Tier.sharePrice(tier,noOfTiles) should equal ((noOfTiles * 100) + uplift)  }
    (7 to 10 ) map { noOfTiles =>  Tier.sharePrice(tier,noOfTiles) should equal (600 + uplift)  }
    (11 to 20 ) map { noOfTiles =>  Tier.sharePrice(tier,noOfTiles) should equal (700 + uplift)  }
    (21 to 30 ) map { noOfTiles =>  Tier.sharePrice(tier,noOfTiles) should equal (800 + uplift)  }
    (31 to 40 ) map { noOfTiles =>  Tier.sharePrice(tier,noOfTiles) should equal (900 + uplift)  }
    (41 to 99 ) map { noOfTiles =>  Tier.sharePrice(tier,noOfTiles) should equal (1000 + uplift)  }
  }

  "tier share prices" should "be correct" in {

    Tier.values collect {
        case tier @ Tier.BRONZE => sharePriceTest(tier, 0)
        case tier @ Tier.SILVER => sharePriceTest(tier, 100)
        case tier @ Tier.GOLD => sharePriceTest(tier, 200)
    }
  }

  "share holder bonus" should "be correct" in {


  }

}
