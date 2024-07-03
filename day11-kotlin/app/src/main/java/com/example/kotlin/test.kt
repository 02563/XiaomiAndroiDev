package com.example.kotlin

class Greeter(val name: String) {
    fun greet() {
        println("Hello, $name")
    }
}

fun main(args: Array<String>) {
    Greeter("World!").greet()          // 创建一个对象不用 new 关键字
    val name = "John Doe"
    val age = 25
    val occupation = "Software Developer"

    println("Hello, my name is $name.")
    println("I am $age years old.")
    println("I work as a $occupation.")

}


