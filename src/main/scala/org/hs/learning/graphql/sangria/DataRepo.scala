package org.hs.learning.graphql.sangria

import org.hs.learning.graphql.sangria.DataSchema.{Product, ProductShopDiscount, Shop}


class DataRepo {

  val products :List[Product]= TestData.products
  val shops: List[Shop] = List[Shop]()
  val discounts :List[ProductShopDiscount] = List[ProductShopDiscount]()

  def getProducts = products

  def getProduct(id : String)={
    products.find(_.id==id)
  }

  def getShop(id :String) = {
    shops.find(_.id==id)
  }

  def getProductShopDiscount(pid :String, sid :String) = {
    discounts.find(a => a.pid==pid && a.sid==sid)
  }


}
