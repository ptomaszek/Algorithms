package org.example

import org.scalatest.{Matchers, FlatSpec}

class VendorMachineCalcSpec extends FlatSpec with Matchers {

  "The VendorMachineCalc" should "count the most optimal change" in {
    Map(
      (0, Seq(25)) -> Some(Nil),
      (1, Seq(25)) -> None,
      (-1, Seq(25)) -> None,
      (25, Seq(25)) -> Some(Seq(25)),
      (50, Seq(25)) -> None,
      (50, Seq(25, 25)) -> Some(Seq(25, 25)),
      (50, Seq(25, 10, 10, 10, 5)) -> Some(Seq(25, 10, 10, 5)),
      (50, Seq(25, 10, 10, 10, 10, 5)) -> Some(Seq(25, 10, 10, 5)),
      (50, Seq(25, 10, 10, 10, 10, 10)) -> Some(Seq(10, 10, 10, 10, 10))
    ).foreach {
      case (givenMoneyAndAvailCoins, expectedResult) =>
        givenMoneyAndAvailCoins match {
          case (givenMoney, availCoins) =>
            VendorMachineCalc.countTheMostOptimalChange(givenMoney, availCoins) shouldEqual expectedResult
        }
    }
  }
}
