/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

abstract class Expression
case class ValueExpression(value: Double) extends Expression
case class AddExpression(left: Expression, right: Expression) extends Expression
case class SubtractExpression(left: Expression, right: Expression) extends Expression
case class MultiplyExpression(left: Expression, right: Expression) extends Expression
case class DivisionExpression(left: Expression, right: Expression) extends Expression

object Expressions{
  def evaluate(e: Expression) : Double = e match{
    case ValueExpression(x) => x
    case AddExpression(x, y) => evaluate(x) + evaluate(y)
    case SubtractExpression(x, y) => evaluate(x) - evaluate(y)
    case MultiplyExpression(x, y) => evaluate(x) * evaluate(y)
    case DivisionExpression(x, y) => evaluate(x) / evaluate(y)
  }
}