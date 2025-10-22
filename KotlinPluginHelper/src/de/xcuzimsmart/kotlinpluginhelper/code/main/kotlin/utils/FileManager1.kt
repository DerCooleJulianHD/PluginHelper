package de.xcuzimsmart.kotlinpluginhelper.code.main.kotlin.utils

import de.xcuzimsmart.pluginhelper.code.main.kotlin.plugin.SpigotPlugin
import org.bukkit.util.FileUtil
import java.io.File
import java.io.IOException
import java.util.*
import java.util.logging.Level

object FileManager {
    fun copyInnerFiles(from: File, to: File?) {
        FileUtil.copy(from, to)
    }

    fun copyDir(dir: File, to: File) {
        if (!dir.exists()) return
        if (to.isDirectory() && !to.exists()) to.mkdirs()

        val copy = File(to, dir.getName())

        if (!copy.isDirectory()) return
        if (!copy.exists()) copy.mkdirs()
        copyInnerFiles(dir, copy)
    }

    fun deleteFile(file: File) {
        if (!file.exists()) return

        if (file.isDirectory()) {
            val childs = file.listFiles()
            if (childs == null) return
            if (hasChilds(file)) for (child in childs) deleteFile(child)
        }

        file.delete()
    }

    fun mkdir(file: File) {
        file.mkdirs()
    }

    fun mkdirIf(expression: Boolean, file: File) {
        if (expression) mkdir(file)
    }

    fun mkdirIfNotExists(file: File) {
        mkdirIf(!file.exists(), file)
    }

    fun zip(target: File) {
        if (!target.exists()) return

        val dir = target.getParentFile()
        val zip = File(dir, target.getName() + ".zip")

        try {
            if (!zip.exists()) zip.createNewFile()

            copyDir(target, zip)
        } catch (e: IOException) {
            SpigotPlugin.Companion.getPluginLogger().log(Level.SEVERE, e.getLocalizedMessage(), e)
        }
    }

    fun unzip(zip: File, to: File?) {
        if (zip.exists()) copyInnerFiles(zip, to)
    }

    fun hasChilds(dir: File): Boolean {
        return dir.listFiles() != null && Objects.requireNonNull<Array<File?>?>(dir.listFiles()).size > 0
    }

    fun renameFile(file: File, newName: String) {
        if (!file.exists()) return
        val destination = File(file.getParent(), newName)
        file.renameTo(destination)
    }

    fun isFolderEmpty(file: File): Boolean {
        return file.isDirectory() && (!hasChilds(file))
    }
}



