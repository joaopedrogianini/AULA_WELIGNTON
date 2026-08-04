<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Novo Empréstimo</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h1>Novo Empréstimo</h1>

<form action="${pageContext.request.contextPath}/EmprestimoServlet?action=inserir"
      method="post">

    Usuário:

    <select name="idUsuario">
        <c:forEach var="usuario" items="${listaUsuarios}">
            <option value="${usuario.id_usuarios}">
                ${usuario.nome_usuarios}
            </option>
        </c:forEach>
    </select>

    <br><br>

    Livro:

    <select name="idLivro">
        <c:forEach var="livro" items="${listaLivros}">
            <option value="${livro.id_livros}">
                ${livro.nome_livros}
            </option>
        </c:forEach>
    </select>

    <br><br>

    Data Empréstimo:

    <input type="date" name="dataEmprestimo">

    <br><br>

    Data Prevista Devolução:

    <input type="date" name="dataPrevistaDevolucao">

    <br><br>

    <button type="submit">
        Salvar
    </button>

</form>

</body>
</html>