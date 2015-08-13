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
  def checkDouble() : Unit = {
    var input = List[ExpressionToken](SubtractToken(), SubtractToken(),ValueToken(12))
    var stripped = detector.makeNumbersNegative(input)
    stripped match {
      case SubtractToken() :: ValueToken(-12) :: Nil =>
      case _ => throw new RuntimeException("Value was not negated!")
    }
  }
}
