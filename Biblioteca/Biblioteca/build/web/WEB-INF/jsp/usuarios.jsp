<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Gerenciamento de Usuários</title>

```
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">
```

</head>
<body>

```
<h1>Gerenciamento de Usuários</h1>

<a href="${pageContext.request.contextPath}/UsuariosController?action=novo">
    <button>Novo Usuário</button>
</a>

<br><br>

<table border="1">

    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Status</th>
        <th>Ações</th>
    </tr>

    <c:forEach var="usuario" items="${listaUsuarios}">
        <tr>

            <td>${usuario.id_usuarios}</td>
            <td>${usuario.nome_usuarios}</td>
            <td>${usuario.email_usuarios}</td>
            <td>${usuario.status_usuarios}</td>

            <td>

                <a href="${pageContext.request.contextPath}/UsuariosController?action=editar&id=${usuario.id_usuarios}">
                    Editar
                </a>

                |

                <a href="${pageContext.request.contextPath}/UsuariosController?action=deletar&id=${usuario.id_usuarios}"
                   onclick="return confirm('Deseja excluir este usuário?')">
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
```

</body>
</html>
