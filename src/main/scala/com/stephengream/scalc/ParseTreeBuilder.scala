/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

trait ParseTreeBuilder {
  def buildTree(input: List[ExpressionToken]) : Expression
}

class DefaultTreeBuilder{
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
    case ValueToken(x) :: xs => buildTreePrime(xs, output :+ ValueExpression(x))
    case (x: OperatorToken) :: xs => buildTreePrime(xs, getExpression(output, x))
    case x => throw new UnrecognisedTokenException(s"Can't handle token '$x'!")
  }
  
  private def getExpression(output : List[Expression], op: OperatorToken) : List[Expression] = op match{
    case AddToken() => {
        if(output.length < 2){
          throw new RuntimeException
        }
        var x :: y :: xs = output
        AddExpression(x, y) :: xs
    }
    case SubtractToken() => {
        if(output.length < 2){
          throw new RuntimeException
        }
        var x :: y :: xs = output
        SubtractExpression(x, y) :: xs
    }
    case DivisionToken() =>{
        if(output.length < 2){
          throw new RuntimeException
        }
        var x :: y :: xs = output
        DivisionExpression(x, y) :: xs
      }
    case MultiplyToken() => {
        if(output.length < 2){
          throw new RuntimeException
        }
        var x :: y :: xs = output
        MultiplyExpression(x, y) :: xs
    }
  }
}
