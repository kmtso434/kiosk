package com.example.newkiostk

class Data {
    fun Bugers(){
        val burger1 = Burger(1,"ShackBurger","기본",6900)
        val burger2 = Burger(2,"CheeseBurger","치즈버거",6900)
        val burger3 = Burger(3,"SalmaonBurger","연어버거",7200)

        val Burgers = listOf(
            burger1.BURGER().toString(),
            burger2.BURGER().toString(),
            burger3.BURGER().toString()
        )


    }


}