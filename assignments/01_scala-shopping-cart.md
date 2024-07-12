# Assignment: Scala Shopping Cart

## Task Description

You are given a list of transactions in a shopping cart system. Each transaction contains an `item`, a `quantity`, and a `pricePerUnit`.
Implement a Scala function to compute the total cost of the cart.
Apply a discount if the total cost exceeds a certain threshold and print the receipt in the specified format.

### Requirements

1. **Define a case class** `Transaction` that includes `item: String`, `quantity: Int`, and `pricePerUnit: Double`.
2. **Implement a function** `computeTotalCost` that:
  - Computes the total cost of all transactions.
  - Applies a discount if the total cost exceeds `discountThreshold`. The discount is calculated as `totalCost * discountRate`.
  - Returns the final total cost after applying the discount, if any.
3. **Implement a function** `printReceipt` that:
  - Prints each transaction in the format: `Item: [item], Quantity: [quantity], Price per unit: [pricePerUnit], Total: [total]`.
  - Prints the total cost, the applied discount and the final total price for the cart at the end.

### Example

Given the following list of transactions:

```scala
val transactions = List(
  Transaction("Apple", 2, 1.5),
  Transaction("Banana", 5, 0.8),
  Transaction("Orange", 3, 1.2)
)
```

If the `discountThreshold` is `10.0` and the `discountRate` is `0.1` (10%), the functions should produce the following output:

```
Item: Apple, Quantity: 2, Price per unit: 1.5, Total: 3.0
Item: Banana, Quantity: 5, Price per unit: 0.8, Total: 4.0
Item: Orange, Quantity: 3, Price per unit: 1.2, Total: 3.6
Total cost: 10.60
Applied Discount (10%): -1.06
Final price: 9.54
```

### Instructions

1. **Implement the functionality** as described above. Even though this is a small and simple example, structure your code in a clean and extensible way.
2. **Write tests** for those units, which are worthy to test. Define test cases which cover various cases and edge cases to ensure your code is working correctly.
