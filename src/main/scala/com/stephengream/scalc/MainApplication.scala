/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stephengream.scalc
import Expressions._
import scala.io.StdIn._

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
  
  def printHelp() : Unit = {
    println("Please invoke with a valid equation")
  }
  
  def printVal(in: String): Unit = {
    try{
      println(evaluate(parser.parse(in)))
    }catch{
      case e: Exception => printHelp
    }
  }

  def prompt(): Unit = {
    println
    println("Type 'exit' to quit")
    while (true) {
      val in = readLine("\r\n> ")
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
