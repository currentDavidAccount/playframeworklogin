package controllers

import play.api.mvc.Results._
import play.api.mvc.{ActionBuilderImpl, BodyParsers, Request, Result}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class AuthenticatedUserAction @Inject()(parser: BodyParsers.Default)(implicit ec: ExecutionContext)
  extends ActionBuilderImpl(parser) {

  private val logger = play.api.Logger(this.getClass)

  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]): Future[Result] = {
    logger.info("ENTERED AuthenticatedUserAction::invokeBlock ...")
    val maybeUserName = request.session.get(models.Global.SESSION_USERNAME_KEY)
    maybeUserName match{
      case None => Future.successful(Forbidden("you are not logged on"))
      case Some(_) => block(request)
    }
  }

}
