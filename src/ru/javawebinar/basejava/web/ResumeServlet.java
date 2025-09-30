package ru.javawebinar.basejava.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ResumeTestData;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/resume")
public class ResumeServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Resume r = ResumeTestData.createResume(UUID.randomUUID().toString(), "Ivan");
        response.setContentType("text/html");
        response.getWriter().println("<table> " +
                "<tr>" +
                " <th> UUID </th>" +
                "<th>Name</th>" +
                "</tr>" +
                "<tr>" +
                "<td>" + r.getUuid() + "</td>" +
                "<td>" + r.getFullName() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td>" + r.getSections() + "</td>" +
                "</tr>" +
                "</table>");
    }
}