package org.hs.learning.graphql.sangria

import scala.concurrent.{Await, Future}

object QueryTest extends App{

  import sangria.macros._
  import DataSchema._

  val query =
    graphql"""
    query P {
      products {
        name
        category
      }
    }
  """
  import sangria.execution._
  import sangria.marshalling.circe._

  import io.circe.Json

  import scala.concurrent.ExecutionContext.Implicits.global

  val result: Future[Json] =
    Executor.execute(schema, query, new DataRepo)

  import scala.concurrent.duration.Duration._
  println(Await.result(result,Inf))

}
