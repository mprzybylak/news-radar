package com.newsreader.feeds.infrastructure

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

import com.newsreader.feeds.domain.Feed
import slick.driver.H2Driver
import slick.driver.H2Driver.api._
import slick.jdbc.meta.MTable

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class FeedH2Dao @Inject() (db: Database) extends FeedDbDao(H2Driver) {
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

  def store(feed: Feed) : Long = {
    Await.result(
      db.run(storeDBIO(feed)),
      Duration.Inf
    )
  }

  def load: Seq[Feed] = {
    Await.result(
      db.run(loadDBIO),
      Duration.Inf
    )
  }

}
