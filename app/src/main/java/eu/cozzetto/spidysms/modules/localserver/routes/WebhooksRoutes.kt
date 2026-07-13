package eu.cozzetto.spidysms.modules.localserver.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import eu.cozzetto.spidysms.domain.EntitySource
import eu.cozzetto.spidysms.modules.localserver.LocalServerSettings
import eu.cozzetto.spidysms.modules.localserver.auth.AuthScopes
import eu.cozzetto.spidysms.modules.localserver.auth.requireScope
import eu.cozzetto.spidysms.modules.webhooks.WebHooksService
import eu.cozzetto.spidysms.modules.webhooks.domain.WebHookDTO

class WebhooksRoutes(
    private val webHooksService: WebHooksService,
    private val localServerSettings: LocalServerSettings,
) {
    fun register(routing: Route) {
        routing.apply {
            webhooksRoutes()
        }
    }

    private fun Route.webhooksRoutes() {
        get {
            if (!requireScope(AuthScopes.WebhooksList)) return@get
            call.respond(webHooksService.select(EntitySource.Local))
        }
        post {
            if (!requireScope(AuthScopes.WebhooksWrite)) return@post
            val webhook = call.receive<WebHookDTO>()
            if (webhook.deviceId != null && webhook.deviceId != localServerSettings.deviceId) {
                throw IllegalArgumentException(
                    "Device ID mismatch"
                )
            }

            val updated = webHooksService.replace(EntitySource.Local, webhook)

            call.respond(HttpStatusCode.Created, updated)
        }
        delete("/{id}") {
            if (!requireScope(AuthScopes.WebhooksDelete)) return@delete
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            webHooksService.delete(EntitySource.Local, id)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}