package dev.infernity.galacticplugin.utilities

// gpt-4 made this. AI gives more training data to copilot?? real??

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.util.Properties

object DatabaseUtilities {
    private const val DB_URL = "jdbc:mysql://localhost:3306/minecraft"
    private const val USER = "username"
    private const val PASSWORD = "password"

    private fun getConnection(): Connection {
        val properties = Properties()
        properties["user"] = USER
        properties["password"] = PASSWORD

        return DriverManager.getConnection(DB_URL, properties)
    }

    fun createTable() {
        val sql = """
            CREATE TABLE IF NOT EXISTS blocks (
                id INT AUTO_INCREMENT PRIMARY KEY,
                x INT NOT NULL,
                y INT NOT NULL,
                z INT NOT NULL,
                blocktype VARCHAR(255) NOT NULL
            )
        """.trimIndent()

        try {
            getConnection().use { connection ->
                connection.createStatement().use { statement ->
                    statement.executeUpdate(sql)
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun insertBlock(x: Int, y: Int, z: Int, blockType: String) {
        val sql = "INSERT INTO blocks (x, y, z, blocktype) VALUES (?, ?, ?, ?)"

        try {
            getConnection().use { connection ->
                connection.prepareStatement(sql).use { preparedStatement ->
                    preparedStatement.setInt(1, x)
                    preparedStatement.setInt(2, y)
                    preparedStatement.setInt(3, z)
                    preparedStatement.setString(4, blockType)

                    preparedStatement.executeUpdate()
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun getBlock(id: String): ResultSet? {
        val sql = "SELECT * FROM blocks WHERE id = ?"

        return try {
            getConnection().use { connection ->
                connection.prepareStatement(sql).use { preparedStatement ->
                    preparedStatement.setString(1, id)

                    preparedStatement.executeQuery()
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    fun getAllBlocks(): ResultSet? {
        val sql = "SELECT * FROM blocks"

        return try {
            getConnection().use { connection ->
                connection.createStatement().use { statement ->
                    statement.executeQuery(sql)
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    fun getBlockByCoordinates(x: Int, y: Int, z: Int): ResultSet? {
        val sql = "SELECT * FROM blocks WHERE x = ? AND y = ? AND z = ?"

        return try {
            getConnection().use { connection ->
                connection.prepareStatement(sql).use { preparedStatement ->
                    preparedStatement.setInt(1, x)
                    preparedStatement.setInt(2, y)
                    preparedStatement.setInt(3, z)

                    preparedStatement.executeQuery()
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    fun deleteBlock(id: String) {
        val sql = "DELETE FROM blocks WHERE id = ?"

        try {
            getConnection().use { connection ->
                connection.prepareStatement(sql).use { preparedStatement ->
                    preparedStatement.setString(1, id)

                    preparedStatement.executeUpdate()
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}