package com.example.androidapp


interface Model<S, E> {

    fun fetch()

    fun init(callback: ResultCallback<S, E>)

    fun clear()
}
interface ResultCallback<S,E>{

    fun provideSuccess(data:S)

    fun provideError(data:E)

}