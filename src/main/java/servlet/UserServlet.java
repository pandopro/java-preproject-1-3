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
public class UserServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/new".equals(action)) {
            req.getRequestDispatcher("addForm.jsp").forward(req, resp);
        } else if ("/delete".equals(action)) {
            userService.deleteUser(Long.parseLong(req.getParameter("id")));
            listOfUser(req, resp);
        } else if ("/edit".equals(action)) {
            req.getRequestDispatcher("editForm.jsp").forward(req, resp);
        } else if ("/list".equals(action)) {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/new".equals(action)) {
            userService.createUser(req.getParameter("name"), req.getParameter("email"), req.getParameter("country"));
            listOfUser(req, resp);
        } else if ("/edit".equals(action)) {
            User user1 = new User(Long.parseLong(req.getParameter("id")), req.getParameter("name"), req.getParameter("email"), req.getParameter("country"));
            userService.editUser(user1);
            listOfUser(req, resp);

        }
    }

    private void listOfUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listUser = userService.allUsers();
        req.setAttribute("listUser", listUser);
        req.setAttribute("size", listUser.size());
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}