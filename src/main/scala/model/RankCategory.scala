package de.bapiakula.sparkscalacourse
package model

import java.time.LocalDateTime

case class RankCategory(category: String, rank: Int, totalOrderVolume: Double, purchaseFrequency: Int, smallerExistence: LocalDateTime, name: String)
