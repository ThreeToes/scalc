/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

class NegativeNumberDetector {
  def makeNumbersNegative(input: List[ExpressionToken]) : List[ExpressionToken] = input match{
    //Special case for the start
    case SubtractToken() :: SubtractToken() :: xs =>
      makeNumbersNegative(xs)
    case AddToken() :: xs => 
      makeNumbersNegative(xs)
    case SubtractToken() :: ValueToken(x) :: xs => 
      ValueToken(-x) :: makeNumbersNegative(xs)
    case SubtractToken() :: LeftParenthesisToken() :: xs => 
      makeNumbersNegativePrime(ValueToken(-1) :: MultiplyToken() :: LeftParenthesisToken() ::xs) 
    case _ => makeNumbersNegativePrime(input)
  }
  
  private def makeNumbersNegativePrime(input: List[ExpressionToken]) : List[ExpressionToken] = input match {
    case Nil => Nil
    case SubtractToken() :: SubtractToken() :: xs => makeNumbersNegativePrime(AddToken() :: xs)
    case SubtractToken() :: AddToken() :: xs => makeNumbersNegativePrime(SubtractToken() ::xs)
    case AddToken() :: SubtractToken() :: xs => makeNumbersNegativePrime(SubtractToken() :: xs)
    case x :: xs => x :: makeNumbersNegativePrime(xs) 
  }
}
