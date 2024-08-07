
To-Do List Project
---
This project is a To-Do List application that allows users to manage tasks and categories. It provides functionality to create, update, and delete tasks and categories.
Project Structure

The project is structured as follows:
- `` src/main/java/dev/Yass/to_do_list/controller: Contains controllers for handling HTTP requests related to tasks and categories.``
- `` src/main/java/dev/Yass/to_do_list/service: Contains services for managing tasks and categories.``
- `` src/main/java/dev/Yass/to_do_list/model: Contains model classes for tasks and categories.``
- `` src/test/java/dev/Yass/to_do_list/controller: Contains test classes for testing the controllers.``

---
Functionality
---
`Task Controller`:
TodoController.java: Contains endpoints for creating tasks. It validates that the title of the task is not blank before saving it.

``Category Controller``
CategoryController.java: Contains endpoints for creating categories.

``Category Service``
CategoryService.java: Contains a method to create a default main category named "Tasks" and save it in the database.

``Testing``
TodoControllerTest.java: Contains test cases for the TodoController class. It includes tests for creating a task with a blank title, and testing the createTodo method.
Running the Application
To run the application, you can use a Java IDE or build tools like Maven. Make sure to set up the database connection and configure the application properties accordingly.

``Testing``
The project includes unit tests for the controllers using Mockito and Spring MVC Test. You can run the tests to ensure the functionality works as expected.
Feel free to explore the codebase and make any necessary modifications or enhancements to suit your requirements. If you have any questions or need assistance, please reach out to the project contributors.
Thank you for checking out the To-Do List project!