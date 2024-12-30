package com.example.androidapp

import java.util.TimerTask

class TestModel(
    private val manageResources: ManageResources
):Model<Joke,Error> {

    private var callback:ResultCallback<Joke,Error>? = null

    private var count = 0
    override fun fetch() {
        java.util.Timer().schedule(object :TimerTask(){
            override fun run() {
                if (count % 2 == 1){
                    callback?.provideSuccess(Joke("rand joke $count", "punchline"))
                } else if (count%3 ==0){
                    callback?.provideError(Error.NoConnection(manageResources))
                }else {
                    callback?.provideError(Error.ServiceUnavaileble(manageResources))
                }
                count++
            }
        },5000)

    }


    override fun init(callback: ResultCallback<Joke , Error>) {
        this.callback =callback
    }

    override fun clear() {
        callback = null
    }


}