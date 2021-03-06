package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val u1 = union(s1, s2)
    val u2 = union(s1, s3)

    val positiveNumbers = union( singletonSet(1), singletonSet(300))
    val negativeNumbers = union( singletonSet(-50), singletonSet(-42))
    val negativeAndPositiveNumbers = union( negativeNumbers, positiveNumbers)
    val evenNumbers = union( singletonSet(2), singletonSet(4))
    val oddNumbers = union( singletonSet(3), singletonSet(5))
    val oddEvenNumbers = union( evenNumbers, oddNumbers)
    val hundreds = union( singletonSet(100), singletonSet(200))
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      assert(contains(u1, 1), "Union 1")
      assert(contains(u1, 2), "Union 2")
      assert(!contains(u1, 3), "Union 3")
    }
  }

  test("intersect of two setes") {
    new TestSets {
      val i = intersect(u1, u2)
      assert( contains(i, 1))
      assert( !contains(i, 2))
      assert( !contains(i, 3))
    }
  }

  test("diff of two sets") {
    new TestSets {
      val d = diff( u1, u2)
      assert( contains(d, 2))
      assert( !contains(d, 1))
      assert( !contains(d, 3))
    }
  }

  test("predicate") {
    new TestSets {
      val u3  = union(u1, s3)
      val filter1 = filter(u3, elem => elem > 2)
      assert( contains(filter1, 3))
      assert( !contains(filter1, 2))

      val filter2 = filter(u3, elem => elem > 1)
      assert( !contains(filter2, 1))
      assert( contains(filter2, 3))
      assert( contains(filter2, 2))
    }
  }

  test("forall test"){
    new TestSets {
      assert( forall( positiveNumbers, elem => elem > 0))
      assert( forall( negativeNumbers, elem => elem < 0))
      assert( !forall( negativeAndPositiveNumbers, elem => elem > 0))
      assert( forall( evenNumbers, elem => elem % 2 == 0))
      assert( forall( oddNumbers, elem => elem % 2 == 1))
      assert( !forall( oddEvenNumbers, elem => elem % 2 == 0))
      assert( forall( hundreds, elem =>  elem % 100 == 0))
    }
  }

  test("Exsists test") {
    new TestSets {
      assert( exists(oddEvenNumbers, elem => elem == 5))
      assert( !exists( oddEvenNumbers, elem => elem > 10))
      assert( exists( hundreds, elem => elem > 150))
      assert( ! exists( hundreds, elem => elem > 500))
    }
  }

  test("Map test" ) {
    new TestSets {
      assert( forall(map(oddNumbers, elem => elem + 1), x => x % 2 == 0))
      assert( forall( map( evenNumbers, elem => elem * 10), x => x % 10 == 0))

      assert( forall( map( singletonSet(10), elem => elem * 100 + 10), x => x  == 1010))
    }
  }
}
