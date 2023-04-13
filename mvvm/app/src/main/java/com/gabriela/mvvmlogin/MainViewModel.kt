package com.gabriela.mvvmlogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var textWelcome = MutableLiveData<String>()
    private var isLoginSuccessful = MutableLiveData<Boolean>()
    private val personRepository = PersonRepository()

    init {
        textWelcome.value = "Login MVVM ViewModel"
    }

    fun welcome() : LiveData<String> {
        return textWelcome
    }


    fun isLoginSuccessful() : LiveData<Boolean> {
        return isLoginSuccessful
    }

    fun doLogin(email: String, password: String)  {
        isLoginSuccessful.value = personRepository.login(email,password)
    }
}