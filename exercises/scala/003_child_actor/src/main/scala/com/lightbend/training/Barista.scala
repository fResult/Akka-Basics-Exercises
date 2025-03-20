package com.lightbend.training

import akka.actor.typed.*
import akka.actor.typed.scaladsl.*
import com.lightbend.training.CoffeeMachine.{BrewCoffee, CoffeeMachineCommand, PickupCoffee}

import scala.collection.*


object Barista {

  def apply(): Behavior[OrderCoffee] =
    Behaviors.setup(BaristaBehavior(_))

  def printOrders(orders: Set[(String, Coffee)]): String = {
    val formattedOrders = orders.map(order => s"${order._1}->${order._2}")
        .reduce((acc, s) => acc + "," + s)
    s"[$formattedOrders]"
  }

  final case class OrderCoffee(whom: String, coffee: Coffee)

  private class BaristaBehavior(context: ActorContext[OrderCoffee]) extends AbstractBehavior[OrderCoffee](context) {
    private val orders: mutable.Map[String, Coffee] = mutable.Map()

    // spawn a coffee machine and get a reference to the coffee-machine child actor,
    // allowing us to send messages to coffee machine
    private val coffeeMachineActorRef: ActorRef[CoffeeMachineCommand] = context.spawn(CoffeeMachine(), "coffee-machine")

    override def onMessage(message: OrderCoffee): Behavior[OrderCoffee] = {
      message match {
        case OrderCoffee(whom, coffee) =>
          orders.put(whom, coffee)
          context.log.info(s"Orders: ${printOrders(orders.toSet)}")
          coffeeMachineActorRef ! BrewCoffee(coffee)
          coffeeMachineActorRef ! PickupCoffee
          this
      }
    }
  }
}
