package com.example.kotlin

import java.util.Scanner

class Calculator {
}

fun main() {
    val scanner = Scanner(System.`in`)

    println("欢迎使用控制台计算器！")
    println("请输入第一个数字：")
    val num1 = scanner.nextDouble()

    println("请输入第二个数字：")
    val num2 = scanner.nextDouble()

    println("请选择操作符：")
    println("1. 加法 (+)")
    println("2. 减法 (-)")
    println("3. 乘法 (*)")
    println("4. 除法 (/)")
    val operator = scanner.nextInt()

    val result = when (operator) {
        1 -> num1 + num2
        2 -> num1 - num2
        3 -> num1 * num2
        4 -> num1 / num2
        else -> {
            println("无效的操作符！")
            return
        }
    }

    println("计算结果：$result")
}