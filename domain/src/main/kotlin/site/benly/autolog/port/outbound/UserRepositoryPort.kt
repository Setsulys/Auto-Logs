package site.benly.autolog.port.outbound

import site.benly.autolog.model.User
import java.util.*

interface UserRepositoryPort {

    fun update()

    fun findById(id: UUID): Optional<User>
}