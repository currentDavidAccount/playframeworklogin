package models

import javax.inject.{Inject, Singleton}

@Singleton
class UserDao @Inject()() {

  def lookupUser(u: User): Boolean = {

    if (u.username == "user1" && u.password == "12345") true else false
  }

}
