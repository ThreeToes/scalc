/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

import org.junit.{ Test, Before }
import Expressions._

class IntegrationTest {
  var lexer: Lexer = null
  var parseTreeBuilder: ParseTreeBuilder = null
  var shunter: Shunter = null
  var parser: Parser = null

  @Before
  def setup(): Unit = {
    lexer = new DefaultLexer
    parseTreeBuilder = new DefaultTreeBuilder
    shunter = new DefaultShunter
    parser = new DefaultParser(lexer, shunter, parseTreeBuilder)
  }

  @Test
  def checkSingleNumber(): Unit = {
    val parsed = parser.parse("1")
    var result = evaluate(parsed)
    result match {
      case 1 => 
      case x => throw new RuntimeException("Got back " + x)
    }
  }
  
  @Test
  def checkSimpleEquation() : Unit = {
    val parsed = parser.parse("2-1")
    println(parsed)
    var result = evaluate(parsed)
    result match {
      case 1 => 
      case x => throw new RuntimeException("Got back " + x + "\n " + parsed)
    }
  }
  
  @Test
  def checkBracketedValues() : Unit ={
    var result = evaluate(parser.parse("(1)"))
    result match {
      case 1 => 
      case x => throw new RuntimeException("Got back " + x)
    }
  }
  
  @Test
  def checkBracketedEquation() : Unit ={
    var result = evaluate(parser.parse("(3+1-1)"))
    result match {
      case 3 => 
      case x => throw new RuntimeException("Got back " + x)
    }
  }
  
  @Test
  def checkBracketedWithUnbracketedEquation() : Unit ={
    var result = evaluate(parser.parse("2*(3+1-1)/1"))
    result match {
      case 6 => 
      case x => throw new RuntimeException("Got back " + x)
    }
  }
}
