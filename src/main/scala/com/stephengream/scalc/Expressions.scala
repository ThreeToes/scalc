/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc

abstract class Expression
case class ValueExpression(value: Double) extends Expression
case class AddExpression(left: Expression, right: Expression) extends Expression
case class SubstractExpression(left: Expression, right: Expression) extends Expression
case class MultiplyExpression(left: Expression, right: Expression) extends Expression
case class DivisionExpression(left: Expression, right: Expression) extends Expression