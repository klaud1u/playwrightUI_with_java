# Playwright UI Tests with Java

## Requirements

- Git
- JDK 27
- Maven
- Node.js and npm — for Allure reports

Set `JAVA_HOME` to your JDK installation folder and add Maven's `bin` folder to `PATH`.

Verify:

```bash
java -version
mvn -version
node --version
npm --version
```

## Clone the project

```bash
git clone https://github.com/klaud1u/playwrightUI_with_java.git
```


## Run all tests and collect Allure results
```bash
mvn clean test
```

Maven downloads the dependencies, and Playwright Java downloads its browsers automatically on the first run.

If Maven cannot delete `target`, stop running tests then try:
```bash
Remove-Item -LiteralPath ".\target" -Recurse -Force -ErrorAction Stop
````
then
```bash
mvn clean test
````

## Run a single test class:

```bash
mvn clean test "-Dtest=ReadOnlyInputsTest"
```

Replace `ReadOnlyInputsTest` with the class you want to run.



Run all tests with retries:

```bash
mvn clean test "-Dsurefire.rerunFailingTestsCount=2"
```

## Open the Allure report

After the tests finish:
```bash
npx allure serve target/allure-results
```

## Open Playwright Trace Viewer
Run from the project folder:
```bash
 mvn exec:java "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=show-trace"
```
Select a `trace.zip` file from `target/test-artifacts`.

Alternatively, open https://trace.playwright.dev/ and select the trace file.

Do not extract `trace.zip`.
