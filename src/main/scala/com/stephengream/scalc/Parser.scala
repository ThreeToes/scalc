/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

/**
 * Parser trait
 */
trait Parser {
  /**
   * Parse a string to return an expression tree
   */
  def parse(x: String) : Expression
}

class DefaultParser(lexer: Lexer, shunter: Shunter, parseTreeBuilder: ParseTreeBuilder) extends Parser{
  private val negator = new NegativeNumberDetector
  @Override
  def parse(x: String) : Expression = {
    var tokens = negator.makeNumbersNegative(lexer.tokenise(x))
    var shunted = shunter.shuntTokens(tokens)
    parseTreeBuilder.buildTree(shunted)
  }
}
