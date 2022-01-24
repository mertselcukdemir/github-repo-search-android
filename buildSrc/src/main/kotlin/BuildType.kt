/**
 * Created by mertselcukdemir on 19.01.2022
 * All rights reserved.
 */


/**
 * Configuration of build types
 */
interface BuildType {

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-DEBUG"
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
}
