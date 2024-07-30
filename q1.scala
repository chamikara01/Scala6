object q1 {
  private val inventory1: Map[Int, Product] = Map(
    101 -> Product("ProdcutA", 10, 15.0),
    102 -> Product("ProdcutB", 5, 25.0),
    103 -> Product("ProdcutC", 20, 7.5)
  )

  private val inventory2: Map[Int, Product] = Map(
    102 -> Product("ProdcutB", 7, 30.0),
    104 -> Product("ProdcutD", 10, 20.0)
  )

  private val productNames: Iterable[String] = inventory1.values.map(_.name)
  productNames.foreach(println)

  private val totalValue: Double = inventory1.values.map(p => p.quantity * p.price).sum
  println(s"Total value of all products: $totalValue")

  private val isEmpty: Boolean = inventory1.isEmpty
  println(s"Is inventory1 empty? $isEmpty")

  private val mergedInventory: Map[Int, Product] = (inventory1.keySet ++ inventory2.keySet).map { id =>
    val p1 = inventory1.get(id)
    val p2 = inventory2.get(id)

    val mergedProduct = (p1, p2) match {
      case (Some(prod1), Some(prod2)) =>
        Product(prod1.name, prod1.quantity + prod2.quantity, math.max(prod1.price, prod2.price))
      case (Some(prod1), None) => prod1
      case (None, Some(prod2)) => prod2
      case _ => throw new Exception("This should never happen")
    }

    id -> mergedProduct
  }.toMap

  mergedInventory.foreach { case (id, product) => println(s"ID: $id, Product: $product") }

  private val productId = 102
  inventory1.get(productId) match {
    case Some(product) => println(s"Product found: $product")
    case None => println(s"Product with ID $productId not found.")
  }

  def main(args: Array[String]): Unit = {

    val names : Iterable[String] = productNames
    val total : Double = totalValue
    val empty : Boolean = isEmpty
    val merge : Map[Int, Product] = mergedInventory
    val checkId : Int = productId
  }
}
