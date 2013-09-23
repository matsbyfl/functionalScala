package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1 else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }


  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def loop(chars: List[Char], count: Int): Int = {
      if (chars.isEmpty || count < 0)
        count
      else if (chars.head == '(')
        loop(chars.tail, (count + 1))
      else if (chars.head == ')')
        loop(chars.tail, count - 1)
      else
        loop(chars.tail, count)
    }
    if (loop(chars, 0) != 0) false else true
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def count(remaining: Int, denominations: List[Int]): Int = {
      if (remaining == 0)
        1
      else if (remaining < 0 || (denominations.isEmpty && remaining >= 1))
        0
      else
        count(remaining, denominations.tail) + count(remaining - denominations.head, denominations)
    }
    count(money, coins.sorted)
  }
}
