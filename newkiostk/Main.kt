package com.example.newkiostk

fun main() {
    class Kiosk {
        private val names = mutableListOf<String>()
        private val total = mutableListOf<Int>()

        fun category() {
            println("어서오세요")
            print("${Category.burger}\n${Category.frozenCustard}\n${Category.drinks}\n${Category.alcohol}\n5.장바구니 \n0.종료\n")
            var choice = readLine()!!.toInt()
            val data = Data()

            when (choice){
                1-> {
                    println("[ BURGERS ]")
                    data.Bugers()
                    println("메뉴를 선택해주세요")

                    var choice = readLine()!!.toInt()
                    when(choice) {
                        1 -> cart(1,"ShackBurger","기본",6900)
                        2 -> cart(2,"CheeseBurger","치즈버거",6900)
                        3 -> cart(3,"SalmanBurger","연어버거",7200)
                        4 -> cart(4, "CheeseBurger", "치즈버거",6900)
                        5 -> cart(5, "Big Mac", "빅맥", 8400)
                        else -> {
                            println("다시 선택해주세요")
                            category()
                        }
                    }
                    category()
                }
                2->{
                    println("[ Frozen Custards ]")
                    data.FrozenCustard()
                    println("메뉴를 선택해주세요")
                    var choice = readLine()!!.toInt()
                    when(choice){
                        1 -> cart(1, "FrozenCustard", "바닐라", 2900)
                        2 -> cart(2, "Chocalate", "초콜릿", 3900)
                        3 -> cart(3,"Strawberry", "딸기", 3700)
                        4 -> cart(4,"Nutty", "아몬드", 4900)
                        5 -> cart(5, "Blue Berry", "블루베리", 5200)
                        else -> {
                            println("다시 선택해주세요")
                            category()
                        }
                    }
                    category()
                }
                3->{
                    println("[ Drinks ]")
                    data.Drinks()
                    println("메뉴를 선택해주세요" )
                    var choice = readLine()!!.toInt()
                    when(choice){
                        1 -> cart(1, "Ade", "에이드", 4000)
                        2 -> cart (2, "Coffee", "커피", 4000)
                        3 -> cart(3, "Juice", "주스", 3000)
                        4 -> cart(4, "water","물", 10000)
                        else -> {
                            println("다시 선택해주세요")
                            category()
                        }
                    }
                    category()
                }
                4->{
                    println("[ Alcohols ]")
                    data.Alcohols()
                    println("메뉴를 선택해주세요" )
                    var choice = readLine()!!.toInt()
                    when(choice){
                        1 -> cart(1, "Cocktail", "칵테일", 6000)
                        2 -> cart (2, "Beers", "맥주", 5000)
                        3 -> cart(3, "Magoly", "막걸리", 5900)
                        4 -> cart(4, "Wine","와인", 8000)
                        else -> {
                            println("다시 선택해주세요")
                            category()
                        }
                    }
                }
                5-> display()
                0->print("종료되었습니다")
                else -> {
                    println("다시 선택해주세요")
                    category()
                }
            }

        }

        fun cart(id: Int, name : String, desc : String, price : Int){
            println("선택하신것이 $id $name $desc $price 가 맞으십니까?")
            println("1. 예 | 2. 아니요")
            var choice = readLine()!!.toInt()
            when(choice){
                1->{
                    names.add(name)
                    total.add(price)

                    for (i in names.indices){
                        print("${names[i]} ${total[i]}")
                    }
                    println("추가되었습니다")
                }
                2-> print("메인화면으로 돌아갑니다")
                else -> {
                    println("다시 선택해주세요")
                    cart(id, name, desc, price)
                }
            }
            category()

        }

        fun display(){
            println("[장바구니] ")
            if(names.isEmpty()){
                print("\n장바구니가 비어있습니다.")

            } else {
                for (i in names.indices){
                    println("${names[i]} ${total[i]}")
                }
                println("주문하시겠습니까?")
                println("1.예 | 2.아니요")

                var yesorno = readLine()!!.toInt()
                when(yesorno){
                    1->{
                        var totalPrice = 0
                        for (price in total) {
                            totalPrice += price
                        }
                        println("총 가격은 $totalPrice 입니다.")
                        cash()
                    }
                    2->{
                        print("메인화면으로 돌아갑니다")
                        category()
                    }
                    else -> {
                        println("다시 선택해주세요")
                        display()
                    }

                }
            }

        }

        fun cash (){
            println("아래에 금액을 입력해주세요")
            var money = readLine()!!.toInt()
            var totals = 0
            for (price in total) {
                totals += price
            }


            if(money > totals){
                println("총 $totals 결제되셨습니다")
                total.clear()
                names.clear()

            } else {
                println("잔액이 부족합니다")
                println("메인화면으로 돌아갑니다")
            }

            category()

        }



    }

    Kiosk().category()
}