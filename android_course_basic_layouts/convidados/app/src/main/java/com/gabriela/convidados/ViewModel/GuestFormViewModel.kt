package com.gabriela.convidados.ViewModel

import androidx.lifecycle.ViewModel
import com.gabriela.convidados.Repository.GuestFormRepository

class GuestFormViewModel : ViewModel() {

    private val repository = GuestFormRepository.getInstance()
}