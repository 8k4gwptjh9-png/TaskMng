<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>ログイン</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="bg-light">

<div class="container mt-5">

<div class="card mx-auto shadow" style="max-width:400px;">
<div class="card-body">

<h3 class="text-center mb-4">ログイン</h3>

<form action="login" method="post">

<div class="mb-3">
<label class="form-label">ユーザー名</label>
<input type="text" name="username" class="form-control" required>
</div>

<div class="mb-3">
<label class="form-label">パスワード</label>
<input type="password" name="password" class="form-control" required>
</div>

<button type="submit" class="btn btn-primary w-100">ログイン</button>

</form>

<div class="text-center mt-3">
<a href="register.jsp">新規登録</a>
</div>

</div>
</div>

</div>

</body>
</html>