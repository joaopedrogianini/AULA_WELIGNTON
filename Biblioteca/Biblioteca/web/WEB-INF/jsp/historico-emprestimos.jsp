<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <title>Livros</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h1>Histórico de Empréstimos</h1>

<table border="1">

    <tr>
        <th>ID</th>
        <th>Livro</th>
        <th>Data Empréstimo</th>
        <th>Data Prevista</th>
        <th>Data Devolução</th>
        <th>Status</th>
        <th>Multa</th>
    </tr>

<c:forEach var="emprestimo" items="${historicoEmprestimos}">
    <tr>

        <td>${emprestimo.id_emprestimos}</td>
        <td>${emprestimo.id_livros}</td>
        <td>${emprestimo.data_emprestimos}</td>
        <td>${emprestimo.dataprevista_devolucao}</td>
        <td>${emprestimo.datareal_devolucao}</td>
        <td>${emprestimo.status_emprestimos}</td>
        <td>R$ ${emprestimo.multa}</td>

    </tr>
</c:forEach>

</table>

<br><br>

<a href="${pageContext.request.contextPath}/home.jsp">
    Voltar
</a>

</body>
</html>