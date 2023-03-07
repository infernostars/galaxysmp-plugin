package dev.infernity.galacticplugin

import dev.infernity.galacticplugin.utilities.foodUtilities
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import dev.infernity.galacticplugin.listeners.*

class GalacticPlugin : JavaPlugin() {

    override fun onEnable() {
        plugin = this
        foodUtilities.createFoods()
        server.pluginManager.registerEvents(PlayerInteractListener(), this)
        Bukkit.getLogger().info("GalacticPlugin loaded!")

    }

    override fun onDisable() {
        // Plugin shutdown logic
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