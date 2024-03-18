package com.example.newkiostk

data class Category(
    val id : Int,
    val name : String,
    val desc : String
) {
    companion object{
        val burger = Category(1, " Burger ","매장에서 직접 만든 수제버거")
        val frozenCustard = Category(2, "FrozenCustard", "수제아이스크림")
        val drinks = Category(3, "Drinks", "음료")
        val alcohol = Category(4, "Alcohol", "술")
    }

    override fun toString(): String {
        return "$id $name $desc"
    }


}