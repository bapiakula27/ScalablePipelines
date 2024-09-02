package de.bapiakula.sparkscalacourse
package model

import java.time.LocalDateTime

case class Purchase(
                     purchaseId: String,
                     customerId: String,
                     productId: String,
                     quantity: Int,
                     pricePerUnit: Double,
                     timestamp: LocalDateTime
                   )
