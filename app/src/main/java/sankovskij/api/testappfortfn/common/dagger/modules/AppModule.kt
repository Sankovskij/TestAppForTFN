package sankovskij.api.testappfortfn.common.dagger.modules

import dagger.Module
import dagger.Provides
import sankovskij.api.testappfortfn.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}