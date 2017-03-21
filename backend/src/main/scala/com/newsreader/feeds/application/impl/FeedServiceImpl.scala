package com.newsreader.feeds.application.impl

import com.newsreader.feeds.application.api.FeedService
import com.newsreader.feeds.domain.{Feed, FeedDao}
import javax.inject.Inject

class FeedServiceImpl @Inject() (dao:FeedDao) extends FeedService {
  override def storeFeed(name: String, address: String): Long = dao.store(new Feed(0, name, address))
  override def loadFeeds: Seq[Feed] = dao.load
}

