<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Task" %>

<%
List<Task> taskList = (List<Task>) request.getAttribute("taskList");
Task task = (Task) request.getAttribute("task");

boolean isEdit = (task != null);
String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
<title>タスク管理</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body class="bg-light">

<div class="container mt-5">

<h2 class="mb-4">タスク管理</h2>

<a href="logout" class="btn btn-secondary float-end">ログアウト</a>

<!-- エラー表示 -->
<% if (error != null) { %>
    <div class="alert alert-danger"><%= error %></div>
<% } %>

<h4 class="mt-4">タスク一覧</h4>

<table class="table table-striped shadow">
<thead class="table-dark">
<tr>
<th>タイトル</th>
<th>締切</th>
<th>優先度</th>
<th>操作</th>
</tr>
</thead>

<tbody>

<% if (taskList != null) {
    for (Task t : taskList) { %>

<tr>
<td><%= t.getTitle() %></td>
<td><%= t.getDeadline() %></td>
<td><%= t.getPriority() %></td>

<td>
<a href="taskForm?id=<%=t.getId()%>" class="btn btn-sm btn-warning">編集</a>

<form action="taskDelete" method="post" style="display:inline;">
<input type="hidden" name="id" value="<%=t.getId()%>">
<button class="btn btn-sm btn-danger">削除</button>
</form>
</td>

</tr>

<% } } %>

</tbody>
</table>

<hr class="my-5">

<h4><%= isEdit ? "タスク編集" : "タスク追加" %></h4>

<form action="taskSave" method="post">

<% if(isEdit){ %>
<input type="hidden" name="id" value="<%=task.getId()%>">
<% } %>

<div class="mb-3">
<label class="form-label">タイトル</label>
<input type="text" name="title" class="form-control"
value="<%= isEdit ? task.getTitle() : "" %>" required>
</div>

<div class="mb-3">
<label class="form-label">詳細</label>
<textarea name="detail" class="form-control" required><%= isEdit ? task.getDetail() : "" %></textarea>
</div>

<div class="mb-3">
<label class="form-label">締切</label>
<input type="date" name="deadline" class="form-control"
value="<%= isEdit && task.getDeadline()!=null ? task.getDeadline() : "" %>" required>
</div>

<div class="mb-3">
<label class="form-label">優先度</label>
<select name="priority" class="form-select" required>

<option value="1" <%= isEdit && task.getPriority()==1 ? "selected" : "" %>>高</option>
<option value="2" <%= isEdit && task.getPriority()==2 ? "selected" : "" %>>中</option>
<option value="3" <%= isEdit && task.getPriority()==3 ? "selected" : "" %>>低</option>

</select>
</div>

<button class="btn btn-success">保存</button>
<a href="taskList" class="btn btn-secondary">リセット</a>

</form>

</div>

</body>
</html>