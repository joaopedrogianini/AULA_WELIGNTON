package controller;

import model.Usuarios;
import dao.UsuariosDAO;
import dao.Status_usuariosDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UsuariosController")
public class UsuariosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuariosDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new Status_usuariosDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "novo":
                    mostrarFormularioNovo(request, response);
                    break;
                case "inserir":
                    inserirUsuario(request, response);
                    break;
                case "deletar":
                    deletarUsuario(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "atualizar":
                    atualizarUsuario(request, response);
                    break;
                case "listar":
                default:
                    listarUsuarios(request, response);
                    break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Usuarios> listaUsuarios = usuarioDAO.listarTodosUsuarios();
        request.setAttribute("listaUsuarios", listaUsuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/usuarios.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/usuario-form.jsp");
        dispatcher.forward(request, response);
    }

private void inserirUsuario(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ParseException, ServletException {

    String nome = request.getParameter("nome");
    String dataNascimentoStr = request.getParameter("dataNascimento");
    String email = request.getParameter("email");
    String senha = request.getParameter("senha");
    String status = request.getParameter("status");

    if (nome == null || nome.trim().isEmpty()) {
        request.setAttribute("erro", "Nome é obrigatório.");
        request.getRequestDispatcher("WEB-INF/jsp/usuario-form.jsp")
                .forward(request, response);
        return;
    }

    if (email == null || email.trim().isEmpty()) {
        request.setAttribute("erro", "Email é obrigatório.");
        request.getRequestDispatcher("WEB-INF/jsp/usuario-form.jsp")
                .forward(request, response);
        return;
    }

    if (senha == null || senha.trim().isEmpty()) {
        request.setAttribute("erro", "Senha é obrigatória.");
        request.getRequestDispatcher("WEB-INF/jsp/usuario-form.jsp")
                .forward(request, response);
        return;
    }

    if (dataNascimentoStr == null || dataNascimentoStr.trim().isEmpty()) {
        request.setAttribute("erro", "Data de nascimento é obrigatória.");
        request.getRequestDispatcher("WEB-INF/jsp/usuario-form.jsp")
                .forward(request, response);
        return;
    }

    Date dataNascimento =
            new SimpleDateFormat("yyyy-MM-dd").parse(dataNascimentoStr);

    Usuarios novoUsuario = new Usuarios();

    novoUsuario.setNome_usuarios(nome);
    novoUsuario.setDatanascimento_usuarios(dataNascimento);
    novoUsuario.setEmail_usuarios(email);
    novoUsuario.setSenha_usuarios(senha);
    novoUsuario.setStatus_usuarios(status);

    usuarioDAO.adicionarUsuario(novoUsuario);

       response.sendRedirect("UsuariosController?action=listar");
}

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuarios usuarioExistente = usuarioDAO.buscarUsuarioPorId(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/usuario-form.jsp");
        request.setAttribute("usuario", usuarioExistente);
        dispatcher.forward(request, response);
    }

    private void atualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String dataNascimentoStr = request.getParameter("dataNascimento");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");

        Date dataNascimento = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascimentoStr);

        Usuarios usuario = new Usuarios(id, nome, dataNascimento, email, senha, status);
        usuarioDAO.atualizarUsuario(usuario);
        response.sendRedirect("UsuariosController?action=listar");
    }

    private void deletarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.deletarUsuario(id);
        response.sendRedirect("UsuariosController?action=listar");
    }
}