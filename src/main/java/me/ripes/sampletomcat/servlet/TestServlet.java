package me.ripes.sampletomcat.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        // Simple Hello World Site
        name = "Hello",
        urlPatterns = {"/hello"}
)

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write("Hello World!".getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
