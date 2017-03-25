package utils

import com.google.inject.AbstractModule
import com.newsreader.feeds.application.api.FeedService
import com.newsreader.feeds.application.impl.FeedServiceImpl
import com.newsreader.feeds.domain.FeedDao
import com.newsreader.feeds.infrastructure.{FeedDbDao, FeedH2Dao}
import slick.driver.H2Driver.api._


class InjectModule extends AbstractModule {

  def configure() = {
    bind(classOf[FeedService]).to(classOf[FeedServiceImpl])
    bind(classOf[FeedDao]).to(classOf[FeedH2Dao])
    bind(classOf[Database]).toProvider(Db2DatabaseProvider)

  }

}
