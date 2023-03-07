package dev.infernity.galacticplugin.utilities

import com.google.common.collect.ImmutableMap
import net.kyori.adventure.text.Component
import org.bukkit.potion.PotionEffectType
import java.util.*
import kotlin.math.abs


object componentUtilities {

    private val KEY_EXCEPTIONS: Map<String, String> = ImmutableMap.builder<String, String>()
        .put("entity.PrimedTnt.name", "tile.tnt.name") // "TNT" instead of "Block of TNT"
        .put("entity.MinecartTNT.name", "item.minecartTnt.name")
        .put("item.anvil.name", "tile.anvil.name")
        .put("item.sand.name", "tile.sand.name")
        .put("item.gravel.name", "tile.gravel.name")
        .put("item.lava.name", "tile.lava.name")
        .put("potion.speed", "potion.moveSpeed")
        .put("potion.slow", "potion.moveSlowdown")
        .put("potion.fastDigging", "potion.digSpeed")
        .put("potion.slowDigging", "potion.digSlowdown")
        .put("potion.damageResistance", "potion.resistance")
        .build()


    private fun camelCase(text: String): String {
        return camelCase(text, false)
    }

    private fun camelCase(text: String, reverse: Boolean): String {
        val sections = text.lowercase(Locale.getDefault()).split("[-_ ]".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val builder = StringBuilder(text.length)
        val start = if (reverse) sections.size - 1 else 0
        for (i in sections.indices) {
            val section = sections[abs(start - i)]
            builder.append(
                if (i == 0) section else section.substring(0, 1).uppercase(Locale.getDefault()) + section.substring(1)
            )
        }
        return builder.toString()
    }

    private const val POTION_KEY = "potion.%s"

    /**
     * Gets a translated potion name.
     *
     * @param potion A potion type.
     * @return A potion name.
     */
    fun getPotion(potion: PotionEffectType): Component {
        return getKey(POTION_KEY, camelCase(potion.name))
    }

    private fun getKey(format: String, name: String): Component {
        val key = String.format(format, name)
        return Component.translatable(KEY_EXCEPTIONS.getOrDefault(key, key))
    }


}