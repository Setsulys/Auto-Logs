package site.benly.autolog.model

import java.util.UUID

class Owner (
    val id: UUID,
    val email: String,
    val pseudo: String,
    ){
    init {
        require(email.isNotBlank()) { "Email cannot be blank" }
        require(email.contains("@")) { "Invalid format for Email" }
        require(pseudo.isNotBlank()) { "Pseudo cannot be blank" }
    }
}