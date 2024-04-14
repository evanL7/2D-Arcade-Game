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

## Creating a JAR File
1. Open the terminal and navigate to the `Code/project` directory.

2. Create a JAR file of our game using the following command:
    ```
    mvn clean compile assembly:single
    ```
    This command will clean the project, compile the source code, and create a JAR file with all the dependencies included.

3. The JAR file will be generated in the `target` directory. You can find the JAR file with the name `Grade Quest-jar-with-dependencies.jar`.

## Generating JavaDocs

This will generate JavaDocs in the following directory: `target/site/apidocs`

1. Open the terminal and navigate to the `Code/project` directory.

2. Generate JavaDocs using the following command:
    ```
    mvn javadoc:javadoc
    ```

Note: Make sure you have Maven installed on your system before running the above commands.
