package com.newsreader.feeds.infrastructure

import scala.concurrent.ExecutionContext.Implicits.global

import com.newsreader.feeds.domain.{Feed, FeedDao}
import slick.driver.H2Driver.api._
import slick.jdbc.meta.MTable

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class Feeds(tag: Tag) extends Table[Feed](tag, "FEEDS") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def address = column[String]("ADDRESS")
  def * = (id, name, address) <> (Feed.tupled, Feed.unapply)
}

class FeedDbDao extends FeedDao{

  // TODO should be injected from outside to avoid H2 dependency here
  lazy val db = Database.forConfig("inMemoryDb")
  lazy val table = TableQuery[Feeds]


  // TODO schema autocreate
  Await.result(
    db.run(MTable.getTables).flatMap(
      v => {
        val names = v.map(m => m.name.name)
        println(names)
        val create = Seq(table).filter(
          t => !names.contains(t.baseTableRow.tableName)
        ).map(_.schema.create)


        db.run(DBIO.sequence(create))
      }
    ), Duration.Inf)


  override def store(feed: Feed): Unit = {
    // TODO allow to return Future
    Await.result(db.run(table += feed), Duration.Inf)
    println("stored!")
  }
}


