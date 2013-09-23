object test {
  def pascal(c: Int, r: Int) = {
    def fact(n: Int) = {
      def fiter(acc: Int, n: Int): Int = {
        if (n == 0) acc else fiter(acc * n, n - 1)
      }
      fiter(1, n)
    }
    fact(r) / (fact(c) * fact(r - c))

  }                                               //> pascal: (c: Int, r: Int)Int

  pascal(1, 3)                                    //> 6
                                                  //| 1
                                                  //| 2
                                                  //| 2
                                                  //| res0: Int = 3
  pascal(2, 4)                                    //> 24
                                                  //| 2
                                                  //| 2
                                                  //| 2
                                                  //| res1: Int = 6
  pascal(0, 2)                                    //> 2
                                                  //| 1
                                                  //| 2
                                                  //| 2
                                                  //| res2: Int = 1
  pascal(1, 2)                                    //> 2
                                                  //| 1
                                                  //| 1
                                                  //| 1
                                                  //| res3: Int = 2
  pascal(3, 4)                                    //> 24
                                                  //| 6
                                                  //| 1
                                                  //| 1
                                                  //| res4: Int = 4
}