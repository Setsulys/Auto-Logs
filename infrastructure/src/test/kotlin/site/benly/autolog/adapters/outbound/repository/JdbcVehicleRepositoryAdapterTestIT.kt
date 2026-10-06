package site.benly.autolog.adapters.outbound.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest
import org.springframework.context.annotation.Import
import org.springframework.jdbc.core.simple.JdbcClient

@JdbcTest
@Import(JdbcVehicleRepositoryAdapter::class)
class JdbcVehicleRepositoryAdapterTestIT {

    @Autowired
    private lateinit var adapter: JdbcVehicleRepositoryAdapter

    @Autowired
    private lateinit var jdbcClient: JdbcClient

//    @Test
//    fun `should save vehicle in database`() {
//
//        //GIVEN
//        val iud: UUID= UUID.randomUUID()
//        val vehicle = Vehicle(
//            id = iud,
//            lastOdometer = 50,
//            currentOdometer = 2350,
//            tankSize = 32
//        )
//
//        //WHEN
//        adapter.save(vehicle)
//
//        //THEN
//        val dbQuery = """
//            SELECT id, last_odometer, current_odometer, tank_size FROM vehicle
//        """.trimIndent()
//        val dbResult =jdbcClient.sql(dbQuery)
//            .param("id", iud.toString())
//            .query{ rs, _ ->
//                Vehicle(
//                    id = UUID.fromString(rs.getString("id")),
//                    currentOdometer = rs.getInt("currentOdometer"),
//                    lastOdometer = rs.getInt("lastOdometer"),
//                    tankSize = rs.getInt("tankSize"),
//                )
//            }
//            .optional()
//            .orElse(null)
//
//        assertThat(dbResult).isNotNull
//    }
}