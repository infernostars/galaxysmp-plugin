package dev.infernity.galacticplugin.listeners

import dev.infernity.galacticplugin.GalacticPlugin
import dev.infernity.galacticplugin.utilities.ItemUtilities
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PlayerInteractListener : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action.isRightClick) {
            if (event.item != null) {
                val item = event.item
                val itemOne = item!!.asOne()
                val player = event.player
                if (itemOne.type == Material.PLAYER_HEAD) {
                    if (ItemUtilities.hasDataInItem(itemOne, GalacticPlugin.createNamespacedKey("potionhead_flag"))) {
                        val potionEffectType = PotionEffectType.getByName(ItemUtilities.getDataInItem(itemOne, GalacticPlugin.createNamespacedKey("potionhead_effect"))!!)
                        val duration = ItemUtilities.getDataInItem(itemOne, GalacticPlugin.createNamespacedKey("potionhead_duration"))!!.toInt()
                        val amplifier = ItemUtilities.getDataInItem(itemOne, GalacticPlugin.createNamespacedKey("potionhead_amplifier"))!!.toInt()
                        val potionEffect = PotionEffect(potionEffectType!!, duration, amplifier)
                        potionEffect.apply(player)
                        player.inventory.itemInMainHand.amount = player.inventory.itemInMainHand.amount - 1
                        player.foodLevel = player.foodLevel + ItemUtilities.getDataInItem(itemOne, GalacticPlugin.createNamespacedKey("potionhead_hunger"))!!.toInt()
                        player.saturation = player.saturation + ItemUtilities.getDataInItem(itemOne, GalacticPlugin.createNamespacedKey("potionhead_saturation"))!!.toFloat()
                        event.isCancelled = true
                    } else if (ItemUtilities.hasDataInItem(itemOne, GalacticPlugin.createNamespacedKey("foodhead_flag"))) {
                        if (!(player.saturation >= 20f && player.foodLevel >= 20)) {
                            player.inventory.itemInMainHand.amount = player.inventory.itemInMainHand.amount - 1
                            player.foodLevel = player.foodLevel + ItemUtilities.getDataInItem(itemOne, GalacticPlugin.createNamespacedKey("foodhead_hunger"))!!.toInt()
                            player.saturation = player.saturation + ItemUtilities.getDataInItem(itemOne, GalacticPlugin.createNamespacedKey("foodhead_saturation"))!!.toFloat()
                        }
                        event.isCancelled = true
                    }
                }
            }
        }
    }
}