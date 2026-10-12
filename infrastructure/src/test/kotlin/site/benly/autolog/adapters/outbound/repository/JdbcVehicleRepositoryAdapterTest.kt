package site.benly.autolog.adapters.outbound.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import site.benly.autolog.adapters.outbound.repository.stub.InMemoryJdbcVehicleRepositoryAdapter
import site.benly.autolog.models.Vehicle
import java.util.*

class JdbcVehicleRepositoryAdapterTest {

    private val vehicleAdapter = InMemoryJdbcVehicleRepositoryAdapter()

    @BeforeEach
    fun setUp() {
        vehicleAdapter.clear()
    }

    @Test
    fun `should insert a new vehicle and then update it on conflict`() {
        val initialUUID = UUID.randomUUID()
        val initialVehicle = Vehicle(
            id= initialUUID,
            lastOdometer = 50_000,
            currentOdometer=50_500,
            tankSize= 32,
        )

        val updatedVehicle = Vehicle(
            id= initialUUID,
            lastOdometer = 50_500,
            currentOdometer=60_000,
            tankSize= 32,
        )

        vehicleAdapter.save(initialVehicle)
        vehicleAdapter.save(updatedVehicle)

        val result = vehicleAdapter.findById(initialUUID)
        assertThat(result).isNotNull
        assertThat(result?.id).isEqualTo(initialUUID)
        assertThat(result?.currentOdometer).isEqualTo(60_000)
        assertThat(result?.tankSize).isEqualTo(32)

    }
}