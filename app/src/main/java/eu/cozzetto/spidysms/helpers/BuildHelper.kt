package eu.cozzetto.spidysms.helpers

import eu.cozzetto.spidysms.BuildConfig

object BuildHelper {
    val isInsecureVersion =
        BuildConfig.BUILD_TYPE == "insecure" || BuildConfig.BUILD_TYPE == "debugInsecure"
}