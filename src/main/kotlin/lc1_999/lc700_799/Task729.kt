package lc1_999.lc700_799

import lc1_999.lc700_799.Task729.MyCalendar
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*


//Solved using a hint
//https://leetcode.com/problems/my-calendar-i/
class Task729 {
    class MyCalendar {
        private val myCal = TreeSet<Pair<Int, Int>>(compareBy { it.first })
        fun book(startTime: Int, endTime: Int): Boolean {
            val list = myCal.toList()
            var index = list.binarySearch(startTime to 0, compareBy { it.first })
            if (index < 0) index = -index - 1
            if ((index == 0 || startTime >= list[index - 1].second) && (index == list.size || endTime <= list[index].first)) {
                myCal.add(startTime to endTime)
                return true
            }
            return false
        }
    }
}

private class Task729Test {

    @Test
    fun test1() {
        val myCalendar = MyCalendar()
        Assertions.assertEquals(true, myCalendar.book(10, 20))
        // return False, It can not be booked because time 15 is already booked by another event.
        Assertions.assertEquals(false, myCalendar.book(15, 25))
        // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
        Assertions.assertEquals(true, myCalendar.book(20, 30))
    }

    @Test
    fun test2() {
        val myCalendar = MyCalendar()
        Assertions.assertEquals(true, myCalendar.book(10, 20))
        Assertions.assertEquals(false, myCalendar.book(15, 25))
        Assertions.assertEquals(true, myCalendar.book(20, 30))
        Assertions.assertEquals(true, myCalendar.book(35, 40))
    }

    @Test
    fun test3() {
        val myCalendar = MyCalendar()
        Assertions.assertEquals(true, myCalendar.book(10, 20))
        Assertions.assertEquals(true, myCalendar.book(30, 40))
        Assertions.assertEquals(false, myCalendar.book(15, 25))
        Assertions.assertEquals(false, myCalendar.book(30, 40))
    }

    @Test
    fun test4() {
        val myCalendar = MyCalendar()
        Assertions.assertEquals(true, myCalendar.book(47, 50))
        Assertions.assertEquals(true, myCalendar.book(33, 41))
        Assertions.assertEquals(false, myCalendar.book(39, 45))
        Assertions.assertEquals(false, myCalendar.book(33, 42))
        Assertions.assertEquals(true, myCalendar.book(25, 32))
        Assertions.assertEquals(false, myCalendar.book(26, 35))
        Assertions.assertEquals(true, myCalendar.book(19, 25))
        Assertions.assertEquals(true, myCalendar.book(3, 8))
        Assertions.assertEquals(true, myCalendar.book(8, 13))
        Assertions.assertEquals(false, myCalendar.book(18, 27))
    }
}