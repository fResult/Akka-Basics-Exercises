package com.lightbend.training

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.*
import com.lightbend.training.CoffeeMachine.CoffeeMachineCommand.{BrewCoffee, PickupCoffee}

import scala.util.*

object CoffeeMachine {
  def apply(): Behavior[CoffeeMachineCommand] = idle()

  // Use handleBrewCoffee when receiving a BrewCoffee message
  private def idle(): Behavior[CoffeeMachineCommand] =
    Behaviors.setup { context =>
      context.log.info("CoffeeMachine: IDLE")
      Behaviors.receiveMessage {
        case BrewCoffee(coffee) => handleBrewCoffee(context, coffee)
        // Can't pick up coffee until coffee is ready, stay in same behavior (equivalent to ignore the message)
        case PickupCoffee => Behaviors.same
      }
    }

  private def coffeeReady(): Behavior[CoffeeMachineCommand] =
    Behaviors.setup { context =>
      context.log.info("CoffeeMachine: Coffee is ready")
      Behaviors.receiveMessage {
        case BrewCoffee(_) => Behaviors.same
        case PickupCoffee => idle()
      }
    }

  private def handleBrewCoffee(context: ActorContext[CoffeeMachineCommand], coffee: Coffee): Behavior[CoffeeMachineCommand] = {
    context.log.info(s"CoffeeMachine: Brewing 1 $coffee")

    // Warn: Don't Thread.sleep in Akka actors, it utilizes a thread from the Thread pool.
    // We will see how to replace Thread.sleep by proper non-blocking scheduling in a further exercise.
    Try(Thread.sleep(10000)) match {
      case Failure(e: InterruptedException) => e.printStackTrace()
      case _ =>
    }

    coffeeReady()
  }

  // sealed trait CoffeeMachineCommand
  // final case class BrewCoffee(coffee: Coffee) extends CoffeeMachineCommand
  // case object PickupCoffee extends CoffeeMachineCommand
  enum CoffeeMachineCommand {
    case BrewCoffee(coffee: Coffee)
    case PickupCoffee
  }
}
