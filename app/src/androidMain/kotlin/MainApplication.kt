package org.cufy.mmrpc.editor

import android.app.Application
import kotlinx.coroutines.runBlocking
import net.lsafer.compose.simplenav.InMemoryNavController

class MainApplication : Application() {
    companion object {
        lateinit var globalLocal: Local
        lateinit var globalNavCtrl: MainNavController
    }

    override fun onCreate() {
        super.onCreate()

        runBlocking {
            val local = createAndroidLocal()
            val navCtrl = InMemoryNavController<MainRoute>(
                default = MainRoute.Home,
            )

            globalLocal = local
            globalNavCtrl = navCtrl
        }
    }
}
