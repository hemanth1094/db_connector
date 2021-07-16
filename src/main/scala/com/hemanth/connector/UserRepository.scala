package com.hemanth.connector

import com.google.inject.ImplementedBy
import slick.lifted.ProvenShape

import scala.concurrent.Future

@ImplementedBy(classOf[UserRepository])
trait UserRepositoryApi extends UserTable {
  this: DBComponent =>

  import driver.api._

  def createUser(user: User): Future[Int] = {
    db.run(userTableAutoInc += user)
  }

  def getUserList: Future[Seq[User]] = {
    db.run(userTableQuery.result)
  }

}

trait UserTable {
  this: DBComponent =>

  import driver.api._

  type UserEmailTableType = UserTable
  protected val userTableQuery = TableQuery[UserTable]

  protected def userTableAutoInc = userTableQuery returning userTableQuery.map(_.id)

  private[UserTable] class UserTable(tag: Tag) extends Table[User](tag, "user_data") {
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val email = column[String]("email")
    val name = column[String]("name")

    def * : ProvenShape[User] = (name, email, id) <> (User.tupled, User.unapply)
  }

}

class UserRepository extends UserRepositoryApi with MySqlDBComponent

object UserRepositoryObj extends UserRepositoryApi with MySqlDBComponent

case class User(name: String, email: String, id: Int = 0)
