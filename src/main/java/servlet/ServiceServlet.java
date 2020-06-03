package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class ServiceServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/list".equals(action)) {
            listOfUser(req, resp);
        } else if ("/demo".equals(action)) {
            userService.createUser("Oleg", "oleg@mail.ru", "Russia");
            userService.createUser("Anton", "anton@mail.ru", "Russia");
            userService.createUser("Dima", "dima@mail.ru", "Russia");
            userService.createUser("Olga", "olga@mail.ru", "Ukraine");
            userService.createUser("Natasha", "natasha@mail.ru", "Ukraine");
            userService.createUser("Aleksandr", "akeks@mail.ru", "Belarus");
            listOfUser(req, resp);
        }
    }


    private void listOfUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("listUser", userService.allUsers());
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}