package mixin

trait MixinTrait extends CAbstract {
  override def greet(): Unit = {
    println("riders")
    super.greet()
  }
}
