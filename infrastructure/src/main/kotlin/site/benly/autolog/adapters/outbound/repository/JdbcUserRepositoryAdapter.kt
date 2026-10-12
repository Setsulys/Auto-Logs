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


    override fun update(user: User) {
        val sql ="""
            INSERT INTO user (id, email, pseudo)
            VALUES (:id, :email, :pseudo)
            ON CONFLICT (id)
            DO UPDATE SET 
            email = :email,
            pseudo = :pseudo
            WHERE id = :id
        """.trimIndent()

        jdbcClient.sql(sql)
            .param("id",user.id)
            .param("email",user.email)
            .param("pseudo",user.pseudo)
            .update()
    }

    override fun findById(id: UUID): User? {
        val sql = """
            SELECT *
            FROM user
            WHERE id = :id
        """.trimIndent()

        return jdbcClient.sql(sql)
            .param("id",id)
            .query{ rs, _ ->
                User(
                    id= UUID.fromString(rs.getString("id")),
                    email = rs.getString("email"),
                    pseudo = rs.getString("pseudo")
                )
            }.optional().orElse(null)
    }

    override fun delete(id: UUID): Boolean {
        val sql = """
            DELETE 
            FROM user
            WHERE id = :id
        """.trimIndent()

        return jdbcClient.sql(sql).param("id",id).update() == 1
    }
}