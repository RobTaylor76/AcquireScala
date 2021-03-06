package acquire

import acquire.CorporationName.CorporationName
import acquire.Tier.Tier

/**
 * Created by rob on 25/11/14.
 */
trait Corporation {
  protected var _status = Status.DORMANT

  def noOfShares : Int
  def tier : Tier = Tier.NONE
  def status = _status
  def name : CorporationName


}

object Status extends Enumeration {
  type Status = Value
  val DORMANT,
      ACTIVE,
      DEFUNCT= Value
}

object CorporationName extends Enumeration {
  type CorporationName = Value
  val UNINCORPORATED,
  WORLDWIDE,
  SACKSON,
  FESTIVAL,
  IMPERIAL,
  AMERICAN,
  CONTINENTAL,
  TOWER = Value
}

case class ShareHolderBonus(majority : Int, minority: Int)

object Tier extends Enumeration {
  def shareHolderBonus(tier: Tier, noOfTiles: Int) : ShareHolderBonus = {

    val majority = sharePrice(tier, noOfTiles) * 10;
    val minority = majority / 2;

    ShareHolderBonus(majority, minority)
  }

  def sharePrice(tier: Tier, noOfTiles:Int) : Int =  {
    val uplift = tier match {
      case Tier.BRONZE => 0
      case Tier.SILVER => 100
      case Tier.GOLD => 200
      case _ => 0
    }
    ////map { _ }
/*
    noOfTiles match  {
      case it if 2 to 6 contains it => (noOfTiles * 100)
      case it if 7 to 10 contains it => 600
      case it if 11 to 20 contains it => 700
      case it if 21 to 30 contains it => 800
      case it if 31 to 40 contains it => 900
      case it if 41 to 99 contains it => 1000
      case _ => 0
    }  + uplift
*/
    val price = if (noOfTiles < 6) {
                  noOfTiles * 100
                } else if (noOfTiles < 11) {
                  600
                } else if (noOfTiles < 21) {
                  700
                } else if (noOfTiles < 31) {
                  800
                } else if (noOfTiles < 41) {
                  900
                } else {
                  1000
                }

    price + uplift
  }

  type Tier = Value
  val NONE,
  BRONZE,
  SILVER,
  GOLD = Value
}



object Corporation {
  case class CorporationDefinition(tier: Tier, noOfShares: Int)

  val corporationTiers = Map((CorporationName.WORLDWIDE -> CorporationDefinition(Tier.BRONZE, 25)),
                              (CorporationName.SACKSON -> CorporationDefinition(Tier.BRONZE, 25)),
                              (CorporationName.FESTIVAL -> CorporationDefinition(Tier.SILVER, 25)),
                              (CorporationName.IMPERIAL -> CorporationDefinition(Tier.SILVER, 25)),
                              (CorporationName.AMERICAN -> CorporationDefinition(Tier.SILVER, 25)),
                              (CorporationName.CONTINENTAL -> CorporationDefinition(Tier.GOLD, 25)),
                              (CorporationName.TOWER -> CorporationDefinition(Tier.GOLD, 25)))

  def apply(name:CorporationName): Corporation = {
    corporationTiers(name) match {
      case CorporationDefinition(tier, noOfShares) =>  new RealCorporation(name, tier, noOfShares ) }
  }
}


object UNINCORPORATED extends Corporation {
  override def noOfShares: Int = 0

  override def name: CorporationName = CorporationName.UNINCORPORATED
}

class RealCorporation(override val name : CorporationName ,
                      override val tier : Tier,
                      override val noOfShares: Int) extends Corporation {
}