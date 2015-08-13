/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

import org.junit.{Test, Before}

class NegativeNumberTest {
  private var detector : NegativeNumberDetector = null
  
  @Before
  def setup() : Unit = {
    detector = new NegativeNumberDetector
  }
  
  @Test
  def checkSingle() : Unit = {
    var input = List[ExpressionToken](SubtractToken(),ValueToken(12))
    var stripped = detector.makeNumbersNegative(input)
    stripped match {
      case ValueToken(-12) :: Nil =>
      case _ => throw new RuntimeException("Value was not negated!")
    }
  }
  
  @Test
  def checkDoubleAtStart() : Unit = {
    var input = List[ExpressionToken](SubtractToken(), SubtractToken(),ValueToken(12))
    var stripped = detector.makeNumbersNegative(input)
    stripped match {
      case ValueToken(12) :: Nil =>
      case _ => throw new RuntimeException("Value was not negated!")
    }
  }
  
  @Test
  def checkDoubleInMiddle() : Unit = {
    var input = List[ExpressionToken](ValueToken(12),SubtractToken(), SubtractToken(),ValueToken(12))
    var stripped = detector.makeNumbersNegative(input)
    println("Ass " + stripped)
    stripped match {
      case ValueToken(12) :: AddToken() :: ValueToken(12) :: Nil =>
      case _ => throw new RuntimeException("Value was not negated!")
    }
  }
  
  @Test
  def checkOpeningBracket() : Unit = {
    var input = List[ExpressionToken](SubtractToken(), LeftParenthesisToken())
    var stripped = detector.makeNumbersNegative(input)
    stripped match {
      case ValueToken(-1) :: MultiplyToken() :: LeftParenthesisToken() :: Nil =>
      case _ => throw new RuntimeException("Value was not negated!")
    }
  }
  
  @Test
  def checkBracketed() : Unit = {
    var input = List[ExpressionToken](SubtractToken(), LeftParenthesisToken(), ValueToken(1), AddToken(), ValueToken(4), RightParenthesisToken())
    var stripped = detector.makeNumbersNegative(input)
    stripped match {
      case ValueToken(-1) :: MultiplyToken() :: LeftParenthesisToken() :: ValueToken(1) :: AddToken() :: ValueToken(4) :: RightParenthesisToken() :: Nil =>
      case _ => throw new RuntimeException("Value was not negated!")
    }
  }
}
