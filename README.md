# Student Management System

A simple web-based student management application built with HTML, CSS, and JavaScript.

## Features

- **Add Students**: Add new students with name, email, course, and age
- **View Students**: Display all students in a clean table format
- **Edit Students**: Update existing student information
- **Delete Students**: Remove students from the system
- **Search Functionality**: Search students by name, email, or course
- **Export to CSV**: Export student data to a CSV file
- **Local Storage**: Data persists in browser's local storage

## How to Use

1. Open `home.html` in a web browser
2. Use the form to add new students:
   - Fill in the student's name, email, course, and age
   - Click "Add Student" to save
3. View all students in the table below
4. Use the search box to filter students
5. Click "Edit" to modify a student's information
6. Click "Delete" to remove a student
7. Use "Export to CSV" to download student data

## File Structure

- `home.html` - Main application interface
- `Student.java` - Java model class (for reference/backend integration)
- `StudentServlet.java` - Java servlet class (for backend integration)
- `README.md` - This documentation file

## Technologies Used

- HTML5
- CSS3
- JavaScript (ES6+)
- Local Storage API

## Browser Compatibility

Works in all modern browsers that support:
- ES6 JavaScript features
- Local Storage API
- CSS Grid/Flexbox

## Future Enhancements

- Database integration (MySQL, PostgreSQL)
- Backend API with Java Servlets
- User authentication
- Advanced filtering and sorting
- Bulk operations
- Data validation improvements
- Responsive design improvements

## Setup

No installation required! Simply open `home.html` in your web browser to start using the application.

## Data Persistence

All student data is stored in the browser's local storage. This means:
- Data persists between browser sessions
- Data is specific to the browser and device
- To reset data, clear browser local storage for this site
