/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

import org.junit.Test
import org.junit.Before
import org.junit.Assert._

class ShunterTest {
  private var shunter : DefaultShunter = null
  
  @Before 
  def setup(){
    shunter = new DefaultShunter
  }
  
  @Test
  def simpleNumberTest() : Unit = {
    var tokens = List[ExpressionToken](ValueToken(12))
    var shunted = shunter.shuntTokens(tokens)
    assertEquals(1, shunted.length)
    shunted(0) match {
      case ValueToken(12) => 
      case _ => throw new UnrecognisedTokenException("Token has been shunted out of existence!")
    }
  }
  
  @Test
  def simpleOperatorTest() : Unit = {
    var tokens = List[ExpressionToken](ValueToken(12), AddToken(), ValueToken(12))
    var shunted = shunter.shuntTokens(tokens)
    assertEquals(3, shunted.length)
    shunted(0) match {
      case ValueToken(12) => 
      case _ => throw new UnrecognisedTokenException("Token has been shunted out of existence!")
    }
    shunted(1) match {
      case ValueToken(12) => 
      case _ => throw new UnrecognisedTokenException("Token has been shunted out of existence!")
    }
    shunted(2) match {
      case AddToken() => 
      case _ => throw new UnrecognisedTokenException("Token has been shunted out of existence!")
    }
  }
  
  @Test
  def wikiTest() : Unit = {
    var tokens = List[ExpressionToken](ValueToken(12), AddToken(), ValueToken(12), MultiplyToken(), ValueToken(12), SubtractToken(), ValueToken(12))
    var shunted = shunter.shuntTokens(tokens)
    assertEquals(7, shunted.length)
    shunted match {
      case ValueToken(12) :: ValueToken(12) :: ValueToken(12) :: MultiplyToken() :: AddToken() :: ValueToken(12) :: SubtractToken() :: Nil => 
      case _ => throw new RuntimeException("Sequence incorrect! " + shunted.toString)
    }
  }
  
  @Test
  def matchedParenTest() : Unit = {
    var input = List[ExpressionToken](LeftParenthesisToken(),ValueToken(12), SubtractToken(), ValueToken(12), RightParenthesisToken(), DivisionToken(), ValueToken(12))
    var shunted = shunter.shuntTokens(input)
    assertEquals(5, shunted.length)
    shunted match {
      case ValueToken(12) :: ValueToken(12) :: SubtractToken() :: ValueToken(12) :: DivisionToken() :: Nil =>  
      case _ => throw new RuntimeException("Sequence incorrect! " + shunted.toString)
    }
  }
  
  @Test(expected = classOf[UnmatchedParenthesisException])
  def unmatchedLeftParen() : Unit = {
    var input = List[ExpressionToken](LeftParenthesisToken(), ValueToken(12))
    shunter.shuntTokens(input)
  }
  
  @Test(expected = classOf[UnmatchedParenthesisException])
  def unmatchedRightParen() : Unit = {
    var input = List[ExpressionToken](ValueToken(12), RightParenthesisToken())
    shunter.shuntTokens(input)
  }
}
