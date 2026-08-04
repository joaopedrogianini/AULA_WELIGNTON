package dao;

import model.Emprestimos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Status_emprestimosDAO implements EmprestimosDAO {

    @Override
    public void adicionarEmprestimo(Emprestimos emprestimo) {
        String sql = "INSERT INTO emprestimos (Data_emprestimos, Dataprevista_devolucao, Datareal_devolucao, Multa, status_emprestimos, Id_usuarios, Id_livros) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(emprestimo.getData_emprestimos().getTime()));
            stmt.setDate(2, new java.sql.Date(emprestimo.getDataprevista_devolucao().getTime()));
            stmt.setDate(3, (emprestimo.getDatareal_devolucao() != null) ? new java.sql.Date(emprestimo.getDatareal_devolucao().getTime()) : null);
            stmt.setDouble(4, emprestimo.getMulta());
            stmt.setString(5, emprestimo.getStatus_emprestimos());
            stmt.setInt(6, emprestimo.getId_usuarios());
            stmt.setInt(7, emprestimo.getId_livros());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar empréstimo", e);
        }
    }

    @Override
    public void atualizarEmprestimo(Emprestimos emprestimo) {
        String sql = "UPDATE emprestimos SET Data_emprestimos = ?, Dataprevista_devolucao = ?, Datareal_devolucao = ?, Multa = ?, status_emprestimos = ?, Id_usuarios = ?, Id_livros = ? WHERE Id_emprestimos = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(emprestimo.getData_emprestimos().getTime()));
            stmt.setDate(2, new java.sql.Date(emprestimo.getDataprevista_devolucao().getTime()));
            stmt.setDate(3, (emprestimo.getDatareal_devolucao() != null) ? new java.sql.Date(emprestimo.getDatareal_devolucao().getTime()) : null);
            stmt.setDouble(4, emprestimo.getMulta());
            stmt.setString(5, emprestimo.getStatus_emprestimos());
            stmt.setInt(6, emprestimo.getId_usuarios());
            stmt.setInt(7, emprestimo.getId_livros());
            stmt.setInt(8, emprestimo.getId_emprestimos());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar empréstimo", e);
        }
    }

    @Override
    public void deletarEmprestimo(int id) {
        String sql = "DELETE FROM emprestimos WHERE Id_emprestimos = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar empréstimo", e);
        }
    }

    @Override
    public Emprestimos buscarEmprestimoPorId(int id) {
        String sql = "SELECT * FROM emprestimos WHERE Id_emprestimos = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extrairEmprestimo(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empréstimo por ID", e);
        }
        return null;
    }

    @Override
    public List<Emprestimos> listarTodosEmprestimos() {
        List<Emprestimos> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                emprestimos.add(extrairEmprestimo(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os empréstimos", e);
        }
        return emprestimos;
    }

    @Override
    public List<Emprestimos> buscarEmprestimosPorUsuario(int Id_usuarios) {
        List<Emprestimos> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos WHERE Id_usuarios = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Id_usuarios);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    emprestimos.add(extrairEmprestimo(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar empréstimos por usuário", e);
        }
        return emprestimos;
    }

   private Emprestimos extrairEmprestimo(ResultSet rs) throws SQLException {

    Emprestimos emprestimo = new Emprestimos();

    emprestimo.setId_emprestimos(rs.getInt("Id_emprestimos"));
    emprestimo.setData_emprestimos(rs.getDate("Data_emprestimos"));
    emprestimo.setDataprevista_devolucao(rs.getDate("Dataprevista_devolucao"));

    if (rs.getDate("Datareal_devolucao") != null) {
        emprestimo.setDatareal_devolucao(
                rs.getDate("Datareal_devolucao"));
    }

    emprestimo.setMulta(rs.getDouble("Multa"));
    emprestimo.setStatus_emprestimos(
            rs.getString("Status_emprestimos"));

    emprestimo.setId_usuarios(
            rs.getInt("Id_usuarios"));

    emprestimo.setId_livros(
            rs.getInt("Id_livros"));

    return emprestimo;
}
}
