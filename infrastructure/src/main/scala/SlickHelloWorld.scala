package test

import slick.driver.H2Driver.api._

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class Tests(tag: Tag) extends Table[(Int, String)](tag, "TEST") {
  def id = column[Int]("TEST_ID", O.PrimaryKey)
  def title = column[String]("TEST_TITLE")
  def * = (id, title)
}

object SlickHelloWorld {

  def main(args: Array[String]): Unit = {
    val tests = TableQuery[Tests]

    val db = Database.forConfig("inMemoryDb")

    try {

      val setup = DBIO.seq(
        tests.schema.create,
        tests += (1, "first test"),
        tests += (2, "second test")
      )

      println("store")
      val setupFuture = db.run(setup);
      Await.result(setupFuture, 5000 millis)

      println("query")
      db.run(tests.result).map(r => r.foreach(t =>
        println("entity with id " + t._1 + " have text " + t._2)
      )
      )
      Thread.sleep(5000)

      println("finished")
    } finally db.close
  }

}


