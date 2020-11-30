package io.kraftsman

import io.kraftsman.services.ContactService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import kotlin.time.ExperimentalTime

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@ExperimentalTime
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {

        }
    }

    routing {

        get("/") {
            call.respondText(
                text = "Hello, Ktor",
                contentType = ContentType.Text.Plain
            )
        }

        get("/contacts") {
            val param = call.request.queryParameters["amount"]
            val amount = param?.toIntOrNull() ?: 10

            val contacts = ContactService().generate(amount)

            call.respond(mapOf("data" to contacts))
        }

    }

}
