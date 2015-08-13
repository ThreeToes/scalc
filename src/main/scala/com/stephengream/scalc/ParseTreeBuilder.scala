/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

trait ParseTreeBuilder {
  def buildTree(input: List[ExpressionToken]) : Expression
}

class DefaultTreeBuilder extends ParseTreeBuilder{
  def buildTree(input: List[ExpressionToken]) : Expression ={
    buildTreePrime(input, Nil)
  }
  
  private def buildTreePrime(input: List[ExpressionToken], output: List[Expression]) 
    : Expression = input match {
    case Nil => {
        if(output.length > 1){
          throw new UnrecognisedTokenException("Unmatched expressions!")
        }
        output(0)
    }
    case ValueToken(x) :: xs => buildTreePrime(xs, ValueExpression(x) :: output)
    case SubtractToken() :: xs => {
        if(output.length < 2){
          throw new UnrecognisedTokenException("Unmatched expressions!")
        }
        val y :: x :: os = output
        buildTreePrime(xs, SubtractExpression(x, y) :: os)
    }
    case AddToken() :: xs => {
        if(output.length < 2){
          throw new UnrecognisedTokenException("Unmatched expressions!")
        }
        val y :: x :: os = output
        buildTreePrime(xs, AddExpression(x, y) :: os)
    }
    case MultiplyToken() :: xs => {
        if(output.length < 2){
          throw new UnrecognisedTokenException("Unmatched expressions!")
        }
        val y :: x :: os = output
        buildTreePrime(xs, MultiplyExpression(x, y) :: os)
    }
    
    case DivisionToken() :: xs => {
        if(output.length < 2){
          throw new UnrecognisedTokenException("Unmatched expressions!")
        }
        val y :: x :: os = output
        buildTreePrime(xs, DivisionExpression(x, y) :: os)
    }
    case x => throw new UnrecognisedTokenException(s"Can't handle token '$x'!")
  }
}
