/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

/**
 * Expression token
 */
abstract class ExpressionToken
/**
 * Token for operators
 */
abstract class OperatorToken extends ExpressionToken
/**
 * Token for Multiply/Divide
 */
abstract class MdToken extends OperatorToken

abstract class AsToken extends OperatorToken
/**
 * Value token, for values such as "11.8"
 */
case class ValueToken(value: Double) extends ExpressionToken
/**
 * Token for "("
 */
case class LeftParenthesisToken() extends OperatorToken
/**
 * Token for ")"
 */
case class RightParenthesisToken() extends OperatorToken
/**
 * Token for "+"
 */
case class AddToken() extends AsToken
/**
 * Token for "-"
 */
case class SubtractToken() extends AsToken
/**
 * Token for "*"
 */
case class MultiplyToken() extends MdToken
/**
 * Token for "/"
 */
case class DivisionToken() extends MdToken