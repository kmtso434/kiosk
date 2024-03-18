package com.example.newkiostk.lv5

open class Food(name : String, price:Double, category:String, desc : String) : Menu(name, desc){
    var price : Double
    var category : String

    init{
        this.price = price
        this.category = category
    }

    override fun displayInfo(){
        println ("카테고리: $category, 가격: $price, 이름: ${name}, 설명 : [ $desc ]")
    }
}