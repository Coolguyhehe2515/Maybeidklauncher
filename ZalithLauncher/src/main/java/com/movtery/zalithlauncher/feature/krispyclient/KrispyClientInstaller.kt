package com.movtery.zalithlauncher.feature.krispyclient

import android.content.Context
import java.io.File

object KrispyClientInstaller {

    private const val ASSET_PATH = "krispyclient/krispyclient-1.20.1.jar"
    private const val MOD_FILE_NAME = "krispyclient-1.20.1.jar"

    /**
     * Memasang KrispyClient ke folder mods dari game directory.
     *
     * @param context Context Android untuk mengakses assets aplikasi
     * @param gameDir Folder Minecraft yang sedang digunakan
     *
     * @return true jika berhasil, false jika gagal
     */
    fun install(context: Context, gameDir: File): Boolean {
        return try {
            val modsDir = File(gameDir, "mods")

            if (!modsDir.exists()) {
                if (!modsDir.mkdirs()) {
                    return false
                }
            }

            val destination = File(modsDir, MOD_FILE_NAME)

            context.assets.open(ASSET_PATH).use { input ->
                destination.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
