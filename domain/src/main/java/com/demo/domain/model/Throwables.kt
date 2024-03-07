package com.demo.domain.model

data class GenericException(override val message: String) : Throwable(message = message)
