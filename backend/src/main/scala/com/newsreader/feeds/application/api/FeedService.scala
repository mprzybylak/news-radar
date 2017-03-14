package com.newsreader.feeds.application.api

trait FeedService {
  def storeFeed(name: String, address: String)
}
