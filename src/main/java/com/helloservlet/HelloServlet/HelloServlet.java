package com.helloservlet.HelloServlet;

import com.helloservlet.HelloServlet.model.User;
import com.helloservlet.HelloServlet.model.UserHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet", description = "This is a hello service")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DatabaseConnector databaseConnector = MysqlDatabase.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(new Date());
        User user = new User(
                "gabi",
                "pass",
                dateString
        );
//        ((MysqlDatabase) databaseConnector).insertRow("users", user);

        HibernateHelper hibernateHelper = HibernateHelper.getInstance();
        Session hibernateHelperSession = hibernateHelper.getSession();
        Query query = hibernateHelperSession.createQuery("Select entity from UserHibernate entity", UserHibernate.class);
        List<UserHibernate> users = query.getResultList();
        for(UserHibernate userHibernate : users) {
            System.out.println("User: " + userHibernate);
        }


        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>" + message + "</h2>");
        out.println("</body></html>");

//        response.sendRedirect("https://www.google.ro/"); // "instruieste" clientul sa trimita
        // o noua cerere catre acest url
        Integer x = new Integer(23);
        Cookie cookie = new Cookie("userId", "8372");
        Cookie cookie1 = new Cookie("cevaInt",  x.toString());
        response.addCookie(cookie);

        HttpSession session = request.getSession();
//        String n=(String)session.getAttribute("uname");

        session.setAttribute("namne", "Vasilica");
        session.setAttribute("adevarat", false);
        Object myObject = new Object();
        System.out.println("Obiect printat: " + myObject);
        session.setAttribute("obiect", myObject);



        // "forward": send request to google, preia raspunsul, trimite-mi raspunsul lui Google

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("{ \"name\": \"John\", \"age\":30, \"car\":null }");

        System.out.println("Vrei sa stergi pe " + request.getParameter("victima"));

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(403);
        PrintWriter out = response.getWriter();

        // citim headere
        Enumeration<String> headerNames = request.getHeaderNames();
        String headerName = headerNames.nextElement();
        System.out.println("Primul header: " + headerName + ": " + request.getHeader(headerName));

        System.out.println("Vrei sa stergi pe " + request.getParameter("victima"));

        out.println("N-ai acces");


    }

    public void destroy() {
    }
}