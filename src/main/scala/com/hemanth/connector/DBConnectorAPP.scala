package com.hemanth.connector

import com.hemanth.connector.{User, UserRepositoryObj}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object DBConnectorAPP extends App {
  val resForUserCreation = Await.result(UserRepositoryObj.createUser(User("hemanth", "hobillaneni@decooda.com")), Duration.Inf)
  println("user creation res ====> " + resForUserCreation)
  val users = Await.result(UserRepositoryObj.getUserList, Duration.Inf)
  users.foreach { user => println("user ===> " + user)}
}
