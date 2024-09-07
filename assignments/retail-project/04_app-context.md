# Assignment: Application Configuration and Context

## Overview

In this assignment, you will enhance the structure of our pipeline by introducing configuration management and application context. You will implement two critical components: `AppConfig` and `AppContext`. These components will allow you to load configurations, create Spark sessions, and pass the necessary context throughout the pipeline. You’ll also complete your `DataSource` and `DataSink` implementations and run a pipeline step using the application context.

## Tasks

### 1. Implement the `AppConfig` Class

1. **Purpose**: `AppConfig` is responsible for loading configuration files and making values accessible to the application.
2. **Constructor**: The class should take one constructor argument, which is the path to the config file. By default, it should use `application.conf` located in the `src/main/resources` directory (which is the default directory in Java/Scala applications).
3. **Library**: Use the `com.typesafe.config` library to load the configuration.
4. **Spark Configuration**:
   - Load all Spark-related configuration under the `spark` key.
   - The values should be loaded generically and made available as a `SparkConf` object.
   - This `SparkConf` will be used to construct the `SparkSession`.

   **Hint**: Think about how you will extract the Spark-related configurations from the config file and convert them to a `SparkConf` object.

### 2. Implement the `AppContext` Class

1. **Purpose**: `AppContext` will encapsulate the `AppConfig` and `SparkSession`.
2. **Functionality**:
   - The class will hold an instance of `AppConfig`.
   - Use the Spark configuration from `AppConfig` to create a  `SparkSession`.
   - Every application will instantiate a single `AppContext` and pass it around to other components.

   **Hint**: Consider how the Spark session initialization depends on `AppConfig`. Why does it make sense to pass this context around the application?

### 3. Complete the `DataSource` and `DataSink` Implementations

1. **DataSource**:
   - The `DataSource` should receive an `AppContext` object implicitly.
   - Use the `AppContext` to access the `SparkSession` for reading data from the appropriate directories.
2. **DataSink**:
   - Similarly, `DataSink` should also receive an `AppContext` object implicitly.
   - It should use the `AppContext` for writing data to the specified directories.

   **Hint**: Think about how implicit parameters are used in Scala. How do they simplify the passing of frequently-used objects like `AppContext`?

### 4. Get Your Pipeline Step to Run

1. **Pipeline Step**:
   - The pipeline step should receive an `AppContext` object.
   - Use the context for both reading data via `DataSource` and writing data (or displaying it) via `DataSink`.
2. **Download Example Data**: Download the example parquet [here](https://drive.google.com/drive/folders/1FHo133ScOft44a914stS_l182yosO-A4?usp=drive_link).
3. **Run the Step**: Ensure your pipeline step is functional and uses the `AppContext` for all its operations.

## Bonus Questions

1. **Why do we pass `AppContext` implicitly to `DataSource` and `DataSink` instead of passing it explicitly? What are the advantages of using implicit parameters in this case?**
   - Discuss how implicit parameters affect code readability and reusability in Scala.

2. **Why do we use a class (`AppConfig`) for configuration management instead of a singleton object?**
   - Reflect on the implications of using classes versus singleton objects in Scala for configuration management and state handling.
