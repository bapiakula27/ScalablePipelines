# Assignment: Scala Shopping Cart with Categories

## Task Description

Building on the previous assignment, you will now extend the functionality of the shopping cart system by including categories for each item. Discounts will only be applied to specific categories if the total cost for that category exceeds a given threshold.

## Requirements

1. **Modify the case class** `Transaction` to include a `category: String` field.
2. **Compute categorized total cost:** 
  - Compute the total cost per category.
3. **Apply discount to categories:**
  - Apply a discount only to those categories whose total cost exceeds `discountThreshold`. The discount is calculated as `totalCost * discountRate` for each eligible category.
  - Return the final total cost after applying the discount to eligible categories.
4. **Update the function** `printReceipt` to:
  - Print each transaction in the format: `Item: [item], Category: [category], Quantity: [quantity], Price per unit: [pricePerUnit], Total: [total]`.
  - Print the total cost per category, the applied discount per category, and the final total price for the cart at the end.

## Example

Given the following list of transactions:

```scala
val transactions = List(
  Transaction("Apple", "Fruit", 2, 1.5),
  Transaction("Banana", "Fruit", 5, 0.8),
  Transaction("Orange", "Fruit", 3, 1.2),
  Transaction("Shampoo", "Personal Care", 2, 4.5)
)
```

If the `discountThreshold` is `10.0` and the `discountRate` is `0.1` (10%), the functions should produce the following output:

```
Item: Apple, Category: Fruit, Quantity: 2, Price per unit: 1.5, Total: 3.0
Item: Banana, Category: Fruit, Quantity: 5, Price per unit: 0.8, Total: 4.0
Item: Orange, Category: Fruit, Quantity: 3, Price per unit: 1.2, Total: 3.6
Item: Shampoo, Category: Personal Care, Quantity: 2, Price per unit: 4.5, Total: 9.0

Total cost per category:
Category: Fruit, Total: 10.6
Category: Personal Care, Total: 9.0

Applied Discount:
Category: Fruit, Discount (10%): -1.06

Final price per category:
Category: Fruit: 9.54
Category: Personal Care: 9.0

Final total price: 18.54
```

## Instructions

- Implement the new functionality 
- Add unit tests for the new functionality