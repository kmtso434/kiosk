package com.example.newkiostk.lv5

import java.time.LocalDateTime
import java.util.Timer
import java.util.TimerTask
import kotlinx.coroutines.delay


val menus : MutableList<Menu> = ArrayList()
val foods : MutableList<Food> = ArrayList()
val orders : MutableList<Order> = ArrayList()
var money : Double = 0.0
var now = LocalDateTime.now()
var start = LocalDateTime.of(now.year, now.month, now.dayOfMonth, 1, 1, 0)
var end = LocalDateTime.of(now.year, now.month, now.dayOfMonth, 1,2,0)

suspend fun main(){
    init()

    while(true){
        displayMenu()

        var categorySelect = getPureNumber()
        if(categorySelect == 0){
            println("프로그램을 3초뒤에 종료합니다")
            globalDelay(3000)
            return
        }

        var selectedObject = selectMenu(categorySelect)
        globalDelay(3000)
        selectedObject?.let { obj ->
          addOrder(obj)
        } ?: run{
            println("\n현재 잔핵: $money\n")
        }
    }
}

fun init(){
    money = 100.0

    menus.add(Menu("Burgers", "Classic Burger"))
    menus.add(Menu("Frozen Custard", "Ice Cream"))
    menus.add(Menu("Drinks", "House Drink"))
    menus.add(Menu("Beer", "Homemade Beer"))
    menus.add(Menu("Order", "order"))
    menus.add(Menu("Cancel", "cancel"))

    foods.add(Food("Shack Burger", 6.9,"Burgers", "Classic burger"))
    foods.add(Food("Smoke Burger", 8.9, "Burgers", "Smokey burger"))
    foods.add(Food("Shrrome Burger", 9.4,"Burgers", "Monster Cheese Burger"))
    foods.add(Food("Cheese Burger", 6.9, "Burgers", "Chesse topping burger"))
    foods.add(Food("Hamburger", 5.4, "Burgers", "Beef petty burger"))

    foods.add(Food("Plain Ice Cream", 12.1,"Frozen Custard", "Vanilla Ice Cream"))
    foods.add(Food("Chocalate Icecream", 10.2, "Frozen Custard", "Sweet Ice cream"))
    foods.add(Food("Fruits Ice Cream", 15.14, "Frozen Custard", "Ice Cream topping with fruit"))
    foods.add(Food("Nuts Ice Cream", 15.14, "Frozen Custard", "Almond Ice Cream"))
    foods.add(Food("Ice Milk", 9.9, "Frozen Custard", "low fat Ice Cream"))

    foods.add(Food("Ade", 7.5, "Drinks", "Ade"))
    foods.add(Food("Americano", 6.4, "Drinks", "Coffee"))
    foods.add(Food("Beverage", 7.7, "Drinks", "Drinks"))
    foods.add(Food("Black Tea", 7.7, "Drinks", "홍자"))
    foods.add(Food("Barley Tea", 8.9, "Drinks", "보리차"))

    foods.add(Food("Bokbunja" , 16.2, "Beer", "복분자"))
    foods.add(Food("Bourbon", 19.2, "Beer", "Whiskey"))
    foods.add(Food("Cocktail", 15.4, "Beer", "Cocktail"))
    foods.add(Food("Gin", 25.2, "Beer", "Gin"))
    foods.add(Food("Armond de Brignac", 999.99, "Beer", "Champaine"))

    checkOrder()
}

fun displayMenu(){
    println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요")
    println("[MY SHOP MENU]")

    val maxNameLength = menus.maxOfOrNull {it.name.length} ?:0
    var menuSize = menus.size
    var count = 1

    for(idx in 1..menuSize){
        val menu = menus[idx-1]
        val name = menu.name
        if(name == "Order") println("[ ORDER MENU ]")

        val desc = menu.desc
        val padding = " ".repeat(maxNameLength - name.length)
        println("$idx. $name$padding | $desc")
        count++
    }

    println("0. 종료 | 프로그램 종료")
}

fun getPureNumber():Int{
    var userInput: String?
    var number: Int?

    while(true){
        print("번호를 입력해주세요")
        userInput = readLine()
        number = userInput?.toIntOrNull()

        if(number != null){
            return number
        } else {
            println("올바른 숫자를 입력해주세요")
        }
    }
}

fun selectMenu(cateNumber : Int): Food?{
    var menu = menus[cateNumber-1]
    var categoryName = menu.name

    if(categoryName != "Order" && categoryName != "Cancel"){
        var filteredFoods = foods.filter { it.category == categoryName }
        displayMenuDetail(categoryName)

        while(true){
            val selectFoodNumber = getPureNumber()
            if(selectFoodNumber > filteredFoods.size || selectFoodNumber < 0){
                println("올바른 숫자를 입력해주세요")
            } else if (selectFoodNumber == 0){
                return null
            }else {
                return filteredFoods[selectFoodNumber -1]
            }
        }
    } else {
        when(categoryName){
            "Order" -> {
                var totalOrderPrice = displayOrderDetail()
                if (totalOrderPrice < 0.0){
                    println("주문 내역이 존재하지 않습니다")
                    return null
                }

                println("1. 주문 \n 2. 메뉴판")
                while(true){
                    var selectOrderNumber = getPureNumber()
                    when (selectOrderNumber){
                        1-> {
                            var isMaintainance = isMaintainance()
                            if(isMaintainance.first){
                                println("현재 시각은 ${isMaintainance.second.hour}시 ${isMaintainance.second.minute}분입니다.")
                                println("은행 점검 시간은 ${start.hour}시 ${start.minute} ~ ${end.hour}시 ${end.minute}분입니다")
                            } else if (money >= totalOrderPrice){
                                orders.clear()
                                money -= totalOrderPrice
                                println("결제를 완료했습니다 ${isMaintainance.second.toString()}")
                            }
                            return null
                        }
                        2->{
                            println("메뉴판으로 이동합니다")
                            return null
                        }
                        else -> println("올바른 숫자를 입력해주세요")
                    }
                }
            }

            "Cancel" -> {
                orders.clear()
                println("메뉴판으로 이동합니다.")
                return null
            }
            else -> return null
        }
    }
}

fun displayMenuDetail(categoryName : String){
    println("\n[ ${categoryName} MENU]")
    var filteredFoods = foods.filter { it.category == categoryName}

    var maxNameLength = filteredFoods.maxOfOrNull {it.name.length} ?:0
    var maxPriceLength = filteredFoods.maxOfOrNull { it.price.toString().length }?:0
    var foodSize = filteredFoods.size

    for (i in 1..foodSize){
        val food = filteredFoods[i -1]
        val name = food.name
        val price = food.price
        val desc = food.desc

        val namePadding = " ".repeat(maxNameLength - name.length)
        val pricePadding = " ".repeat(maxPriceLength - price.toString().length)
        println(" $i. $name$namePadding | W $price$pricePadding | $desc")
    }
    val backPadding = " ".repeat(maxNameLength - "0. back".length)
    println("0. back$backPadding | 뒤로가기")
}

fun addOrder(food: Food){
    food.displayInfo()
    print("위 메뉴를 장바구니에 추가하시겠습니까?\n\n1. 예 | 2. 아니요")

    while(true){
        var selectOrderNumber = getPureNumber()
        when(selectOrderNumber){
            1 -> {
                orders.add(Order(food))
                println("${food.name}를 장바구니에 추가했습니다.")
                return
            }
            2 -> {
                println("구매를 취소했습니다")
                return
            }
            else -> println("올바른 숫자를 입력해주세요")
        }
    }
}

fun displayOrderDetail(): Double {
    var orderSize = orders.size
    if(orderSize > 0){
        println("\n아래와 같이 주문하시겠습니까?\n")

        println("[ Orders ]")
        for (i in 0 until orderSize){
            orders[i].food.displayInfo()
        }

        println("[ Total ]")
        val totalOrderPrice = orders.fold(0.0) { accmulator, order ->
            accmulator + order.food.price
        }

        println("W $totalOrderPrice")
        return totalOrderPrice

    }else {
        return -1.0
    }
}

suspend fun globalDelay(time: Long){
    delay(3000)
}

fun isMaintainance(): Pair<Boolean, LocalDateTime>{
    var now = LocalDateTime.now()

    return Pair (now.toLocalTime() >= start.toLocalTime() && now.toLocalTime() <= end.toLocalTime(), now)
}

fun checkOrder(){
    var timer = Timer()
    timer.schedule(object: TimerTask(){
        override fun run() {
            println("\n 현재 주문 대기수: ${orders.size}")
        }
    },0,5000)
}