    package controller;

    import model.Livros;
    import dao.LivrosDAO;
    import dao.Status_livrosDAO;

    import java.io.IOException;
    import java.util.List;
    import jakarta.servlet.RequestDispatcher;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    @WebServlet("/LivroServlet")
    public class LivrosController extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private LivrosDAO livroDAO;

        public void init() {
            livroDAO = new Status_livrosDAO();
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String action = request.getParameter("action");

            if (action == null || action.isEmpty()) {
                action = "listar";
            }

            try {
                switch (action) {

                    case "novo":
                        mostrarFormularioNovo(request, response);
                        break;

                    case "inserir":
                        inserirLivro(request, response);
                        break;

                    case "deletar":
                        deletarLivro(request, response);
                        break;

                    case "editar":
                        mostrarFormularioEditar(request, response);
                        break;

                    case "atualizar":
                        atualizarLivro(request, response);
                        break;

                    case "disponiveis":
                        listarLivrosDisponiveis(request, response);
                        break;

                    case "buscar":
                        buscarLivros(request, response);
                        break;

                    case "listar":
                    default:
                        listarLivros(request, response);
                        break;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ServletException(ex);
            }
        }

        private void listarLivros(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            List<Livros> listaLivros = livroDAO.listarTodosLivros();

            request.setAttribute("listaLivros", listaLivros);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/livros.jsp");

            dispatcher.forward(request, response);
        }

        private void listarLivrosDisponiveis(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            List<Livros> listaLivros = livroDAO.buscarLivrosDisponiveis();

            request.setAttribute("listaLivros", listaLivros);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/livros.jsp");

            dispatcher.forward(request, response);
        }

        private void buscarLivros(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String nome = request.getParameter("nome");

            List<Livros> listaLivros = livroDAO.buscarLivrosPorNome(nome);

            request.setAttribute("listaLivros", listaLivros);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/livros.jsp");

            dispatcher.forward(request, response);
        }

        private void mostrarFormularioNovo(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/jsp/livro-form.jsp");

            dispatcher.forward(request, response);
        }

        private void inserirLivro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String autor = request.getParameter("autor");
        String quantidadeStr = request.getParameter("quantidade");

        if (nome == null || nome.trim().isEmpty()) {
            request.setAttribute("erro", "Nome do livro é obrigatório.");
            request.getRequestDispatcher("WEB-INF/jsp/livro-form.jsp")
                    .forward(request, response);
            return;
        }

        if (autor == null || autor.trim().isEmpty()) {
            request.setAttribute("erro", "Autor é obrigatório.");
            request.getRequestDispatcher("WEB-INF/jsp/livro-form.jsp")
                    .forward(request, response);
            return;
        }

        int quantidade;

        try {
            quantidade = Integer.parseInt(quantidadeStr);

            if (quantidade < 0) {
                request.setAttribute("erro", "Quantidade não pode ser negativa.");
                request.getRequestDispatcher("WEB-INF/jsp/livro-form.jsp")
                        .forward(request, response);
                return;
            }

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Quantidade inválida.");
            request.getRequestDispatcher("WEB-INF/jsp/livro-form.jsp")
                    .forward(request, response);
            return;
        }

        Livros novoLivro = new Livros();

        novoLivro.setNome_livros(nome);
        novoLivro.setAutor_livros(autor);
        novoLivro.setQuantidade_livros(quantidade);
        novoLivro.setFaixaetaria_livros(request.getParameter("faixaEtaria"));
        novoLivro.setCategoria_livros(request.getParameter("categoria"));
        novoLivro.setAnopublicacao_livros(Integer.parseInt(request.getParameter("anoPublicacao")));

        livroDAO.adicionarLivro(novoLivro);

        response.sendRedirect("LivroServlet?action=listar");
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Livros livroExistente = livroDAO.buscarLivroPorId(id);

        request.setAttribute("livro", livroExistente);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/livro-form.jsp");

        dispatcher.forward(request, response);
    }

    private void atualizarLivro(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Livros livro = new Livros(
                id,
                request.getParameter("nome"),
                request.getParameter("autor"),
                Integer.parseInt(request.getParameter("quantidade")),
                request.getParameter("faixaEtaria"),
                request.getParameter("categoria"),
                Integer.parseInt(request.getParameter("anoPublicacao"))
        );

        livroDAO.atualizarLivro(livro);

        response.sendRedirect("LivroServlet?action=listar");
    }

    private void deletarLivro(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        livroDAO.deletarLivro(id);

        response.sendRedirect("LivroServlet?action=listar");
    }
    }