# Group 3 Project

## How to Run the Game

1. Open the terminal and navigate to the `Code/project` directory.

2. Compile the Maven project by running the following command:
    ```
    mvn compile
    ```

3. Execute the main file using the following command:
    ```
    mvn exec:java -Dexec.mainClass="Main"
    ```

## How to Test the Game

1. Open the terminal and navigate to the `Code/project` directory.

2. Run the tests using the following command:
    ```
    mvn test
    ```

## Generating JavaDocs

This will generate JavaDocs in the following directory: `target/site/apidocs`

1. Open your terminal and navigate to the project directory:
    ```
    cd Code/project
    ```

2. Generate JavaDocs using the following command:
    ```
    mvn javadoc:javadoc
    ```

Note: Make sure you have Maven installed on your system before running the above commands.
