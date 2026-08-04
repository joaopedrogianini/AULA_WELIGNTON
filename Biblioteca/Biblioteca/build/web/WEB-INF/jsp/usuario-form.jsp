<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">
    <title>Usuário</title>

```
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">
```

</head>
<body>

<h1>${usuario != null ? "Editar Usuário" : "Cadastrar Usuário"}</h1>

<% if(request.getAttribute("erro") != null){ %> <p style="color:red;font-weight:bold;">
<%= request.getAttribute("erro") %> </p>
<% } %>

<form action="${pageContext.request.contextPath}/UsuariosController?action=${usuario != null ? 'atualizar' : 'inserir'}"
      method="post">

```
<input type="hidden"
       name="id"
       value="${usuario.id_usuarios}">

<label>Nome:</label>
<input type="text"
       name="nome"
       value="${usuario.nome_usuarios}">
<br><br>

<label>Data de Nascimento:</label>
<input type="date"
       name="dataNascimento"
       value="${usuario.datanascimento_usuarios}">
<br><br>

<label>Email:</label>
<input type="email"
       name="email"
       value="${usuario.email_usuarios}">
<br><br>

<label>Senha:</label>
<input type="password"
       name="senha"
       value="${usuario.senha_usuarios}">
<br><br>

<label>Status:</label>
<select name="status">
    <option value="ATIVO">ATIVO</option>
    <option value="INATIVO">INATIVO</option>
</select>

<br><br>

<button type="submit">Salvar</button>
```

</form>

</body>
</html>
