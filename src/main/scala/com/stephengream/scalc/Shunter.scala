/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

/**
 * Classes that implement this trait should shunt the tokens according
 * to Djisktra's shunting yard algorithm
 */
import scala.collection.mutable.Stack

trait Shunter {
  /**
   * Shunt the tokens into a different order
   * @param tokens the raw list of tokens
   */
  def shuntTokens(tokens: List[ExpressionToken]) : List[ExpressionToken]
}

class DefaultShunter extends Shunter {
  def shuntTokens(tokens: List[ExpressionToken]) : List[ExpressionToken] = {
    shuntTokensPrime(tokens, Nil)
  }
  
  private def finalOpStack(operatorStack: List[OperatorToken]) : List[ExpressionToken] = operatorStack match{
    case Nil => Nil
    case LeftParenthesisToken() :: xs => throw new UnmatchedParenthesisException("Unmatched left parenthesis!")
    case x :: xs => x :: finalOpStack(xs)
  }
    
  private def shuntTokensPrime(tokens : List[ExpressionToken], operatorStack: List[OperatorToken]) : List[ExpressionToken] = tokens match{
    case Nil => finalOpStack(operatorStack)
    case ValueToken(x) :: xs => ValueToken(x) :: shuntTokensPrime(xs, operatorStack)
    case LeftParenthesisToken() :: xs => {
        shuntTokensPrime(xs, LeftParenthesisToken() :: operatorStack)
    }
    case RightParenthesisToken() :: xs => {
        operatorStack match {
          case Nil => throw new UnmatchedParenthesisException("Unmatched right parenthesis!")
          case LeftParenthesisToken() :: ops => shuntTokensPrime(xs, ops)
          case op :: ops => op :: shuntTokensPrime(tokens, ops) 
        }
    }
    case (op: OperatorToken) :: xs => {
        if(operatorStack.length > 0 && operatorPrecedence(operatorStack(0), op)){
          var top::stack = operatorStack
          return top :: shuntTokensPrime(tokens, stack)
        }
        return shuntTokensPrime(xs, op :: operatorStack)
    }
    case x :: xs => throw new UnrecognisedTokenException(x.toString)
  }
      
  private def shuntParentheses(operatorStack: List[OperatorToken]) : List[ExpressionToken] = {
    List[ExpressionToken]()
  }
  
  /**
   * Whether x > y in precedence
   */
  private def operatorPrecedence(x: OperatorToken, y: OperatorToken) : Boolean = (x, y) match{
    case (x: MdToken, y: MdToken) => true
    case (x: MdToken, y: AsToken) => true
    case (x: AsToken, y: AsToken) => true
    case _ => false
  }
}