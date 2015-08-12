/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephengream.scalc

import org.junit.Test
import org.junit.Before
import org.junit.Assert._

class DefaultLexerTest {
  private var lexer: DefaultLexer = null

  @Before
  def setup(): Unit = {
    lexer = new DefaultLexer
  }

  @Test
  def checkNumbersOnly(): Unit = {
    var tokens = lexer.tokenise("12")
    tokens match {
      case ValueToken(12) :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkAddOnly() : Unit ={
    var tokens = lexer.tokenise("+")
    tokens match {
      case AddToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkSubtractOnly() : Unit = {
    var tokens = lexer.tokenise("-")
    tokens match {
      case SubtractToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkMultiplyOnly() : Unit = {
    var tokens = lexer.tokenise("*")
    tokens match {
      case MultiplyToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkDivideOnly() : Unit = {
    var tokens = lexer.tokenise("/")
    tokens match {
      case DivisionToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  
  @Test
  def checkLeftParenthesesOnly() : Unit = {
    var tokens = lexer.tokenise("(")
    tokens match {      
      case LeftParenthesisToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkRightParenthesesOnly() : Unit = {
    var tokens = lexer.tokenise(")")
    tokens match {
      case RightParenthesisToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkNumbersWithSpace(): Unit = {
    var tokens = lexer.tokenise("   12")
    tokens match {
      case ValueToken(12) :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkAddWithSpace() : Unit ={
    var tokens = lexer.tokenise("     +")
    tokens match {
      case AddToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkSubtractWithSpace() : Unit = {
    var tokens = lexer.tokenise("    -")
    tokens match {
      case SubtractToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkMultiplyWithSpace() : Unit = {
    var tokens = lexer.tokenise("     *")
    tokens match {
      case MultiplyToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkDivideWithSpace() : Unit = {
    var tokens = lexer.tokenise("    /")
    tokens match {
      case DivisionToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  
  @Test
  def checkLeftParenthesesWithSpace() : Unit = {
    var tokens = lexer.tokenise("     (")
    tokens match {      
      case LeftParenthesisToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkRightParenthesesWithSpace() : Unit = {
    var tokens = lexer.tokenise("      )")
    tokens match {
      case RightParenthesisToken() :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkSimple() : Unit = {
    var tokens = lexer.tokenise("1+1")
    tokens match{
      case ValueToken(1) :: AddToken() :: ValueToken(1) :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkSimpleWithSpaces() : Unit = {
    var tokens = lexer.tokenise(" 1 + 1 ")
    tokens match{
      case ValueToken(1) :: AddToken() :: ValueToken(1) :: Nil =>
      case x => throw new RuntimeException("Sequence is incorrect! " + x)
    }
  }
  
  @Test
  def checkComplex() : Unit = {
    var tokens = lexer.tokenise("1+1-22*(3/2)")
    tokens match {
      case ValueToken(1) :: AddToken() :: ValueToken(1) :: SubtractToken() :: 
        ValueToken(22) :: MultiplyToken() :: LeftParenthesisToken() :: 
        ValueToken(3) :: DivisionToken() :: ValueToken(2) :: 
        RightParenthesisToken() :: Nil => 
      case x => throw new RuntimeException("Sequnce incorrect! " + tokens)
    }
  }
    
  @Test
  def checkComplexWithSpaces() : Unit = {
    var tokens = lexer.tokenise(" 1  + 1 -   22*(3 /     2)")
    tokens match {
      case ValueToken(1) :: AddToken() :: ValueToken(1) :: SubtractToken() :: 
        ValueToken(22) :: MultiplyToken() :: LeftParenthesisToken() :: 
        ValueToken(3) :: DivisionToken() :: ValueToken(2) :: 
        RightParenthesisToken() :: Nil => 
      case x => throw new RuntimeException("Sequnce incorrect! " + tokens)
    }
  }
  
  @Test(expected = classOf[UnrecognisedTokenException])
  def badSpaceString() : Unit = {
    lexer.tokenise("1 .23232")
  }
  
  @Test(expected = classOf[UnrecognisedTokenException])
  def alphanumerics() : Unit = {
    lexer.tokenise("abd")
  }
}
