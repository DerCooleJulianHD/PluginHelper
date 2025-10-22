package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils

import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException

object Base64 {
    @kotlin.Throws(IllegalStateException::class)
    fun itemStackArrayToBase64(items: Array<ItemStack?>): String {
        try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)

            // Write the size of the inventory
            dataOutput.writeInt(items.size)

            // Save every element in the list
            for (i in items.indices) {
                dataOutput.writeObject(items[i])
            }

            // Serialize that array
            dataOutput.close()
            return Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (e: Exception) {
            throw IllegalStateException("Unable to save item stacks.", e)
        }
    }

    @kotlin.Throws(IOException::class)
    fun itemStackArrayFromBase64(base64: String): Array<ItemStack?> {
        try {
            val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(base64))
            val dataInput = BukkitObjectInputStream(inputStream)
            val items = arrayOfNulls<ItemStack>(dataInput.readInt())

            // Read the serialized inventory
            for (i in items.indices) {
                items[i] = dataInput.readObject() as ItemStack?
            }

            dataInput.close()
            return items
        } catch (e: ClassNotFoundException) {
            throw IOException("Unable to decode class type.", e)
        }
    }
}
