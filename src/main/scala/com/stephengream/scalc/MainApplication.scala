/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc
import Expressions._

object MainApplication extends App {
  def printTokens(x : List[ExpressionToken]) : Unit = {
   x.foreach(y => printToken(y))
   println()
  }
  
  def printToken(tok : ExpressionToken) = tok match {
    case x:OperatorToken => print(s" $x ")
    case ValueToken(y) => print(s" Double $y ")
    case _ => print("unrecognised")
  }
  println("Hello, world!")
  var lexer = new DefaultLexer
  var parseTreeBuilder = new DefaultTreeBuilder
  var shunter = new DefaultShunter
  var tokens = lexer.tokenise("11+1")
  var shunted = shunter.shuntTokens(tokens)
  printTokens(shunted)
  var tree = parseTreeBuilder.buildTree(shunted)
  println(evaluate(tree))
}
