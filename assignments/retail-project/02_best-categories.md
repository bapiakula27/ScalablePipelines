# Retail Project Assignment 2: Best Categories

In this assignment, we are going to implement the first analytics use-case.

**Task:** Identify the best performing product categories by their total order volume.

**Requirements:**

- Products are ranked by their total oder volume across all purchases
- If two products have an equal volume, rank them by the following criteria (ordered by precedence)
  1. Larger total order volume
  2. Lower purchase frequency (total items sold)
  3. Smaller existence period of category
  4. Alphanumerical category name

**Instructions:**

1. We won't work with data we read. Rather, we adopt a test-driven development approach. Don't focus on loading data or storing results. Simply start with a unit test by specifying the input data and expected result. This is very similar to what we did in the Scala assignments.
2. Create a new package `analytics` and within that an object `BestCategories`.
3. Within the `BestCategories` object, implement a method `calculate(purchaseDs: Dataset[Purchase], productDs: Dataset[Product]): DataFrame`. It's fine to return a `DataFrame` for now. You can treat the `Dataset`s as `DataFrame`s to implement the logic.
4. Create a new package `src/test/scala/analytics/BestCategoriesTest` which will hold the test code.
5. Add a couple of tests, asserting that the ranking criteria are implemented correctly. Hint: You will have to instantiate the `SparkSession` in the test class.
