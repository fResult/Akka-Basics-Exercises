package com.lightbend.training

import akka.actor.typed.ActorSystem
import com.lightbend.training.Barista.OrderCoffee
import com.lightbend.training.Coffee.{Akkaccino, CaffeJava, MochaPlay}

import java.io.IOException
import scala.io.StdIn.readLine
import scala.util.control.Exception

object Main {

  def main(args: Array[String]): Unit = {
    val actorSystem: ActorSystem[OrderCoffee] = ActorSystem(Barista(), "barista")

    actorSystem ! OrderCoffee("Bart", Akkaccino)
    actorSystem ! OrderCoffee("Lisa", MochaPlay)
    actorSystem ! OrderCoffee("John", CaffeJava)

    println(">>> Press ENTER to exit <<<")
    readLine()
    Exception.ignoring(classOf[IOException])

    actorSystem.terminate()
  }
}
