    <%@page contentType="text/html" pageEncoding="UTF-8"%>

    <!DOCTYPE html>
    <html>

    <head>
        <title>Livros</title>

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>

    <h1>${livro != null ? "Editar Livro" : "Cadastrar Livro"}</h1>

    <% if(request.getAttribute("erro") != null){ %>
        <p style="color:red;font-weight:bold;">
            <%= request.getAttribute("erro") %>
        </p>
    <% } %>

    <form action="${pageContext.request.contextPath}/LivroServlet?action=${livro != null ? 'atualizar' : 'inserir'}"
          method="post">

        <input type="hidden" name="id" value="${livro.idLivro}">

        Nome:
        <input type="text" name="nome" value="${livro.nome}">
        <br><br>

        Autor:
        <input type="text" name="autor" value="${livro.autor}">
        <br><br>

        Quantidade:
        <input type="number" name="quantidade" value="${livro.quantidade}">
        <br><br>

        Categoria:
        <input type="text" name="categoria" value="${livro.categoria}">
        <br><br>

        Faixa Etária:
        <input type="text" name="faixaEtaria" value="${livro.faixaEtaria}">
        <br><br>

        Ano:
        <input type="number" name="anoPublicacao" value="${livro.anoPublicacao}">
        <br><br>

        <button type="submit">Salvar</button>

    </form>

    <br><br>

    <a href="${pageContext.request.contextPath}/LivroServlet?action=listar">
        Voltar
    </a>

    </body>
    </html>