# Assignment: Data source and pipeline steps

## Overview

In this assignment, we will start to build the pipeline design. We want to implement a series of pipeline steps, which transform and analyze incoming data. Each pipeline step will consist of three parts:

1. Load data from the raw, clean, or derived layer
2. Transform or analyze the data (business logic)
3. Write the data to the clean or derived layer.

Your task is to implement the data loading functionality and define a first pipeline step. Specifically, you will focus on loading data from our `cln` (cleaned) layer, which you will then use in a pipeline step for analytics.

## 3-Layered Data Layout Overview

In our data pipeline architecture, we are introducing a structured 3-layered data layout. This layout helps in organizing the data processing stages effectively, allowing for a clear separation of concerns and better data management.

1. **Raw Layer (`raw`)**
  - **Purpose**: Stores unprocessed input data as received.
  - **Format**: CSV files.
  - **Usage**: Acts as the source of truth, capturing data in its original form for reference and reprocessing.

2. **Clean Layer (`cln`)**
  - **Purpose**: Holds cleaned and standardized data.
  - **Format**: Parquet files.
  - **Usage**: Primary source for all analytics, offering consistent and optimized data for processing.

3. **Derived Layer (`drv`)**
  - **Purpose**: Contains data produced by analytics and transformations.
  - **Format**: Various, depending on the analytical needs.
  - **Usage**: Provides final processed data for end-user consumption and reporting.

## Objective

Your primary objective is to create a modular design of the pipeline, consisting of reusable components. This will help in organizing and scaling our data processing tasks effectively. You’ll work on the following components:

1. **Data Source**: A generic method to load data using Spark.
2. **Pipeline Step**: An interface for pipeline steps, with a specific implementation for the `BestCategories` analytics.

## Tasks

### 1. Implement a Data Source

1. **Create a New Package: `datastores`**
  - In this package, you will manage data sources, focusing on loading data into Spark datasets.

2. **Define a Trait: `DataSource`**
  - This trait should declare a generic method `load[T](spark: SparkSession, path: String): Dataset[T]`.
  - The method should be generic and constrained by `scala.Product` and `scala.reflect.runtime.universe.TypeTag`.
  - Hint: Think about why Spark requires these constraints for type `T`.

3. **Implement the Trait: `ClnDataSource`**
  - This trait should implement the `load` method, specifically for loading data from the `cln` (cleaned) layer.
  - Consider the role of mixins in Scala and why we’re using a trait here.

### 2. Implement a Pipeline Step

1. **Create a New Package: `pipeline`**
  - This package will contain all classes and traits related to pipeline steps.

2. **Define a Trait: `PipelineStep`**
  - This trait should declare a method `run(): Unit`, serving as the entry point for each pipeline step.

3. **Implement the Analytics Step: `BestCategoriesPipelineStep`**
  - Create the `BestCategoriesPipelineStep` class, extending `PipelineStep`.
  - Mix in the `ClnDataSource` to enable loading data from the cleaned layer.
  - In the `run()` method, outline the sequence of operations (start with pseudocode) required to load, process, and display the data.
  - **Note:** Don't focus on the writing part yet. Instead, simply use `.show()` to display the result for now.

## Considerations

- **Modularity**: Think about how these components could be reused across different pipeline steps.
- **Type Safety**: Consider why the `load` method in `DataSource` is generic and how it ensures type safety.
- **Mixins**: Reflect on why mixins are used in Scala and how they differ from inheritance.

## Bonus Questions

1. **Why is the `DataSource` trait's `load` method constrained by `scala.Product` and `scala.reflect.runtime.universe.TypeTag`?**
  - Hint: Look at the encoders we have discussed previously. 

2. **What are the differences between a mixin and class inheritance in Scala, and why might you prefer a mixin in this context?**
