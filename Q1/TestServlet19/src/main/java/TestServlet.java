import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String deptName = request.getParameter("deptName");
        String numStr = request.getParameter("numStudents");
        String action = request.getParameter("action");

        int numStudents = 0;
        if (numStr != null && !numStr.isEmpty()) {
            numStudents = Integer.parseInt(numStr);
        }

        ServiceClass service = new ServiceClass();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><style>");
        out.println("table { border-collapse: collapse; width: 50%; }");
        out.println("th, td { border: 1px solid #333; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("</style></head><body>");

        out.println("<h2>Action: " + action + "</h2>");

        switch (action) {

            case "Insert":
                if (service.insertDB(deptName, numStudents)) {
                    out.println("<p>Inserted successfully.</p>");
                } else {
                    out.println("<p>Insertion failed.</p>");
                }
                break;

            case "View":
                List<String> departments = service.viewDB();
                if (departments.isEmpty()) {
                    out.println("<p>No departments found.</p>");
                } else {
                    out.println("<table>");
                    out.println("<tr><th>Department Name</th><th>Number of Students</th></tr>");

                    for (String dept : departments) {
                        String[] parts = dept.split(", Students: ");
                        String dName = parts[0].replace("Department: ", "");
                        String count = parts.length > 1 ? parts[1] : "";
                        out.println("<tr><td>" + dName + "</td><td>" + count + "</td></tr>");
                    }
                    out.println("</table>");
                }
                break;

            case "Update":
                if (service.updateDB(deptName, numStudents)) {
                    out.println("<p>Updated successfully.</p>");
                } else {
                    out.println("<p>Update failed. Department not found?</p>");
                }
                break;

            case "Delete":
                if (service.deleteDB(deptName)) {
                    out.println("<p>Deleted successfully.</p>");
                } else {
                    out.println("<p>Delete failed. Department not found?</p>");
                }
                break;

            default:
                out.println("<p>Unknown action.</p>");
        }

        // Show Date & Time (IMPORTANT for screenshot)
        out.println("<br><p><b>Current Date & Time:</b> " + new java.util.Date() + "</p>");

        out.println("</body></html>");
    }
}
