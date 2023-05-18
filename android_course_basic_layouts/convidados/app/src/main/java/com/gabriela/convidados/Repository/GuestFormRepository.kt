package com.gabriela.convidados.Repository

class GuestFormRepository private constructor() { // Parte do singleton, para não deixar a classe ser instanciada

    // singleton -> controlar o acesso e o numero de instancias da classe
    companion object {
        private lateinit var repository : GuestFormRepository

        fun getInstance() : GuestFormRepository {
            if (!Companion::repository.isInitialized) {
                repository = GuestFormRepository()
            }
            return repository
        }
    }
}