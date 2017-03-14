package com.newsreader.feeds.domain

trait FeedDao {
  def store(feed: Feed)
}
