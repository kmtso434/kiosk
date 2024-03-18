package com.example.newkiostk

open class foods(val id: Int, val name : String, val desc : String, val price : Int) {
    fun FOOD(){
        println("$id $name $desc $price")
    }
}