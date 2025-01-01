package com.example.androidapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

class BaseModel(
    private val JokeService:JokeService,
    private val manageResources: ManageResources
):Model<Joke,Error> {
    private val Noconnection = Error.NoConnection(manageResources)
    private val ServiceError = Error.ServiceUnavaileble(manageResources)
    private var callback:ResultCallback<Joke,Error>? = null

    override fun fetch() {
        JokeService.Joke().enqueue(object : Callback<JokeCloud> {
            override fun onResponse(call: Call<JokeCloud>, response: Response<JokeCloud>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body == null)
                        callback?.provideError(ServiceError)
                    else
                    callback?.provideSuccess(body.toJoke())
                }else{
                    callback?.provideError(ServiceError)
                }
            }

            override fun onFailure(call: Call<JokeCloud>, t: Throwable) {

                if (t is UnknownHostException || t is java.net.ConnectException){

                    callback?.provideError(Noconnection)
                }else{
                    callback?.provideError(ServiceError)
                }
            }
        })
//        JokeService.Joke(object : ServiceCallback{
//            override fun returnSuccess(data: JokeCloud) {
//
//                callback?.provideSuccess(data.toJoke())
//            }
//
//            override fun returnError(errorType: ErrorType) {
//                when (errorType){
//                    ErrorType.NO_CONNECTION -> callback?.provideError(Noconnection)
//                    ErrorType.OTHER -> callback?.provideError(ServiceError)
//
//                }
//            }
//        })
    }

    override fun clear() {
        callback =null
    }

    override fun init(resultCallback: ResultCallback<Joke, Error>) {
        callback = resultCallback
    }
}