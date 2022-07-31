# MYKRELLO

My Krello is a task management software, for a better development, and optimize the execution of the work, taking an online production, allowing to have a status of the tasks and to know the progress of the project, this program allows to manage the flow of the project and design alternative plans to effectively and efficiently meet the proposed objectives.

## BACKEND 

The development of the software logic has been carried out in java with the help of the Spring Boot platform, for the development of the application, automatically configuring the application according to the dependencies. 
This project is distributed in three folders, the controller that allows connecting the user with the project server, the model folder in which a modeling of the database tables is done and a utilities folder where different classes are developed as a tool for the operation of the program.

In the folder contoller we can find the different routes or endpoints that allow to contact the service and give an answer to the request made by the user. Among these we can find the following: 


| Boards EndPoints   | Method | Description                      |
| ------------------ | ------ | -------------------------------- |
| /api/v1/boards     | GET    | Get all boards                   |
| /api/v1/boards     | POST   | Create a board                   |
| /api/v1/boards/:id | GET    | Get a detail board given it a id |
| /api/v1/boards/:id | PUT    | Update a board                   |
| /api/v1/boards/:id | DELETE | Delete a board                   |

Table. 1 Endpoints for board crud


| Tasks EndPoints                    | Method | Description                                        |
| ---------------------------------- | ------ | -------------------------------------------------- |
| /api/v1/tasks/:id                  | GET    | Get a task give it a id                            |
| /api/v1/tasks                      | POST   | Create a task using a json from the request's body |
| /api/v1/tasks/move/:taskId/:column | PATCH  | Update a task given it a id                        |
| /api/v1/tasks/:id                  | PUT    | Update a task given it a id                        |
| /api/v1/tasks/:id                  | DELETE | Delete a task given it a id                        |

Table. 2 Endpoints for the crud of the tasks

## FRONTEND
The development of the application, with which the user interacts, was developed in vanilla JavaScript, modifying the DOM through javaScript, and managing classes for the development of the different functionalities of the application.