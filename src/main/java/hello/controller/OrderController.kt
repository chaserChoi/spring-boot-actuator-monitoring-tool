package hello.controller

import hello.order.OrderService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {  }

@RestController
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping("/order")
    fun order(): String {
        log.info { "order" }
        orderService.order()
        return "order"
    }

    @GetMapping("/cancel")
    fun cancel(): String {
        log.info { "cancel" }
        orderService.cancel()
        return "cancel"
    }

    @GetMapping("/stock")
    fun stock(): Int {
        log.info { "stock" }
        return orderService.stock.get()
    }
}