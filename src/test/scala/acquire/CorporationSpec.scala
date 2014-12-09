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
}

class TierSpeac extends FlatSpec with Matchers {

  def sharePriceTest( tier : Tier, uplift : Int): Unit = {
    (2 to 5) map { noOfTiles => checkSharePrice(tier,noOfTiles, uplift, (noOfTiles * 100) ) }
    (6 to 10) map { noOfTiles =>  checkSharePrice(tier,noOfTiles, uplift, 600 )  }
    (11 to 20) map { noOfTiles =>  checkSharePrice(tier,noOfTiles, uplift, 700)  }
    (21 to 30) map { noOfTiles =>  checkSharePrice(tier,noOfTiles, uplift, 800)  }
    (31 to 40) map { noOfTiles =>  checkSharePrice(tier,noOfTiles, uplift, 900)  }
    (41 to 108) map { noOfTiles =>  checkSharePrice(tier,noOfTiles, uplift, 1000)  }
  }


  def shareHolderBonusTest( tier : Tier, majorUplift : Int, minorUplift : Int): Unit = {
    (2 to 5) map { noOfTiles =>  checkBonus(tier, noOfTiles, majorUplift, minorUplift, (noOfTiles*1000), (noOfTiles*500))  }
    (6 to 10) map { noOfTiles =>  checkBonus(tier, noOfTiles, majorUplift, minorUplift, 6000, 3000)  }
    (11 to 20) map { noOfTiles =>  checkBonus(tier, noOfTiles, majorUplift, minorUplift, 7000, 3500)  }
    (21 to 30) map { noOfTiles =>  checkBonus(tier, noOfTiles, majorUplift, minorUplift, 8000, 4000)  }
    (31 to 40) map { noOfTiles =>  checkBonus(tier, noOfTiles, majorUplift, minorUplift, 9000, 4500)  }
    (41 to 108) map { noOfTiles => checkBonus(tier, noOfTiles, majorUplift, minorUplift, 10000, 5000)  }
  }

  def checkSharePrice(tier : Tier, noOfTiles : Int, uplift : Int, value : Int): Unit = {
    Tier.sharePrice(tier,noOfTiles) should equal (value + uplift)
  }

  def checkBonus(tier : Tier, noOfTiles : Int, majorUplift : Int, minorUplift : Int, majorValue : Int, minorValue : Int): Unit = {
    Tier.shareHolderBonus(tier,noOfTiles) match {
      case ShareHolderBonus(major, minor) => {
        major should equal (majorValue + majorUplift)
        minor should equal (minorValue + minorUplift)
      }
    }
  }

  "tier share prices" should "be correct" in {

    Tier.values collect {
        case tier @ Tier.BRONZE => sharePriceTest(tier, 0)
        case tier @ Tier.SILVER => sharePriceTest(tier, 100)
        case tier @ Tier.GOLD => sharePriceTest(tier, 200)
    }
  }

  "share holder bonus" should "be correct" in {
    Tier.values collect {
      case tier@Tier.BRONZE => shareHolderBonusTest(tier, 0, 0)
      case tier@Tier.SILVER => shareHolderBonusTest(tier, 1000, 500)
      case tier@Tier.GOLD => shareHolderBonusTest(tier, 2000, 1000)
    }
  }
}
