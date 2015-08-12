/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

object MainApplication extends App {
  def printTokens(x : List[ExpressionToken]) : Unit = {
   x.foreach(y => printToken(y))
   println()
  }
  
  def printToken(tok : ExpressionToken) = tok match {
    case AddToken() => print(" Operator + ")
    case SubtractToken() => print(" Operator - ")
    case ValueToken(y) => print(s"Double $y")
    case _ => print("unrecognised")
  }
  println("Hello, world!")
  var lexer = new DefaultLexer
  var tokenList = lexer.tokenise("11+1")
  printTokens(tokenList)
}
