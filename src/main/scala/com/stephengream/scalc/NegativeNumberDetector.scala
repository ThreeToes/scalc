/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

class NegativeNumberDetector {
  def makeNumbersNegative(input: List[ExpressionToken]) : List[ExpressionToken] = input match{
    //Special case for the start
    case SubtractToken() :: ValueToken(x) :: xs => ValueToken(-x) :: makeNumbersNegativePrime(xs)
    case AddToken() :: ValueToken(x) :: xs => ValueToken(x) :: makeNumbersNegativePrime(xs)
    case _ => makeNumbersNegativePrime(input)
  }
  
  private def makeNumbersNegativePrime(input: List[ExpressionToken]) : List[ExpressionToken] = input match {
    case Nil => Nil
    case (a: OperatorToken) :: SubtractToken() :: ValueToken(x) :: xs =>  a :: ValueToken(-x) :: makeNumbersNegativePrime(xs)
    case x :: xs => x :: makeNumbersNegativePrime(xs) 
  }
}
