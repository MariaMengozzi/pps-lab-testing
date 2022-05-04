package testLab.steps

import io.cucumber.scala.{EN, ScalaDsl}
import org.junit.jupiter.api.Assertions

import testLab.*

class ShoppingFeatureSteps extends ScalaDsl with EN:
  var shopping: Shopping = _
  var p1: Product = _
  var warehouse: BasicWarehouse = _
  var cart: BasicCart = _
  var catalog: BasicCatalog = _
  var item: Item = _

  Given("""^a shopping with (\d+) p1 products that costs (\d+)$""") { (n: Int, p: Int) =>
    /*warehouse = new BasicWarehouse()
    p1 = new Product("Hat")
    warehouse.supply(p1, n)
    catalog = new BasicCatalog(Map[Product, Price](
      p1 -> new Price(p)
    ))
    cart = new BasicCart()
    shopping = new Shopping(warehouse, catalog, cart, new BasicLogger(">> "))*/

  }

  When("""^buy (\d+) item of p1$""") { (qt: Int) =>
    //shopping.pick(p1, qt)
  }

  Then("""^I have to pay (\d+)$""") { (expected: Int) =>
    //Assertions.assertEquals(expected, cart.totalCost)
  }


