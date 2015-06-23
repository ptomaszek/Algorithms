package org.example

object VendorMachineCalc {

  /**
   * @return a Seq of used coins (biggest coins prioritized)
   *         or None if can't process (means return the giver whatever he gave)
   */
  def countTheMostOptimalChange(money: Int, availCoins: Seq[Int]): Option[Seq[Int]] = {
    countTheMostOptimalChange(money, availCoins.sorted.reverse, Nil)
  }
  
  private def countTheMostOptimalChange(money: Int, availCoins: Seq[Int], usedCoins: Seq[Int]): Option[Seq[Int]] = {
    if (money == 0) Some(usedCoins)
    else if (availCoins.isEmpty || money < 0) None
    else {
      countTheMostOptimalChange(money - availCoins.head, availCoins.tail, usedCoins :+ availCoins.head) match {
        case None => countTheMostOptimalChange(money, availCoins.tail, usedCoins) // the head coin is no help => try without it
        case change => change
      }
    }
  }
}
