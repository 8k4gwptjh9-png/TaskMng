package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.TaskDAO;
import model.Task;
import model.User;

@WebServlet("/taskList")
public class TaskListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            // 未ログイン時はLoginServletへリダイレクト
            response.sendRedirect("login");
            return;
        }

        User user = (User) session.getAttribute("loginUser");
        TaskDAO dao = new TaskDAO();
        List<Task> taskList = dao.findAll(user.getId());

        request.setAttribute("taskList", taskList);
        
        request.getRequestDispatcher("/WEB-INF/views/task.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}