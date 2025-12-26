package servlet;

import db.MongoDBUtil;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import java.io.IOException;

@WebServlet("/addStudent")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String stop = request.getParameter("stop");

        MongoDatabase db = MongoDBUtil.getDatabase();
        MongoCollection<Document> collection =
                db.getCollection("students");

        Document doc = new Document("name", name)
                .append("stop", stop);

        collection.insertOne(doc);

        // ✅ Redirect back to same module page (NO dashboard.jsp)
        response.sendRedirect("student.jsp");
    }
}
