package site.benly.autolog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AutoLogApplication

fun main(args: Array<String>) {
    runApplication<AutoLogApplication>(*args)
}
