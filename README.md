# Running Code, Tests, and Generating Coverage Reports

## Prerequisites

Android Studio Hedgehog version (or later)
A project with written tests
## Running Tests with Coverage

Locate the test directory: In the Project window, navigate to the src/test/java directory (or your project's designated test directory).
Right-click and select "Run with Coverage": Right-click on the test directory or any specific test file and select "Run 'Tests in 'java'' with Coverage" (or a similar option).
## Viewing Coverage Results

The Coverage tool window will open automatically after test execution, displaying coverage details, including:
Package, class, method, and line coverage percentages (highlighted in green for covered and red for uncovered).
Click on packages, classes, and methods to view coverage at different levels.
Hover over individual lines to see coverage information for specific lines of code.
## Generating HTML Coverage Reports

Open Run/Debug Configurations: Go to Run > Edit Configurations or press Ctrl+Shift+A (Cmd+Shift+A on macOS).
Select your test configuration: Choose the test configuration you used for running tests with coverage.
Navigate to the Code Coverage tab: Click on the Code Coverage tab.
Enable HTML report generation: Check the box for "Generate coverage report" and specify the desired output directory.
Run tests with coverage again: Click the Run button (or the green play button).
Access the report: After the tests complete, open the generated HTML report in your web browser.
## Additional Notes

Customizing coverage settings: Explore additional configuration options in the Run/Debug Configurations dialog.
Using command-line tools: For advanced usage or integration with CI/CD pipelines, consider tools like jacoco or gradle-android-test-plugin.
Third-party tools: Explore third-party plugins or extensions for enhanced coverage features and visualizations.

## Remember: As time was short so no proper report is generated and verified.
