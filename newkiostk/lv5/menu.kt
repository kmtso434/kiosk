package com.example.newkiostk.lv5

open class Menu (name: String, desc: String){
    var name : String
    var desc : String

    init {
        this.name  = name
        this.desc = desc
    }

    open fun displayInfo(){
        println("이름 : $name, 설명 : [ $desc ]")
    }
}