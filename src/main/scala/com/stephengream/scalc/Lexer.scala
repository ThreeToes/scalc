/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

/**
 * Trait for a lexer
 */
trait Lexer {
  /**
   * Tokenise a string
   */
  def tokenise(x: String) : List[ExpressionToken]
}

class DefaultLexer extends Lexer{
  private val numberRegex = """\s*([0-9]+\.[0-9]+|[0-9]+)(.*)""".r
  private val addToken = """\s*(\+)(.*)""".r
  private val subtractToken = """\s*(\-)(.*)""".r
  private val multiplyToken = """\s*(\*)(.*)""".r
  private val divideToken = """\s*(\/)(.*)""".r
  private val leftParenthesis = """\s*(\()(.*)""".r
  private val rightParenthesis = """\s*(\))(.*)""".r
  private val whitespace = """(\s*)""".r
  
  def tokenise(input : String) : List[ExpressionToken] =
    input match{
      case numberRegex(x,xs) => {
          new ValueToken(x.toDouble) :: tokenise(xs)
        }
      case addToken(x,xs) => {
          new AddToken :: tokenise(xs)
      }
      case subtractToken(x,xs) => {
          new SubtractToken :: tokenise(xs)
      }
      case multiplyToken(x, xs) => {
          new MultiplyToken :: tokenise(xs)
      }
      case divideToken(x, xs) => {
          new DivisionToken :: tokenise(xs)
      }
      case leftParenthesis(x, xs) => {
          new LeftParenthesisToken :: tokenise(xs)
      }
      case rightParenthesis(x, xs) => {
          new RightParenthesisToken :: tokenise(xs)
      }
      case whitespace(x) => {
          List[ExpressionToken]()
      }
      case _ => {
          throw new UnrecognisedTokenException(s"Unrecognised sequence '$input'")
        }
    }
  
}