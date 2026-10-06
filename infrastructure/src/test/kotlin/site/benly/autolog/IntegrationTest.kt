package site.benly.autolog

import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest
import org.springframework.test.context.TestPropertySource


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = ["spring.sql.init.mode=always"])
annotation class IntegrationTest