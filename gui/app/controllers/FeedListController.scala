package controllers

import javax.inject.Inject

import com.newsreader.feeds.application.api.FeedService
import play.api.mvc._
import play.api.routing.JavaScriptReverseRouter


class FeedListController @Inject() (service:FeedService) extends Controller {

  def index = Action {
    Ok(views.html.index(""))
  }

  def feedJavascriptControllers = Action {
    implicit request => Ok(
      JavaScriptReverseRouter("jsRoutes")(
        controllers.routes.javascript.FeedListController.add
      )
    ).as("text/javascript")
  }

  def add = Action { implicit request =>
    val json = request.body.asJson
    val jsonName = json.map(json => (json \ "feedName").as[String]).get
    val jsonAddress = json.map(json => (json \ "feedAddress").as[String]).get
    service.storeFeed(jsonName, jsonAddress)
    println(json)
    Ok("ok");
  }

}
