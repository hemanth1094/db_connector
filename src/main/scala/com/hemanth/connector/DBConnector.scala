package com.hemanth.connector

import java.sql.DriverManager

import slick.jdbc.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.control.NonFatal

class DBConnector(dbInfo: DBCredentials) {

  /*val driver: JdbcProfile =
    if (dbInfo.dbType == "mysql") {
      slick.jdbc.MySQLProfile
    } else if (dbInfo.dbType == "postgres" || dbInfo.dbType == "redshift") {
      slick.jdbc.PostgresProfile
    } else if (dbInfo.dbType == "h2") {
      slick.jdbc.H2Profile
    } else if (dbInfo.dbType == "snowflake") {
      slick.jdbc.PostgresProfile
    } else {
      throw new IllegalArgumentException(s"Invalid Database Type ${dbInfo.dbType}")
    }

  import driver.api._

  private val db: driver.backend.DatabaseDef = {
    if (dbInfo.dbType == "mysql") {
      val url = "jdbc:mysql://" + dbInfo.ipAddress + ":" + dbInfo.dbPort.get + "/" + dbInfo.dbName + "?user=" +
        dbInfo.username + "&password=" + dbInfo.password + "&useUnicode=true&characterEncoding=utf8"
      Database.forURL(url)
    } else if (dbInfo.dbType == "postgres") {
      val url = "jdbc:postgresql://" + dbInfo.ipAddress + ":" + dbInfo.dbPort.get + "/" + dbInfo.dbName + "?user=" +
        dbInfo.username + "&password=" + dbInfo.password + "&allowEncodingChanges=true"
      Database.forURL(url)
    } else if (dbInfo.dbType == "redshift") {
      val url = "jdbc:redshift://" + dbInfo.ipAddress + ":" + dbInfo.dbPort.get + "/" + dbInfo.dbName + "?user=" +
        dbInfo.username + "&password=" + dbInfo.password + "&allowEncodingChanges=true"
      Database.forURL(url)
    } else if (dbInfo.dbType == "h2") {
      Database.forURL("jdbc:h2:mem:test1;MODE=MySql;DATABASE_TO_UPPER=false;INIT=runscript from 'src/test/resources/schema.sql'\\;", driver = "org.h2.Driver")
    } else if (dbInfo.dbType == "snowflake") {
      Database.forURL("jdbc:snowflake://" + dbInfo.ipAddress + "/" + "?user=" + dbInfo.username + "&password=" +
        dbInfo.password + "&warehouse=" + dbInfo.warehouse.get + "&db=" + dbInfo.dbName + "&schema=" + dbInfo.schema)
    } else {
      throw new IllegalArgumentException(s"Invalid Database Type ${dbInfo.dbType}")
    }
  }*/

  private lazy val db1 = {
    if (dbInfo.dbType == "mysql") {
      val url = "jdbc:mysql://" + dbInfo.ipAddress + ":" + dbInfo.dbPort.get + "/" + dbInfo.dbName + "?user=" +
        dbInfo.username + "&password=" + dbInfo.password + "&useUnicode=true&characterEncoding=utf8"
      DriverManager.getConnection(url)
    } else if (dbInfo.dbType == "postgres") {
      val url = "jdbc:postgresql://" + dbInfo.ipAddress + ":" + dbInfo.dbPort.get + "/" + dbInfo.dbName + "?user=" +
        dbInfo.username + "&password=" + dbInfo.password + "&allowEncodingChanges=true"
      DriverManager.getConnection(url)
    } else if (dbInfo.dbType == "redshift") {
      val url = "jdbc:redshift://" + dbInfo.ipAddress + ":" + dbInfo.dbPort.get + "/" + dbInfo.dbName + "?user=" +
        dbInfo.username + "&password=" + dbInfo.password + "&allowEncodingChanges=true"
      DriverManager.getConnection(url)
    } else if (dbInfo.dbType == "h2") {
      DriverManager.getConnection("jdbc:h2:mem:test1;MODE=MySql;DATABASE_TO_UPPER=false;INIT=runscript from 'src/test/resources/schema.sql'\\;")
    } else if (dbInfo.dbType == "snowflake") {
      DriverManager.getConnection("jdbc:snowflake://" + dbInfo.ipAddress + "/" + "?user=" + dbInfo.username + "&password=" +
        dbInfo.password + "&warehouse=" + dbInfo.warehouse.get + "&db=" + dbInfo.dbName + "&schema=" + dbInfo.schema)
    } else {
      throw new IllegalArgumentException(s"Invalid Database Type ${dbInfo.dbType}")
    }
  }

  def isValid = try {
    println("is valid ==> " + db1.isValid(10))
    println("is read only ==> " + db1.isReadOnly)
    db1.close()
  } catch {
    case NonFatal(th) =>
      println("error ==> " +th.getMessage)
  }

/*  def close(): Unit = db.close()

  def getTable = Await.result(db.run(sql"""show tables;""".as[String]), Duration.Inf)*/
}

case class DBCredentials(
                          ipAddress: String,
                          username: String,
                          password: String,
                          dbType: String,
                          dbName: String,
                          dbPort: Option[String] = None,
                          schema: Option[String] = None,
                          warehouse: Option[String] = None
                        )
