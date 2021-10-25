package controllers

import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}

@Singleton
class LandingPageController @Inject()(
                                      cc: ControllerComponents,
                                      authenticatedUserAction: AuthenticatedUserAction
                                     ) extends AbstractController(cc) {

  def showLandingPage() = authenticatedUserAction{ implicit request: Request[AnyContent] =>
    Ok(views.html.loginLandingPage(routes.AuthenticatedUserController.logout))
  }

}
