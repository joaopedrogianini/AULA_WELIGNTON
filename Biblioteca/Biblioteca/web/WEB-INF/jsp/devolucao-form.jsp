<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrar Devolução</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h1>Registrar Devolução</h1>

<form action="${pageContext.request.contextPath}/EmprestimoServlet?action=registrarDevolucao"
      method="post">

    <input type="hidden"
           name="idEmprestimo"
           value="${emprestimo.id_emprestimos}">

    Data da Devolução:

    <input type="date"
           name="dataRealDevolucao">

    <br><br>

    <button type="submit">
        Registrar Devolução
    </button>

</form>

<br><br>

<a href="${pageContext.request.contextPath}/EmprestimoServlet?action=listar">
    Voltar
</a>

</body>
</html>