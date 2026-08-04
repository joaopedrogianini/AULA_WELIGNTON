package controller;

import model.Usuarios;
import dao.UsuariosDAO;
import dao.Status_usuariosDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UsuariosDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new Status_usuariosDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuarios usuario = usuarioDAO.buscarUsuarioPorEmailESenha(email, senha);

        if (usuario != null && "ativo".equalsIgnoreCase(usuario.getStatus_usuarios())) {

            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);

            response.sendRedirect(request.getContextPath() + "/home.jsp");

        } else {

            request.setAttribute("errorMessage",
                    "E-mail ou senha inválidos ou usuário inativo.");

            request.getRequestDispatcher("/index.html")
                   .forward(request, response);
        }
    }
}