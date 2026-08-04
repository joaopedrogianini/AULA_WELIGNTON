<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Livros</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h1>Gerenciamento de Livros</h1>

<a href="${pageContext.request.contextPath}/LivroServlet?action=novo">
    <button>Novo Livro</button>
</a>

<br><br>

<form action="${pageContext.request.contextPath}/LivroServlet" method="get">

    <input type="hidden" name="action" value="buscar">

    <input type="text"
           name="nome"
           placeholder="Digite o nome do livro">

    <button type="submit">
        Buscar
    </button>

</form>

<br><br>

<table border="1">

    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Autor</th>
        <th>Quantidade</th>
        <th>Categoria</th>
        <th>Faixa Etária</th>
        <th>Ano</th>
        <th>Ações</th>
    </tr>

    <c:forEach var="livro" items="${listaLivros}">
        <tr>

         <td>${livro.id_livros}</td>
<td>${livro.nome_livros}</td>
<td>${livro.autor_livros}</td>
<td>${livro.quantidade_livros}</td>
<td>${livro.categoria_livros}</td>
<td>${livro.faixaetaria_livros}</td>
<td>${livro.anopublicacao_livros}</td>

            <td>

               <a href="LivroServlet?action=editar&id=${livro.id_livros}">
                    Editar
                </a>

                |

             <a href="LivroServlet?action=deletar&id=${livro.id_livros}"
   onclick="return confirm('Deseja excluir este livro?')">
    Excluir
</a>

            </td>

        </tr>
    </c:forEach>

</table>

<br><br>

<a href="${pageContext.request.contextPath}/home.jsp">
    Voltar
</a>

</body>
</html>