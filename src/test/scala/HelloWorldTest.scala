import org.scalatest.{Matchers, FlatSpec}

class HelloWorldTest extends FlatSpec with Matchers {

  "Hello world example" should "return hello world string" in {
    val hw = new HelloWorld
    hw.helloWorld() should be ("Hello, Daj sie poznac!")
  }

}
