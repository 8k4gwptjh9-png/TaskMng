package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.TaskDAO;
import model.Task;

@WebServlet("/taskForm")
public class TaskFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr != null) {

            int id = Integer.parseInt(idStr);

            TaskDAO dao = new TaskDAO();
            Task task = dao.findById(id);

            // 編集用データをJSPへ
            request.setAttribute("task", task);
        }

        // 共通画面へ
        request.getRequestDispatcher("/WEB-INF/views/task.jsp").forward(request, response);
    }
}