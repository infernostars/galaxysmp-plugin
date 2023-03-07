package dev.infernity.galacticplugin.utilities

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object foodUtilities {
    fun createFoods() {
        createFood(
            Component.text("Chocolate", NamedTextColor.WHITE),
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODE5Zjk0OGQxNzcxOGFkYWNlNWRkNmUwNTBjNTg2MjI5NjUzZmVmNjQ1ZDcxMTNhYjk0ZDE3YjYzOWNjNDY2In19fQ==",
            2,
            1f,
            "chocolate",
            arrayOf(ItemStack(Material.COCOA_BEANS), ItemStack(Material.SUGAR))
        )
    }
    private fun createFood(name: Component, base64: String, hunger: Int, saturation: Float, key: String, recipeItems: Array<ItemStack?>){
        val food = itemUtilities.createFoodHead(name, base64, hunger, saturation)
        Bukkit.addRecipe(
            itemUtilities.shapelessRecipeCreator(
                food,
                key,
                recipeItems
            )
        )
    }
}