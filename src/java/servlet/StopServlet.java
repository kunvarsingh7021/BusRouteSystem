package servlet;

import db.MongoDBUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.bson.Document;

@WebServlet("/addStop")
public class StopServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String stop = request.getParameter("stop");

        MongoDatabase db = MongoDBUtil.getDatabase();
        MongoCollection<Document> collection =
                db.getCollection("stops");

        collection.insertOne(new Document("stop", stop));

        // ✅ Redirect back to same page (NO dashboard.jsp)
        response.sendRedirect("stop.jsp");
    }
}
