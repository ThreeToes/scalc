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
    val input = List[ExpressionToken](ValueToken(12),ValueToken(12), ValueToken(12), AddToken(), SubtractToken())    
    var ret = parseTreeBuilder.buildTree(input)
    ret match {
      case SubtractExpression(AddExpression(ValueExpression(12), ValueExpression(12)), ValueExpression(12)) =>
      case _ => throw new RuntimeException("Value expression wasn't returned!")
    }}
}
