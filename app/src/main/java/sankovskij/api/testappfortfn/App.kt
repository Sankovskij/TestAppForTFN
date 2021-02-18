package sankovskij.api.testappfortfn

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.util.CoilUtils
import okhttp3.OkHttpClient
import sankovskij.api.testappfortfn.common.dagger.AppComponent
import sankovskij.api.testappfortfn.common.dagger.DaggerAppComponent
import sankovskij.api.testappfortfn.common.dagger.modules.AppModule

class App : Application(), ImageLoaderFactory {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .componentRegistry {
                add(SvgDecoder(applicationContext))
            }
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }
            .build()
    }
}

