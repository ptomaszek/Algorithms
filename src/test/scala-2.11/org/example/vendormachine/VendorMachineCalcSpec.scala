package org.example.vendormachine

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

  it should "find all the possible changes" in {
    Map(
      (-1, Seq(5)) ->
        Nil,
      (0, Seq(5)) ->
        Seq(Nil),
      (50, Seq(5)) ->
        Nil,
      (50, Seq(25, 10, 10, 10, 5, 5, 5, 5, 5, 5)) ->
        Set(
          Seq(25, 10, 10, 5),
          Seq(25, 10, 5, 5, 5),
          Seq(25, 5, 5, 5, 5, 5),
          Seq(10, 10, 10, 5, 5, 5, 5),
          Seq(10, 10, 5, 5, 5, 5, 5, 5)
        ),
      (50, Seq(5, 10, 10, 10, 5, 5, 10, 15, 5)) ->
        Set(
          Seq(10, 10, 10, 5, 5, 5, 5),
          Seq(10, 10, 10, 10, 5, 5),
          Seq(15, 10, 10, 10, 5),
          Seq(15, 10, 10, 5, 5, 5)
        )
    ).foreach {
      case (givenMoneyAndAvailCoins, expectedResult) =>
        givenMoneyAndAvailCoins match {
          case (givenMoney, availCoins) =>
            VendorMachineCalc.countPossibleChanges(givenMoney, availCoins) should contain theSameElementsAs expectedResult
        }
    }
  }
}
