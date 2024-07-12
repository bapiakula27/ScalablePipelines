# Session 1 (2024-06-25)

## Setting Your Goal

What's your current situation – what do you already know?

- Spark & Scala basics (DataFrames, scripts, windowing, aggregations, no Datasets)
- Extensive PySpark experience (also streaming with RDDs)
- GCP (run Spark job, AirFlow), AWS (getting started, EMR basics)
- Spark: General concepts (7/10, executors, WebUI, memory, partitioning, mitigating performance problems)

Imagine 12 weeks ahead in time – where do you want to stand?

- Be able to design and implement generic and extensible applications
- Clean code (readable)
- Scala: Data Engineering focus, clean-structured data processing pipeline (software engineering practices), relevant language features, practical mixture of OO and functional style
- Spark: Refresher on internals & execution model
- AWS: Refresher on EMR deployment and monitoring
- Optionally: Streaming

Use-case suggestion

- Adding business logic (fields) to tables in the processing pipeline in a generic self-owned way (adding source file & configuration)

## Setting Up Your Development Environment

- IntelliJ
- Scala
- JVM
- GitHub

## Assignments

- Please find the assignment in `assignments/01_scala-shopping-cart.md`
- Push your code to the GitHub repository and create a PR

# Session 2 (2024-07-05)

## What we did

- Setup GitHub repository (add remote, add .gitignore file, create branch)
- Review assignment code
  - Break up methods into isolated clearly defined units
  - Don't have functional units have side effects
  - If a method/function name does not reflect what it's doing, it's probably mixing multiple functionalities
- Write unit tests for the units
  - Create a test class in the same package, but in the `test` directory of your project

## Assignments

- Set up your git cli to access your GitHub repo
- Refactor your `ShoppingCart` implementation
- Write unit tests for your `ShoppingCart` implementation 
  - Add the scalatest dependency to your `build.sbt` file (`"org.scalatest" %% "scalatest" % "3.2.17" % "test"`)
  - Implement a test class which extends the `AnyFunSuite` coming from the scalatest package (`class FirstAssignmentTest extends AnyFunSuite`)
  - Write some unit tests for your modular units
- Push your solution to the GitHub Repo
