import sbt._

/**
 * [[CompilerPlugins]] shared/used by all Scala modules.
 */
object CompilerPlugins {

  // Compiler Plugins
  val betterForComp = "com.olegpy" %% "better-monadic-for" % V.betterForComp

  // Compiler Plugin Versions
  object V {
    val betterForComp = "0.3.1"
  }
}
