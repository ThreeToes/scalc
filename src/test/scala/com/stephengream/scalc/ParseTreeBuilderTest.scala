/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

import org.junit.{Test, Before}

class ParseTreeBuilderTest {
  private var parseTreeBuilder : ParseTreeBuilder = null
  
  @Before
  def setup() : Unit = {
    parseTreeBuilder = new DefaultTreeBuilder
  }
  
  @Test
  def checkNumber() : Unit = {
    val input = List[ExpressionToken](ValueToken(12))
    var ret = parseTreeBuilder.buildTree(input)
    ret match {
      case ValueExpression(12) =>
      case _ => throw new RuntimeException("Value expression wasn't returned!")
    }
  }
  
  @Test
  def checkSimple() : Unit = {
    val input = List[ExpressionToken](ValueToken(12),ValueToken(12), AddToken())    
    var ret = parseTreeBuilder.buildTree(input)
    ret match {
      case AddExpression(ValueExpression(12), ValueExpression(12)) =>
      case _ => throw new RuntimeException("Value expression wasn't returned!")
    }
  }
  
  @Test
  def checkSimpleMultipleOperators() : Unit = {
    val input = List[ExpressionToken](ValueToken(1),ValueToken(2), ValueToken(3), AddToken(), SubtractToken())    
    var ret = parseTreeBuilder.buildTree(input)
    ret match {
      case SubtractExpression(ValueExpression(1), AddExpression(ValueExpression(2), ValueExpression(3))) =>
      case _ => throw new RuntimeException("Value expression wasn't returned! \n " + ret)
    }
  }
  
  @Test
  def checkBuildSubstract() : Unit = {
    val input = ValueToken(2) :: ValueToken(1) :: SubtractToken() :: Nil   
    var ret = parseTreeBuilder.buildTree(input)
    ret match {
      case SubtractExpression(ValueExpression(2), ValueExpression(1)) =>
      case _ => throw new RuntimeException("Value expression wasn't returned! \n" + ret)
    }
  }
  
  @Test
  def checkBuildAdd() : Unit = {
    val input = ValueToken(2) :: ValueToken(1) :: AddToken() :: Nil   
    var ret = parseTreeBuilder.buildTree(input)
    ret match {
      case AddExpression(ValueExpression(2), ValueExpression(1)) =>
      case _ => throw new RuntimeException("Value expression wasn't returned! \n" + ret)
    }
  }
  
  @Test
  def checkBuildMultiply() : Unit = {
    val input = ValueToken(2) :: ValueToken(1) :: MultiplyToken() :: Nil   
    var ret = parseTreeBuilder.buildTree(input)
    ret match {
      case MultiplyExpression(ValueExpression(2), ValueExpression(1)) =>
      case _ => throw new RuntimeException("Value expression wasn't returned! \n" + ret)
    }
  }
  
  @Test
  def checkBuildDivide() : Unit = {
    val input = ValueToken(2) :: ValueToken(1) :: DivisionToken() :: Nil   
    var ret = parseTreeBuilder.buildTree(input)
    ret match {
      case DivisionExpression(ValueExpression(2), ValueExpression(1)) =>
      case _ => throw new RuntimeException("Value expression wasn't returned! \n" + ret)
    }
  }
}
