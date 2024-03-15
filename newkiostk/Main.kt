package com.example.newkiostk

fun main() {
    class Kiosk {
        private val names = mutableListOf<String>()
        private val total = mutableListOf<Int>()
        
        fun category() {
            print("어서오세요")
            print("${Category.burger} \n5.장바구니 \n0.종료")
            var choice = readLine()!!.toInt()
            val data = Data()

            when (choice){
                1-> {
                    data.Bugers()
                    println("선택하세요")

                    var choice = readLine()!!.toInt()
                    when(choice) {
                        1 -> cart(1,"ShackBurger","기본",6900)
                        2 -> cart(2,"CheeseBurger","치즈버거",6900)
                        3 -> cart(3,"SalmaonBurger","연어버거",7200)
                    }
                    category()
                }
                5-> display()
                0->print("종료되었습니다")
                else -> throw IllegalArgumentException("다시 선택해주세요")
            }

        }

        fun cart(id: Int, name : String, desc : String, price : Int){
            println("선택하신것이 $id $name $desc $price 가 맞으십니까?")
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
            }
            category()

        }

        fun display(){
            println("[장바구니] ")
            if(names.isEmpty()){
                print("장바구니가 비어있습니다.")

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
                    }
                    2->{
                        print("메인화면으로 돌아갑니다")
                        category()
                    }

                }
            }

        }



    }

    Kiosk().category()
}