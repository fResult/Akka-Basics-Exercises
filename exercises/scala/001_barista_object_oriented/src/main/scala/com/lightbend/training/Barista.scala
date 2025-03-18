package com.lightbend.training

import scala.collection._
import akka.actor.typed.Behavior

object Barista {

  def apply(): Behavior[OrderCoffee] = ???

  def printOrders(orders: Set[(String, Coffee)]): String = {
    val formattedOrders = orders.map(order => s"${order._1}->${order._2}")
        .reduce((acc, s) => acc + "," + s)
    s"[$formattedOrders]"
  }

  final case class OrderCoffee(whom: String, coffee: Coffee)
}
