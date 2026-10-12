package site.benly.autolog.port.outbound

import site.benly.autolog.model.User
import java.util.*

interface UserRepositoryPort {

    fun update(user: User)

    fun findById(id: UUID): User?

    fun delete(id: UUID) : Boolean
}