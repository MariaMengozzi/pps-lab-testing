package testLab

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class CartTest extends AnyFunSuite :
  test ("A new cart has size 0") {
    assert(new BasicCart(Map.empty).size == 0 )
  }

class CatalogTest extends AnyFlatSpec with Matchers:
  "An hat" should "cost 34 euros" in {
    val (p1,p2) = (Product("Shoe"), Product("Hat"))
    val catalog = new BasicCatalog(Map[Product,Price](
      p1 -> Price(78),
      p2 -> Price(34)
    ))
    assert(catalog.priceFor(p2, 1) == Price(34))
  }

class BasicShopping extends AnyFreeSpec with Matchers:
  "shop with a catalog and a warehouse with 50 items of p1 product" - {
    val p1 = Product("Hat")
    val warehouse = new BasicWarehouse
    warehouse.supply(p1, 50)
    val catalog = new BasicCatalog(Map[Product,Price](
      p1 -> Price(34)
    ))
    "And given a chart" - {
      val cart = new BasicCart()
      val shopping = new Shopping(warehouse, catalog, cart, new BasicLogger(">> "))
      "piking some 10 items of p1" - {
        shopping.pick(p1, 10)
        "gain 340 euros" - {
          assert(cart.totalCost == 340)
        }
      }
    }
  }
