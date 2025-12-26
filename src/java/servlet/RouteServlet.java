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

@WebServlet("/addRoute")
public class RouteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        int distance = Integer.parseInt(request.getParameter("distance"));

        MongoDatabase db = MongoDBUtil.getDatabase();
        MongoCollection<Document> collection =
                db.getCollection("routes");

        Document doc = new Document("from", from)
                .append("to", to)
                .append("distance", distance);

        collection.insertOne(doc);

        // ✅ Redirect back to same page (NO dashboard.jsp)
        response.sendRedirect("route.jsp");
    }
}
