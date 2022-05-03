package testLab

import org.scalacheck.Prop.{exists, forAll}
import org.scalacheck.{Arbitrary, Gen, Prop, Properties}


class PropertyBasedTestOnList extends Properties("Seqs"):
  def genericAssociativeProp[A: Arbitrary]: Prop =
    forAll{ (xs: List[A], ys: List[A], zs: List[A]) => (xs ++ ys) ++ zs == xs ++ (ys ++ zs)}

  def genericAddToEmpty[A: Arbitrary]: Prop =
    forAll { (xs: List[A]) => List() ++ xs == xs}

  def genericIdentity[A: Arbitrary]: Prop =
    forAll { (xs: List[A]) => xs.map(identity) == xs }

  property("List associative props") = genericAssociativeProp[Int]
  property("Add to an empty list") = genericAddToEmpty[String]
  property("identity") = genericIdentity[Int]
  property("composition") = forAll { (xs: List[Int], f: Int => Int, g: Int => Int) => xs.map(f compose g) == xs.map(g).map(f)}



class PropBasedPalindrome extends Properties("Palindrome"):
  import TestPalindrome.Palindrome

  val palindromeGen: Gen[Palindrome] =
    for s <- Gen.alphaStr
        c <- Gen.option(Gen.alphaChar)
    yield Palindrome(s + c.getOrElse("") + s.reverse)

  property("palindrome") = forAll(palindromeGen) { case (p: Palindrome) =>
    p.isPalindrome
  }

object TestPalindrome:
  case class Palindrome(s: String):
    def isPalindrome: Boolean = s == s.reverse