package mixin

trait MixinTrait2 extends MixinTrait {
  override def greet = {
    println("these a killer on the road")
    super.greet()
  }
  def greet2 = println("into this house we are born")
}
