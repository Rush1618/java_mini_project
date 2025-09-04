import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {
    private List<Student> students = new ArrayList<>();
    private int nextId = 1;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listStudents(out);
                break;
            case "add":
                showAddForm(out);
                break;
            case "edit":
                showEditForm(request, out);
                break;
            case "delete":
                deleteStudent(request, out);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            addStudent(request, out);
        } else if ("update".equals(action)) {
            updateStudent(request, out);
        }
    }
    
    private void listStudents(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
        out.println("table { border-collapse: collapse; width: 100%; margin: 20px 0; }");
        out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println(".btn { padding: 5px 10px; margin: 2px; text-decoration: none; border: 1px solid #ccc; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Student Management System</h1>");
        out.println("<a href='?action=add' class='btn'>Add New Student</a>");
        out.println("<h2>Student List</h2>");
        
        if (students.isEmpty()) {
            out.println("<p>No students found.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>Age</th><th>Actions</th></tr>");
            
            for (Student student : students) {
                out.println("<tr>");
                out.println("<td>" + student.getId() + "</td>");
                out.println("<td>" + student.getName() + "</td>");
                out.println("<td>" + student.getEmail() + "</td>");
                out.println("<td>" + student.getCourse() + "</td>");
                out.println("<td>" + student.getAge() + "</td>");
                out.println("<td>");
                out.println("<a href='?action=edit&id=" + student.getId() + "' class='btn'>Edit</a>");
                out.println("<a href='?action=delete&id=" + student.getId() + "' class='btn'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
        }
        
        out.println("</body>");
        out.println("</html>");
    }
    
    private void showAddForm(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add Student</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
        out.println("form { max-width: 400px; }");
        out.println("input, button { margin: 5px 0; padding: 8px; width: 100%; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Add New Student</h1>");
        out.println("<form method='post'>");
        out.println("<input type='hidden' name='action' value='add'>");
        out.println("Name: <input type='text' name='name' required><br>");
        out.println("Email: <input type='email' name='email' required><br>");
        out.println("Course: <input type='text' name='course' required><br>");
        out.println("Age: <input type='number' name='age' required><br>");
        out.println("<button type='submit'>Add Student</button>");
        out.println("</form>");
        out.println("<br>");
        out.println("<a href='?action=list'>Back to List</a>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void showEditForm(HttpServletRequest request, PrintWriter out) {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = findStudentById(id);
        
        if (student != null) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Student</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
            out.println("form { max-width: 400px; }");
            out.println("input, button { margin: 5px 0; padding: 8px; width: 100%; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edit Student</h1>");
            out.println("<form method='post'>");
            out.println("<input type='hidden' name='action' value='update'>");
            out.println("<input type='hidden' name='id' value='" + student.getId() + "'>");
            out.println("Name: <input type='text' name='name' value='" + student.getName() + "' required><br>");
            out.println("Email: <input type='email' name='email' value='" + student.getEmail() + "' required><br>");
            out.println("Course: <input type='text' name='course' value='" + student.getCourse() + "' required><br>");
            out.println("Age: <input type='number' name='age' value='" + student.getAge() + "' required><br>");
            out.println("<button type='submit'>Update Student</button>");
            out.println("</form>");
            out.println("<br>");
            out.println("<a href='?action=list'>Back to List</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void addStudent(HttpServletRequest request, PrintWriter out) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        int age = Integer.parseInt(request.getParameter("age"));
        
        Student student = new Student(nextId++, name, email, course, age);
        students.add(student);
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Added</title>");
        out.println("<meta http-equiv='refresh' content='2;url=?action=list'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Student added successfully!</h2>");
        out.println("<p>Redirecting to student list...</p>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void updateStudent(HttpServletRequest request, PrintWriter out) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        int age = Integer.parseInt(request.getParameter("age"));
        
        Student student = findStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setEmail(email);
            student.setCourse(course);
            student.setAge(age);
        }
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Updated</title>");
        out.println("<meta http-equiv='refresh' content='2;url=?action=list'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Student updated successfully!</h2>");
        out.println("<p>Redirecting to student list...</p>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void deleteStudent(HttpServletRequest request, PrintWriter out) {
        int id = Integer.parseInt(request.getParameter("id"));
        students.removeIf(student -> student.getId() == id);
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student Deleted</title>");
        out.println("<meta http-equiv='refresh' content='2;url=?action=list'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Student deleted successfully!</h2>");
        out.println("<p>Redirecting to student list...</p>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}
