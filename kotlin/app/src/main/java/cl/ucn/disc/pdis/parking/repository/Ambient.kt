package cl.ucn.disc.pdis.parking.repository

import androidx.compose.ambientOf

/**
 * Create an ambient key that can be provided.
 */
val repository = ambientOf<VehicleRepository> {
    error("Repository not found.")
}
