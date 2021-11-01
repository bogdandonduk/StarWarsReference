package proto.android.starwarsreference

import android.app.Application

class StarWarsReference : Application() {
    companion object {
        lateinit var instance: StarWarsReference
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}