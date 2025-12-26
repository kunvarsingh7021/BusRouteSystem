package servlet;

import db.MongoDBUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import org.bson.Document;
import org.bson.types.ObjectId;

@WebServlet("/deleteStop")
public class DeleteStopServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        ObjectId id = new ObjectId(request.getParameter("id"));

        MongoDatabase db = MongoDBUtil.getDatabase();
        MongoCollection<Document> col =
                db.getCollection("stops");

        col.deleteOne(new Document("_id", id));

        response.sendRedirect("viewData.jsp");
    }
}
