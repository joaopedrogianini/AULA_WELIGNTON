package dao;

import model.Livros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Status_livrosDAO implements LivrosDAO {

    @Override
    public void adicionarLivro(Livros livro) {

        String sql = "INSERT INTO livros (Nome_livros, Autor_livros, Quantidade_livros, Faixaetaria_livros, Categoria_livros, Anopublicacao_livros) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getNome_livros());
            stmt.setString(2, livro.getAutor_livros());
            stmt.setInt(3, livro.getQuantidade_livros());
            stmt.setInt(4, Integer.parseInt(livro.getFaixaetaria_livros()));
            stmt.setString(5, livro.getCategoria_livros());
            stmt.setInt(6, livro.getAnopublicacao_livros());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar livro", e);
        }
    }

    @Override
    public void atualizarLivro(Livros livro) {

        String sql = "UPDATE livros SET Nome_livros = ?, Autor_livros = ?, Quantidade_livros = ?, Faixaetaria_livros = ?, Categoria_livros = ?, Anopublicacao_livros = ? WHERE Id_livros = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getNome_livros());
            stmt.setString(2, livro.getAutor_livros());
            stmt.setInt(3, livro.getQuantidade_livros());
            stmt.setInt(4, Integer.parseInt(livro.getFaixaetaria_livros()));
            stmt.setString(5, livro.getCategoria_livros());
            stmt.setInt(6, livro.getAnopublicacao_livros());
            stmt.setInt(7, livro.getId_livros());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar livro", e);
        }
    }

    @Override
    public void deletarLivro(int id) {

        String sql = "DELETE FROM livros WHERE Id_livros = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar livro", e);
        }
    }

    @Override
    public Livros buscarLivroPorId(int id) {

        String sql = "SELECT * FROM livros WHERE Id_livros = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return extrairLivro(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro por ID", e);
        }

        return null;
    }

    @Override
    public List<Livros> listarTodosLivros() {

        List<Livros> livros = new ArrayList<>();

        String sql = "SELECT * FROM livros";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                livros.add(extrairLivro(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os livros", e);
        }

        return livros;
    }

    @Override
    public List<Livros> buscarLivrosDisponiveis() {

        List<Livros> livros = new ArrayList<>();

        String sql = "SELECT * FROM livros WHERE Quantidade_livros > 0";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                livros.add(extrairLivro(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livros disponíveis", e);
        }

        return livros;
    }

    @Override
    public void atualizarQuantidadeLivro(int Id_livros, int Quantidade_livros) {

        String sql = "UPDATE livros SET Quantidade_livros = ? WHERE Id_livros = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Quantidade_livros);
            stmt.setInt(2, Id_livros);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar quantidade do livro", e);
        }
    }
private Livros extrairLivro(ResultSet rs) throws SQLException {

    Livros livro = new Livros();

    livro.setId_livros(rs.getInt("Id_livros"));
    livro.setNome_livros(rs.getString("Nome_livros"));
    livro.setAutor_livros(rs.getString("Autor_livros"));
    livro.setQuantidade_livros(rs.getInt("Quantidade_livros"));
    livro.setFaixaetaria_livros(rs.getString("Faixaetaria_livros"));
    livro.setCategoria_livros(rs.getString("Categoria_livros"));
    livro.setAnopublicacao_livros(rs.getInt("Anopublicacao_livros"));

    return livro;
}
    @Override
public List<Livros> buscarLivrosPorNome(String Nome_livros) {

    List<Livros> livros = new ArrayList<>();

    String sql = "SELECT * FROM livros WHERE Nome_livros ILIKE ?";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + Nome_livros + "%");

        try (ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                livros.add(extrairLivro(rs));
            }
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar livros por nome", e);
    }

    return livros;
}
}