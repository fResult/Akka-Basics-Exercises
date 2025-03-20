package com.lightbend.training

sealed trait Coffee

case object Akkaccino extends Coffee

case object CaffeJava extends Coffee

case object MochaPlay extends Coffee
