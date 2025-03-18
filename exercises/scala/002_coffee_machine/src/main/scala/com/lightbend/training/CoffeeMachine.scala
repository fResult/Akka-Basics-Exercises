package com.lightbend.training

import scala.util._
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl._


object CoffeeMachine {
  def apply(): Behavior[CoffeeMachineCommand] = idle()

  // Use handleBrewCoffee when receiving a BrewCoffee message
  private def idle(): Behavior[CoffeeMachineCommand] =
    Behaviors.setup { context =>
      context.log.info("CoffeeMachine: IDLE")
      ???
    }

  private def coffeeReady(): Behavior[CoffeeMachineCommand] =
    Behaviors.setup { context =>
      context.log.info("CoffeeMachine: Coffee is ready")
      ???
    }

  private def handleBrewCoffee(context: ActorContext[CoffeeMachineCommand], coffee: Coffee): Behavior[CoffeeMachineCommand] = {
    context.log.info(s"CoffeeMachine: Brewing 1 $coffee")

    // Warn: Don't Thread.sleep in Akka actors, it utilizes a thread from the Thread pool.
    // We will see how to replace Thread.sleep by proper non-blocking scheduling in a further exercise.
    Try(Thread.sleep(10000)) match {
      case Failure(e: InterruptedException) => e.printStackTrace()
      case _ =>
    }

    ???
  }

  sealed trait CoffeeMachineCommand

  final case class BrewCoffee(coffee: Coffee) extends CoffeeMachineCommand

  final case object PickupCoffee extends CoffeeMachineCommand

}
