package com.gabriela.motivationapp.infra

class MotivationConstants private constructor(){
    object KEY {
        const val USER_NAME = "USER_NAME"
        const val EMAIL = "EMAIL"
        const val PASSWORD = "PASSWORD"
        const val IS_LOGGED = "IS_LOGGED"
    }

    object FILTER {
        const val ALL = 1
        const val HAPPY = 2
        const val SUNNY = 3
    }
}