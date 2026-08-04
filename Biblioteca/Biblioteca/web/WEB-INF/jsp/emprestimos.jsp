<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <title>Livros</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h1>Gerenciamento de Empréstimos</h1>

<a href="${pageContext.request.contextPath}/EmprestimoServlet?action=novo">
    <button>Novo Empréstimo</button>
</a>

<br><br>

<table border="1">

    <tr>
        <th>ID</th>
        <th>Usuário</th>
        <th>Livro</th>
        <th>Data Empréstimo</th>
        <th>Data Prevista</th>
        <th>Data Devolução</th>
        <th>Status</th>
        <th>Multa</th>
        <th>Ações</th>
    </tr>

    <c:forEach var="emprestimo" items="${listaEmprestimos}">
        <tr>

            <td>${emprestimo.id_emprestimos}</td>
            <td>${emprestimo.id_usuarios}</td>
            <td>${emprestimo.id_livros}</td>
            <td>${emprestimo.data_emprestimos}</td>
           <td>${emprestimo.dataprevista_devolucao}</td>
            <td>${emprestimo.datareal_devolucao}</td>
            <td>${emprestimo.status_emprestimos}</td>
            <td>R$ ${emprestimo.multa}</td>

            <td>

               <a href="EmprestimoServlet?action=editar&id=${emprestimo.id_emprestimos}">
                    Editar
                </a>

                |

              <a href="EmprestimoServlet?action=devolver&id=${emprestimo.id_emprestimos}">
                    Devolver
                </a>

                |

                <a href="EmprestimoServlet?action=deletar&id=${emprestimo.id_emprestimos}"  
                   onclick="return confirm('Deseja excluir este empréstimo?')">
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