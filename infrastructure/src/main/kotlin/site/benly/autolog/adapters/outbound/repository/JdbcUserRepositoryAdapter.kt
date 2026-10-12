package site.benly.autolog.adapters.outbound.repository

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Component
import site.benly.autolog.model.User
import site.benly.autolog.port.outbound.UserRepositoryPort
import java.util.*


@Component
class JdbcUserRepositoryAdapter(
    val jdbcClient: JdbcClient
) : UserRepositoryPort {


    override fun update() {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): Optional<User> {
        TODO("Not yet implemented")
    }
}