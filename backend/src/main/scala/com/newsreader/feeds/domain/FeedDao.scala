package com.newsreader.feeds.domain

trait FeedDao {
  def store(feed: Feed) : Long
  def load: Seq[Feed]
}
