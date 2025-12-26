package servlet;

import db.MongoDBUtil;
import dsa.Graph;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import org.bson.Document;

@WebServlet("/shortest")
public class ShortestPathServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String src = request.getParameter("src");
        String dest = request.getParameter("dest");

        Graph graph = new Graph();
        MongoDatabase db = MongoDBUtil.getDatabase();

        MongoCollection<Document> stops =
                db.getCollection("stops");
        MongoCollection<Document> routes =
                db.getCollection("routes");

        // ✅ Load stops safely
        for (Document d : stops.find()) {
            String stop = d.getString("stop");
            if (stop != null) {
                graph.addStop(stop);
            }
        }

        // ✅ Load routes safely
        for (Document r : routes.find()) {
            String from = r.getString("from");
            String to = r.getString("to");
            Integer distance = r.getInteger("distance");

            if (from != null && to != null && distance != null) {
                graph.addRoute(from, to, distance);
            }
        }

        int result = graph.shortestPath(src, dest);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // ---------- UI OUTPUT ----------
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Shortest Path</title>");
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css'>");

        out.println("<style>");
        out.println("body{font-family:Segoe UI,Arial;background:linear-gradient(135deg,#e0f2fe,#f8fafc);padding:40px;text-align:center;}");
        out.println(".card{max-width:500px;margin:auto;padding:30px;border-radius:16px;box-shadow:0 20px 40px rgba(0,0,0,0.15);}");
        out.println(".success{background:linear-gradient(135deg,#22c55e,#4ade80);color:white;}");
        out.println(".error{background:linear-gradient(135deg,#ef4444,#f87171);color:white;}");
        out.println("h1{font-size:48px;margin:20px 0;}");
        out.println("i{font-size:40px;margin-bottom:15px;}");
        out.println("</style></head><body>");

        if (result == -1) {
            out.println("<div class='card error'>");
            out.println("<i class='fa-solid fa-triangle-exclamation'></i>");
            out.println("<h2>Invalid Input</h2>");
            out.println("<p>Please check stop names and routes.</p>");
            out.println("</div>");
        } else {
            out.println("<div class='card success'>");
            out.println("<i class='fa-solid fa-route'></i>");
            out.println("<h2>Shortest Route Found</h2>");
            out.println("<h1>" + result + " km</h1>");
            out.println("<p>Calculated using <b>Dijkstra’s Algorithm</b></p>");
            out.println("</div>");
        }

        out.println("</body></html>");
    }
}
