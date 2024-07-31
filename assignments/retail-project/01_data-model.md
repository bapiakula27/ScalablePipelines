# Retail Project Assignment 1: Data Model

We are going to implement an analytics pipeline for an online retail store, consisting of multiple pipeline steps. Our focus is on software engineering best-practices, keeping an extensible software design and writing unit tests.

## Data Model

For the scope of this project, we will work with the following, simplified data model.

**Purchase:**

- `purchaseId: String`
- `customerId: String`
- `productId: String`
- `quantity: Int`
- `pricePerUnit: Float`
- `timestamp: DateTime`

**Product:**

- `productId: String`
- `name: String`
- `category: String`
- `insertTimestamp: DateTime`

**Customer:**

- `customerId: String`
- `firstName: String`
- `lastName: String`
- `dateOfBirth: Date`
- `joinDate: Date`

## Assignment

Your first assignment is to create the data classes with the respective fields. I recommend to introduce a package `model` which will hold the data object definitions. As more data sources (objects) are added, these should be added to this package as well. As discussed in the session, we could either use case classes or define the schema explicitly. I recommend using case classes for better readability of the code.
