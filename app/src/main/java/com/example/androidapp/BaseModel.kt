package com.example.androidapp

class BaseModel(
    private val JokeService:JokeService,
    private val manageResources: ManageResources
):Model<Joke,Error> {
    private val Noconnection = Error.NoConnection(manageResources)
    private val ServiceError = Error.ServiceUnavaileble(manageResources)
    private var callback:ResultCallback<Joke,Error>? = null

    override fun fetch() {
        JokeService.Joke(object : ServiceCallback{
            override fun returnSuccess(data: JokeCloud) {

                callback?.provideSuccess(data.toJoke())
            }

            override fun returnError(errorType: ErrorType) {
                when (errorType){
                    ErrorType.NO_CONNECTION -> callback?.provideError(Noconnection)
                    ErrorType.OTHER -> callback?.provideError(ServiceError)

                }
            }
        })
    }

    override fun clear() {
        callback =null
    }

    override fun init(resultCallback: ResultCallback<Joke, Error>) {
        callback = resultCallback
    }
}