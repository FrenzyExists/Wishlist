package com.pikachu.codepathmail

import android.util.Log

class WishFetcher {
    companion object {
        private val wishes: MutableList<Wish> = mutableListOf() // A mutable list to store items
        private var pointer = 0
        fun getWishes(): MutableList<Wish> {
            return wishes
        }

        fun getNextWish(): Wish? {
            println(wishes.size)
            println(pointer+1)
            if (pointer+1 > wishes.size) {
                return null
            }
            val w = wishes[pointer]
            pointer +=1
            return w
        }

        fun getWish(position: Int): Wish {
            return wishes[position]
        }

        fun deleteWish(position: Int){
            wishes.removeAt(position)
        }

        fun getNext5Wishes(): MutableList<Wish> {
            val newWishes : MutableList<Wish> = ArrayList()
            if (pointer >= wishes.size )  {
                return newWishes
            }
            for (i in pointer..pointer+5) {
                newWishes.add(wishes[i])
            }
            pointer += 5
            return newWishes
        }

        fun addItem(item: String, store: String, price: Float, currency: String) {
            Log.v("Pressed", "TEsting $item")
            val wish = Wish(item, store, price, currency)
            wishes.add(wish)
        }
    }
}