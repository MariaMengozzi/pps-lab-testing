package testLab

  import org.scalatest.flatspec.{AnyFlatSpec}
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

class WarehouseTest extends AnyFreeSpec with Matchers:
  "Getting from an empty warehouse returns 0" - {
    val warehouse = new BasicWarehouse()
    val p = new Product("a")
    assert(warehouse.get(p, 5) == (p, 0))
  }

class BasicShoppingTest extends AnyFlatSpec with Matchers:
  // https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/flatspec/AnyFlatSpec.html#getFixtureMethods
  class Fixture {
    val p1 = Product("Hat")
    val warehouse = new BasicWarehouse
    val catalog = new BasicCatalog(Map[Product,Price](
      p1 -> Price(34)
    ))
    val cart = new BasicCart()
    val shopping = new Shopping(warehouse, catalog, cart, new BasicLogger(">> "))
  }
  def fixture = new Fixture

  "total cost for picking 10 p1 items" should "be 340" in {
    val f = fixture
    f.warehouse.supply(f.p1, 50)
    f.shopping.pick(f.p1, 10)
    assert(f.cart.totalCost == 340)
    }

