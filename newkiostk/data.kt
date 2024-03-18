package com.example.newkiostk

class Data {
    fun Bugers(){
        val burger1 = foods(1,"ShackBurger","기본",6900)
        val burger2 = foods(2,"CheeseBurger","치즈버거",6900)
        val burger3 = foods(3,"SalmaonBurger","연어버거",7200)
        val burger4 = foods(4,"CheeseBurger", "치즈버거", 6900)
        val burger5 = foods(5,"Big Mac","빅맥", 8400)

        val Burgers = listOf(
            burger1.FOOD().toString(),
            burger2.FOOD().toString(),
            burger3.FOOD().toString(),
            burger4.FOOD().toString(),
            burger5.FOOD().toString()
        )
    }

    fun FrozenCustard(){
        val FC1 = foods(1, "FrozenCustard", "바닐라", 2900)
        val FC2 = foods(2, "Chocalate", "초콜릿", 3900)
        val FC3 = foods(3,"Strawberry", "딸기", 3700)
        val FC4 = foods(4,"Nutty", "아몬드", 4900)
        val FC5 = foods(5, "Blue Berry", "블루베리", 5200)

        val Frozencustards = listOf(
            FC1.FOOD().toString(),
            FC2.FOOD().toString(),
            FC3.FOOD().toString(),
            FC4.FOOD().toString(),
            FC5.FOOD().toString()
        )
    }

    fun Drinks(){
        val drink1 = foods(1, "Ade", "에이드", 4000)
        val drink2 = foods(2, "Coffee", "커피", 4000)
        val drink3 = foods(3, "Juice", "주스", 3000)
        val drink4 = foods(4, "water","물", 10000)

        val drinks = listOf(
            drink1.FOOD().toString(),
            drink2.FOOD().toString(),
            drink3.FOOD().toString(),
            drink4.FOOD().toString()
        )
    }

    fun Alcohols(){
        val alcohol1 = foods(1, "Cocktail", "칵테일", 6000)
        val alcohol2 = foods(2, "Beers", "맥주", 5000)
        val alcohol3 = foods(3, "Magoly", "막걸리", 5900)
        val alcohol4 = foods(4, "Wine","와인", 8000)

        val alcohols = listOf(
            alcohol1.FOOD().toString(),
            alcohol2.FOOD().toString(),
            alcohol3.FOOD().toString(),
            alcohol4.FOOD().toString()
        )
    }


}