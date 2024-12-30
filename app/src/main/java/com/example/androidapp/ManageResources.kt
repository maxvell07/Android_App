package com.example.androidapp

import android.content.Context
import androidx.annotation.StringRes

interface ManageResources {

fun string(@StringRes resourceid: Int):String
class Base (private val context:Context):ManageResources{
    override fun string(resourceid: Int): String {

        return context.getString(resourceid)
    }

}
}