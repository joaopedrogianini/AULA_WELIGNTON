package controller;

import model.Emprestimos;
import model.Livros;
import model.Usuarios;
import dao.EmprestimosDAO;
import dao.Status_emprestimosDAO;
import dao.LivrosDAO;
import dao.Status_livrosDAO;
import dao.UsuariosDAO;
import dao.Status_usuariosDAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/EmprestimoServlet")
public class EmprestimosController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmprestimosDAO emprestimoDAO;
    private LivrosDAO livroDAO;
    private UsuariosDAO usuarioDAO;

    public void init() {
        emprestimoDAO = new Status_emprestimosDAO();
        livroDAO = new Status_livrosDAO();
        usuarioDAO = new Status_usuariosDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "novo":
                    mostrarFormularioNovo(request, response);
                    break;
                case "inserir":
                    inserirEmprestimo(request, response);
                    break;
                case "deletar":
                    deletarEmprestimo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "atualizar":
                    atualizarEmprestimo(request, response);
                    break;
                case "devolver":
                    mostrarFormularioDevolucao(request, response);
                    break;
                case "registrarDevolucao":
                    registrarDevolucao(request, response);
                    break;
                case "historico":
                    listarHistoricoEmprestimos(request, response);
                    break;
                case "listar":
                default:
                    listarEmprestimos(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listarEmprestimos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Emprestimos> listaEmprestimos = emprestimoDAO.listarTodosEmprestimos();
        request.setAttribute("listaEmprestimos", listaEmprestimos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/emprestimos.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuarios> listaUsuarios = usuarioDAO.listarTodosUsuarios();
        List<Livros> listaLivros = livroDAO.buscarLivrosDisponiveis();
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.setAttribute("listaLivros", listaLivros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emprestimo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void inserirEmprestimo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        int idLivro = Integer.parseInt(request.getParameter("idLivro"));
        String dataEmprestimoStr = request.getParameter("dataEmprestimo");
        String dataPrevistaDevolucaoStr = request.getParameter("dataPrevistaDevolucao");

        Date dataEmprestimo = new SimpleDateFormat("yyyy-MM-dd").parse(dataEmprestimoStr);
        Date dataPrevistaDevolucao = new SimpleDateFormat("yyyy-MM-dd").parse(dataPrevistaDevolucaoStr);

        Livros livro = livroDAO.buscarLivroPorId(idLivro);
        if (livro != null && livro.getQuantidade_livros() > 0) {
            
            Usuarios usuario = usuarioDAO.buscarUsuarioPorId(idUsuario);
            if (usuario != null && "18+".equals(livro.getFaixaetaria_livros())) {
                Date hoje = new Date();
                long diff = hoje.getTime() - usuario.getDatanascimento_usuarios().getTime();
                long anos = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) / 365;
                if (anos < 18) {
                    request.setAttribute("errorMessage", "Usuário menor de 18 anos não pode emprestar livros 18+.");
                    mostrarFormularioNovo(request, response);
                    return;
                }
            }

            Emprestimos novoEmprestimo = new Emprestimos();
            novoEmprestimo.setData_emprestimos(dataEmprestimo);
            novoEmprestimo.setDataprevista_devolucao(dataPrevistaDevolucao);
            novoEmprestimo.setStatus_emprestimos("Emprestado");
            novoEmprestimo.setId_usuarios(idUsuario);
            novoEmprestimo.setId_livros(idLivro);

            emprestimoDAO.adicionarEmprestimo(novoEmprestimo);
            livroDAO.atualizarQuantidadeLivro(idLivro, livro.getQuantidade_livros() - 1); 
            response.sendRedirect("EmprestimoServlet?action=listar");
        } else {
            request.setAttribute("errorMessage", "Livro não disponível para empréstimo.");
            mostrarFormularioNovo(request, response);
        }
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Emprestimos emprestimoExistente = emprestimoDAO.buscarEmprestimoPorId(id);
        List<Usuarios> listaUsuarios = usuarioDAO.listarTodosUsuarios();
        List<Livros> listaLivros = livroDAO.listarTodosLivros(); 
        request.setAttribute("emprestimo", emprestimoExistente);
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.setAttribute("listaLivros", listaLivros);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/emprestimo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizarEmprestimo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        int idLivro = Integer.parseInt(request.getParameter("idLivro"));
        String dataEmprestimoStr = request.getParameter("dataEmprestimo");
        String dataPrevistaDevolucaoStr = request.getParameter("dataPrevistaDevolucao");
        String status = request.getParameter("status");

        Date dataEmprestimo = new SimpleDateFormat("yyyy-MM-dd").parse(dataEmprestimoStr);
        Date dataPrevistaDevolucao = new SimpleDateFormat("yyyy-MM-dd").parse(dataPrevistaDevolucaoStr);

        Emprestimos emprestimo = new Emprestimos();
        emprestimo.setId_emprestimos(id);
        emprestimo.setData_emprestimos(dataEmprestimo);
        emprestimo.setDataprevista_devolucao(dataPrevistaDevolucao);
        emprestimo.setStatus_emprestimos(status);
        emprestimo.setId_usuarios(idUsuario);
        emprestimo.setId_livros(idLivro);

        emprestimoDAO.atualizarEmprestimo(emprestimo);
        response.sendRedirect("EmprestimoServlet?action=listar");
    }

    private void deletarEmprestimo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        emprestimoDAO.deletarEmprestimo(id);
        response.sendRedirect("EmprestimoServlet?action=listar");
    }

    private void mostrarFormularioDevolucao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Emprestimos emprestimo = emprestimoDAO.buscarEmprestimoPorId(id);
        request.setAttribute("emprestimo", emprestimo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/devolucao-form.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarDevolucao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        int idEmprestimo = Integer.parseInt(request.getParameter("idEmprestimo"));
        String dataRealDevolucaoStr = request.getParameter("dataRealDevolucao");
        Date dataRealDevolucao = new SimpleDateFormat("yyyy-MM-dd").parse(dataRealDevolucaoStr);

        Emprestimos emprestimo = emprestimoDAO.buscarEmprestimoPorId(idEmprestimo);
        if (emprestimo != null) {
      emprestimo.setDatareal_devolucao(dataRealDevolucao);
emprestimo.setStatus_emprestimos("Devolvido");

long diffInMillies =
        dataRealDevolucao.getTime()
        - emprestimo.getDataprevista_devolucao().getTime();

long diffInDays =
        TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

if (diffInDays > 0) {
    emprestimo.setMulta(diffInDays * 2.00);
    emprestimo.setStatus_emprestimos("Atrasado");
} else {
    emprestimo.setMulta(0.00);
}

            emprestimoDAO.atualizarEmprestimo(emprestimo);

         
            Livros livro = livroDAO.buscarLivroPorId(emprestimo.getId_livros());
            if (livro != null) {
                livroDAO.atualizarQuantidadeLivro(livro.getId_livros(), livro.getQuantidade_livros() + 1);
            }
        }
        response.sendRedirect("EmprestimoServlet?action=listar");
    }

   private void listarHistoricoEmprestimos(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession();

    Usuarios usuarioLogado =
            (Usuarios) session.getAttribute("usuarioLogado");

    System.out.println("Usuário logado: "
            + usuarioLogado.getId_usuarios());

    List<Emprestimos> historico =
            emprestimoDAO.buscarEmprestimosPorUsuario(
                    usuarioLogado.getId_usuarios());

    System.out.println("Quantidade: "
            + historico.size());

    request.setAttribute("historicoEmprestimos", historico);

    RequestDispatcher dispatcher =
            request.getRequestDispatcher(
                    "WEB-INF/jsp/historico-emprestimos.jsp");

    dispatcher.forward(request, response);

        }
    }
