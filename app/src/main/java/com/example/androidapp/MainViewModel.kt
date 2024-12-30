package com.example.androidapp


class MainViewModel(private val model: Model<Joke,Error>) {

    private  var textCallback:TextCallback = TextCallback.Empty()

    fun getJoke(){
//    textCallback.provideText("res")
    model.fetch()

    }
    fun init(textCallback: TextCallback){
        this.textCallback =textCallback
        model.init(object :ResultCallback<Joke,Error>{
            override fun provideSuccess(data: Joke) {
                textCallback.provideText(data.getJokeUi())
            }
            override fun provideError(data: Error) {
                textCallback.provideText(data.message())
            }
        })
    }

    fun clear() {
        textCallback = TextCallback.Empty()
    }
}
interface TextCallback{
    fun provideText(text:String)
    class Empty:TextCallback{
        override fun provideText(text: String) = Unit
    }
}