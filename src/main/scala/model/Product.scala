package de.bapiakula.sparkscalacourse
package model

import java.time.LocalDateTime

case class Product(productId: String,
                   name: String,
                   category: String,
                   insertTimestamp: LocalDateTime
                  )
