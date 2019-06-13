package com.sf.jkt.k.entity

import java.io.Serializable

data class User(val id:Long,val username:String,val password:String):Serializable{



    companion object {
        private const val serialVersionUID = 4453467635945716529L
    }


                
}

