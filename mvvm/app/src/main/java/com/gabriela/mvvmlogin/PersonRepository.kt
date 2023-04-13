package com.gabriela.mvvmlogin

class PersonRepository {
    fun login(email: String, password: String) : Boolean {
        println("-------> 🛑 $email | $password")
        return email != "" && password != ""
    }
}