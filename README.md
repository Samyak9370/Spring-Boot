Employee Management System 🧑‍💼
A RESTful Employee Management System built using Spring Boot, designed to manage employee data efficiently. This project serves as the backend service for operations like employee creation, update, deletion, and listing, demonstrating a complete workflow from a frontend user interface to a backend REST API connected to a database.

✨ Features
• Create, Read, Update, and Delete (CRUD) operations for employees.
• RESTful API for all backend operations.
• Simple and responsive user interface to interact with the data.
• Clear separation between frontend, backend, and database layers.
🖥️ Frontend Showcase
FrontEnd Page:-
This is the main user interface where users can view, add, and manage employees.

<img width="1823" height="850" alt="Screenshot 2025-07-26 132411" src="https://github.com/user-attachments/assets/60de95ff-d796-4198-9b48-6c3e61ef2b1a" />
This is for the Delete Operation:-
<img width="1890" height="781" alt="Screenshot 2025-07-26 132606" src="https://github.com/user-attachments/assets/ffe2caff-494b-4999-88d9-ab06c700823e" />


⚙️ Backend: REST API
The backend is a RESTful service built with Spring Boot. It exposes several endpoints to manage employee data.
API Endpoints
| Method | Endpoint                 | Description                        |
|:-------|:-------------------------|:-----------------------------------|
| GET    | /api/v1/employees        | Get a list of all employees.       |
| GET    | /api/v1/employees/{id}   | Get a single employee by their ID. |
| POST   | /api/v1/employees        | Create a new employee.             |
| PUT    | /api/v1/employees/{id}   | Update an existing employee.       |
| DELETE | /api/v1/employees/{id}   | Delete an employee by their ID.    |

🔌 Architecture: Connecting the Pieces
Frontend ↔️ Backend Connection
The frontend application (built with HTML, CSS, and JavaScript) communicates with the backend through HTTP requests.

• When a user performs an action (like clicking "Add Employee"), the JavaScript code sends a fetch request to the corresponding backend API endpoint (e.g., POST /api/v1/employees).
• The backend processes the request, interacts with the database, and sends a JSON response back to the frontend.
• The frontend then dynamically updates the user interface based on the response.

Backend ↔️ Database Connection
The Spring Boot backend handles the connection to the database (e.g., MySQL, PostgreSQL).

• The connection details (URL, username, password) are configured in the application.properties file.
• We use Spring Data JPA and Hibernate as the ORM (Object-Relational Mapping) tool. This allows us to map Java Employee objects directly to the employees table in the database without writing raw SQL queries.










































