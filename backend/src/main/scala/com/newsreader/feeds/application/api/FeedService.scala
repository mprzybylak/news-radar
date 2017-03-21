package com.newsreader.feeds.application.api

import com.newsreader.feeds.domain.Feed

trait FeedService {
  def storeFeed(name: String, address: String): Long
  def loadFeeds: Seq[Feed]
}
