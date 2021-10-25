package controllers

import play.api.mvc._

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class AuthenticatedUserController @Inject()(
                                            cc: ControllerComponents,
                                            authenticatedUserAction: AuthenticatedUserAction
                                           ) extends AbstractController(cc) {

  def logout = authenticatedUserAction { implicit request: Request[AnyContent] =>
    Redirect(routes.UserController.showLoginForm).flashing("info" -> "you are logged out").withNewSession
  }

  val timeout: Action[AnyContent] = Action.async { implicit request =>
    Future.successful( Redirect(routes.UserController.showLoginForm).withNewSession)
  }

  val keepAlive: Action[AnyContent] = Action.async { implicit  request =>
    Future.successful(Ok("OK").withSession(request.session))
  }
}
