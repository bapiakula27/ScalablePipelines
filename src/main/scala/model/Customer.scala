package de.bapiakula.sparkscalacourse
package model

import java.time.LocalDate

case class Customer(customerId: String,
                    firstName: String,
                    lastName: String,
                    dateOfBirth: LocalDate,
                    joinDate: LocalDate)
