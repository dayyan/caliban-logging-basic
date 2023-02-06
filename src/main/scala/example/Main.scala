import example.ExampleData._
import example.{ ExampleApi, ExampleService }

import caliban.ZHttpAdapter
import zio._
import zio.stream._
import zhttp.http._
import zhttp.service.Server
import zio.logging.backend.SLF4J

object ExampleApp extends ZIOAppDefault {
  import sttp.tapir.json.circe._

  override val bootstrap: ZLayer[ZIOAppArgs, Any, Any] = Runtime.removeDefaultLoggers >>> SLF4J.slf4j

  override def run =
    (for {
      _           <- ZIO.logInfo("Starting server.")
      interpreter <- ExampleApi.api.interpreter
      _           <- Server
                      .start(
                         9000,
                         Http.collectHttp[Request] {
                           case _ -> !! / "api" / "graphql" => ZHttpAdapter.makeHttpService(interpreter)
                           case _ -> !! / "ws" / "graphql"  => ZHttpAdapter.makeWebSocketService(interpreter)
                         }
                       )
                       .forever
    } yield ())
      .provideLayer(ExampleService.make(sampleCharacters))
      .zip(ZIO.never)
}
