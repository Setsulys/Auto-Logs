package site.benly.autolog.adapters.inbound.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import site.benly.autolog.model.User
import site.benly.autolog.port.inbound.UserUseCase
import java.util.*


@RestController
@RequestMapping("/api/v1/users")
class UserRestAdapter(
    val userUseCase: UserUseCase
) {

    @PostMapping("")
    fun `create user`(@RequestBody user: User): ResponseEntity<User> {
        TODO()
    }

    @DeleteMapping("/{id}")
    fun `delete user`(@PathVariable id: UUID): ResponseEntity<User> {
        TODO()
    }

    @PostMapping("/{id}")
    fun `update user`(@PathVariable id: UUID, @RequestBody user: User): ResponseEntity<User> {
        TODO()
    }
}