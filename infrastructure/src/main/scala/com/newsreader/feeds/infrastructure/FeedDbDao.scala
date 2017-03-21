package com.newsreader.feeds.infrastructure

import javax.inject.Inject

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

// TODO abstract from driver
class FeedDbDao @Inject() (db: Database) extends FeedDao {

  lazy val table = TableQuery[Feeds]

  Await.result(
    db.run(MTable.getTables).flatMap(
      v => {
        val names = v.map(m => m.name.name)
        val create = Seq(table).filter(
          t => !names.contains(t.baseTableRow.tableName)
        ).map(_.schema.create)
        db.run(DBIO.sequence(create))
      }
    ), Duration.Inf)


  override def store(feed: Feed): Long =
    Await.result(
      db.run(
        table returning table.map(_.id)  += feed
      ),
      Duration.Inf
    )

  override def load: Seq[Feed] = Await.result(db.run(table.result), Duration.Inf)

}


