package com.github.azurapi.azurapikotlin.api

import com.github.azurapi.azurapikotlin.internal.entities.Ship
import com.github.azurapi.azurapikotlin.internal.exceptions.DatabaseException
import com.github.azurapi.azurapikotlin.internal.exceptions.ShipNotFoundException
import com.github.azurapi.azurapikotlin.internal.exceptions.ApiException
import com.github.azurapi.azurapikotlin.json.Takao
import java.util.stream.Collectors

/**
 * API class
 * @throws ShipNotFoundException
 * @throws DatabaseException
 * @throws ApiException
 */
class Atago {

    private val database = try {
        Takao()
    } catch (e: DatabaseException) {
        throw DatabaseException(e.message ?: "")
    }

    /**
     * @since 1.0.0
     *
     * Get information about a ship by name
     * @param name name of the ship
     */
    fun getShipByName(name: String): Ship {
        val formattedName = name.toLowerCase()
        if (database.shipsByName.containsKey(formattedName)) {
            return database.shipsByName[formattedName]!!
        } else {
            throw ShipNotFoundException("Could not find ship with name: $name")
        }
    }

    /**
     * @since 1.0.0
     *
     * Get information about a ship by id
     * @param id id of the ship
     */
    fun getShipById(id: String): Ship {
        val formattedId = id.toLowerCase()
        if (database.shipsById.containsKey(formattedId)) {
            return database.shipsById[formattedId]!!
        } else {
            throw ShipNotFoundException("Could not find ship with id: $id")
        }
    }

    /**
     * @since 1.0.0
     *
     * Get list of all ships
     */
    fun getAllShips(): List<Ship> {
        return database.shipsById.values.stream().collect(Collectors.toList())
    }
}