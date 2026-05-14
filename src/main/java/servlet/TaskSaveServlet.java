package servlet;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.TaskDAO;
import model.Task;
import model.User;

@WebServlet("/taskSave")
public class TaskSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");

        // 未ログイン対策
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // パラメータ取得
        String idStr = request.getParameter("id");
        String title = request.getParameter("title");
        String detail = request.getParameter("detail");
        String deadlineStr = request.getParameter("deadline");
        String priorityStr = request.getParameter("priority");

        if (title == null || title.isEmpty() ||
            detail == null || detail.isEmpty() ||
            deadlineStr == null || deadlineStr.isEmpty() ||
            priorityStr == null || priorityStr.isEmpty()) {

            request.setAttribute("error", "すべての項目を入力してください");

            // 入力値保持
            Task task = new Task();
            task.setTitle(title);
            task.setDetail(detail);

            if (deadlineStr != null && !deadlineStr.isEmpty()) {
                try {
                    task.setDeadline(Date.valueOf(deadlineStr));
                } catch (Exception e) {}
            }

            if (priorityStr != null && !priorityStr.isEmpty()) {
                try {
                    task.setPriority(Integer.parseInt(priorityStr));
                } catch (Exception e) {}
            }

            request.setAttribute("task", task);

            request.getRequestDispatcher("/WEB-INF/views/task.jsp").forward(request, response);
            return;
        }

        Date deadline = Date.valueOf(deadlineStr);
        int priority = Integer.parseInt(priorityStr);

        Task task = new Task();
        task.setUserId(user.getId());
        task.setTitle(title);
        task.setDetail(detail);
        task.setDeadline(deadline);
        task.setPriority(priority);

        TaskDAO dao = new TaskDAO();

        // 新規 or 更新
        if (idStr == null || idStr.isEmpty()) {
            dao.insert(task);
        } else {
            task.setId(Integer.parseInt(idStr));
            dao.update(task);
        }

        response.sendRedirect("taskList");
    }
}