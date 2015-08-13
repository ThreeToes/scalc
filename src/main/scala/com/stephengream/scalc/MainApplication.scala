/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc
import Expressions._
import scala.io.StdIn.readLine

object MainApplication extends App {
  var lexer = new DefaultLexer
  var parseTreeBuilder = new DefaultTreeBuilder
  var shunter = new DefaultShunter
  var parser = new DefaultParser(lexer, shunter, parseTreeBuilder)

  if (args.length == 0) {
    prompt
  } else {
    printVal(args.mkString)
  }
  def printVal(in: String): Unit = {
    println(evaluate(parser.parse(in)))
  }

  def prompt(): Unit = {
    println("Type 'exit' to quit")
    while (true) {
      val in = readLine("> ")
      in match {
        case "exit" => return
        case _ => {
          try {
            printVal(in)
          } catch {
            case e: Throwable => {
              println(s"Error: $e.toString")
          }
            }
        }
      }
    }
  }
}
