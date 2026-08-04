package dao;

import model.Livros;
import java.util.List;

public interface LivrosDAO {
    void adicionarLivro(Livros livro);
    void atualizarLivro(Livros livro);
    void deletarLivro(int id);
    Livros buscarLivroPorId(int id);
    List<Livros> listarTodosLivros();
    List<Livros> buscarLivrosDisponiveis();
    void atualizarQuantidadeLivro(int Id_livros, int Quantidade_livros);
    List<Livros> buscarLivrosPorNome(String nome);
}