package com.robertgordon.finaldailyplan

import android.app.Application

class GoalsApplication: Application() {

    init{
        instance = this
    }

    companion object {
        private var instance: Application? = null

        fun getInstance():Application{
            return instance!!
        }

    }

}