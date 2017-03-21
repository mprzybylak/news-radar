package controllers

import javax.inject.Inject

import akka.util.ByteString
import com.newsreader.feeds.application.api.FeedService
import com.newsreader.feeds.domain.Feed
import play.api.libs.json.Json
import play.api.mvc._
import play.api.routing.JavaScriptReverseRouter

class FeedListController @Inject() (service:FeedService) extends Controller {

  implicit val feedWrites = Json.writes[Feed]


  def index = Action {
    Ok(views.html.index(""))
  }

  def feedJavascriptControllers = Action {
    implicit request => Ok(
      JavaScriptReverseRouter("jsRoutes")(
        controllers.routes.javascript.FeedListController.add,
        controllers.routes.javascript.FeedListController.load
      )
    ).as("text/javascript")
  }

  def add = Action { implicit request =>
    val json = request.body.asJson
    val jsonName = json.map(json => (json \ "feedName").as[String]).get
    val jsonAddress = json.map(json => (json \ "feedAddress").as[String]).get
    val id = service.storeFeed(jsonName, jsonAddress)

    Console.println(id)
    Console.println(ByteString(id))

    Ok(Json.toJson(id))
  }

  def load = Action {
    Ok(Json.toJson(service.loadFeeds))
  }

}
