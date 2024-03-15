package com.example.newkiostk

data class Category(
    val id : Int,
    val name : String,
    val desc : String
) {
    companion object{
        val burger = Category(1, " Burger ","매장에서 직접 만든 수제버거")
    }

    override fun toString(): String {
        return "$id $name $desc"
    }


}