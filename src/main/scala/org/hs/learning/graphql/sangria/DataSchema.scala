package org.hs.learning.graphql.sangria

import java.time.LocalDateTime

import sangria.schema._
import sangria.macros.derive._

object DataSchema {


  //Scala Specific Schema
  trait Identifiable {
    def id: String
  }

  case class Product(id: String, name: String, category: String) extends Identifiable

  case class Shop(id: String, name: String, location: String) extends Identifiable

  case class ProductShopDiscount(pid: String, sid: String, cost: String , discount: String, validFrom : String, validTo: String )

  case class DiscountsByProducts(pid: String, productShopDiscounts: List[ProductShopDiscount])

  case class DiscountsByShops(sid: String, productShopDiscounts: List[ProductShopDiscount])


  // Graphql Sangria Schema
  val ProductType = ObjectType("Product"
    ,"The product catalog",
    fields[Unit,Product](
     // Field("id", StringType, description = Some("Product Id"), resolve = _.value.id),
      Field("name", StringType,description = Some("Product Name"), resolve = _.value.name),
      Field("category", StringType,description = Some("Product Category"), resolve = _.value.category)
    )
  )

  val ShopType = deriveObjectType[Unit,Shop](
    ObjectTypeName("Shop"),
    ObjectTypeDescription("Shop catalog"),
    DocumentField("id","Shop Id"),
    DocumentField("name", "Shop Name"),
    DocumentField("location", "Shop Location")
  )

  val productShopDiscount = deriveObjectType[Unit,ProductShopDiscount](
    ObjectTypeName("ProductShopDiscount"),
    ObjectTypeDescription("Discount of a product in a shop"),
    DocumentField("pid","Product Id"),
    DocumentField("sid", "Shop Id"),
    DocumentField("cost", "Product cost"),
    DocumentField("discount","Product discount"),
    DocumentField("validFrom", "offer valid from date"),
    DocumentField("validTo", "offer valid to date")
  )

  val IdentifiableType = InterfaceType(
    "Identifiable",
    "Entity that can be identified",
    fields[Unit, Identifiable](
      Field("id", StringType, resolve = _.value.id)))

  /*
  val DiscountsByProductsType = deriveObjectType[Unit,DiscountsByProducts](
    ObjectTypeName("DiscountsByProducts"),
    ObjectTypeDescription("All the discounts for a product")
  )

  val DiscountsByShopsType = deriveObjectType[Unit,DiscountsByShops](
    ObjectTypeName("DiscountsByShop"),
    ObjectTypeDescription("All the discounts for a shop")
  )
  */

  val Id = Argument("id", StringType)

  val QueryProducts = ObjectType(
    "QueryProducts",
    fields[DataRepo, Unit](
      Field("products", ListType(ProductType),
        description = Some("Return all Products"),
        resolve = c => c.ctx.getProducts)
    )
  )

  val QueryProductById = ObjectType(
    "QueryProductbyID",
    fields[DataRepo,Unit](
      Field("product" , OptionType(ProductType),
        description = Some("Returns a product with specific `id`."),
        arguments = Id :: Nil,
        resolve = c ⇒ c.ctx.getProduct(c.arg(Id)))
    )
  )

  val schema = Schema(QueryProducts)

}
