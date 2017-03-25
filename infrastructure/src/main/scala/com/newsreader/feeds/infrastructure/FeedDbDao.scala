package com.newsreader.feeds.infrastructure


import com.newsreader.feeds.domain.{Feed, FeedDao}
import slick.jdbc.JdbcProfile

import scala.language.higherKinds

abstract class FeedDbDao(val driver: JdbcProfile) extends FeedDao {

  import driver.api._

  class Feeds(tag: Tag) extends Table[Feed](tag, "FEEDS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def address = column[String]("ADDRESS")
    def * = (id, name, address) <> (Feed.tupled, Feed.unapply)
  }

  protected lazy val table = TableQuery[Feeds]

  protected def loadDBIO: DBIO[Seq[Feed]] = table.result

  protected def storeDBIO(feed: Feed): DBIO[Long] = table returning table.map(_.id) += feed
}

