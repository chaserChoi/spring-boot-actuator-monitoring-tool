package hello.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {  }

/**
 * 여러 레벨 남기는 단순 컨트롤러
 */
@RestController
class LogController {

    @GetMapping("/log")
    fun log(): String {
        log.trace { "trace log" }
        log.debug { "debug log" }
        log.info { "info log" }
        log.warn { "warn log" }
        log.error { "error log" }
        return "ok"
    }
}