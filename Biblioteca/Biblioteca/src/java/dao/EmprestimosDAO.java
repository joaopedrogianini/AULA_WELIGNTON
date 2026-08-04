package dao;

import model.Emprestimos;
import java.util.List;

public interface EmprestimosDAO {
    void adicionarEmprestimo(Emprestimos emprestimo);
    void atualizarEmprestimo(Emprestimos emprestimo);
    void deletarEmprestimo(int id);
    Emprestimos buscarEmprestimoPorId(int id);
    List<Emprestimos> listarTodosEmprestimos();
    List<Emprestimos> buscarEmprestimosPorUsuario(int Id_usuarios);
}
