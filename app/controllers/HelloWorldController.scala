package controllers

import play.api.mvc._

class HelloWorldController extends Controller {

  def index = Action {
    Ok(views.html.index("Hello, daj sie poznac"))
  }

}
