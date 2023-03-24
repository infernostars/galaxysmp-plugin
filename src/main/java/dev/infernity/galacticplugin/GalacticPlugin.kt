package dev.infernity.galacticplugin

import dev.infernity.galacticplugin.utilities.FoodUtilities
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import dev.infernity.galacticplugin.listeners.*

class GalacticPlugin : JavaPlugin() {

    override fun onEnable() {
        plugin = this
        FoodUtilities.createFoods()
        server.pluginManager.registerEvents(PlayerInteractListener(), this)
        Bukkit.getLogger().info("GalacticPlugin loaded!")

    }

    override fun onDisable() {
        Bukkit.getLogger().info("GalacticPlugin shutting down.")
    }

    companion object {
        // Builtin utilities that require the plugin //
        fun createNamespacedKey(keyName: String?): NamespacedKey {
            return NamespacedKey(plugin!!, keyName!!)
        }

        var plugin: GalacticPlugin? = null
            private set
    }
}