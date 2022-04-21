package io.jamshid.pdpuz.domain.models

sealed class Resourse<T>(
    val message:String?=null,
    val data:T?,
    val isLoading:Boolean=false
){
    class Success<T>(data: T) : Resourse<T>(data = data!!)
    class Error<T>(message: String?) : Resourse<T>(message = message,data = null,isLoading = false)
    class Loading<T>() : Resourse<T>(isLoading = true, message = "", data = null)
}
