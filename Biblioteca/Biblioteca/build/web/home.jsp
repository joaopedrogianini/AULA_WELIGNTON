<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sistema de Biblioteca - Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<div class="container">

    <c:if test="${sessionScope.usuarioLogado == null}">
        <c:redirect url="index.html"/>
    </c:if>

    <h2>Bem-vindo, ${sessionScope.usuarioLogado.nome_usuarios}!</h2>

    <p>Este é o painel principal do sistema de biblioteca.</p>

    <nav>
        <ul>

            <li>
                <a href="UsuariosController?action=listar">
                    Gerenciar Usuários
                </a>
            </li>

            <li>
                <a href="LivroServlet?action=listar">
                    Gerenciar Livros
                </a>
            </li>

            <li>
                <a href="EmprestimoServlet?action=listar">
                    Gerenciar Empréstimos
                </a>
            </li>

            <li>
                <a href="LivroServlet?action=disponiveis">
                    Livros Disponíveis
                </a>
            </li>

            <li>
                <a href="EmprestimoServlet?action=historico">
                    Histórico de Empréstimos
                </a>
            </li>

            <li>
                <a href="LogoutServlet">
                    Sair
                </a>
            </li>

        </ul>
    </nav>

</div>

</body>
</html>