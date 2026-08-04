package dao;

import model.Usuarios;
import java.util.List;

public interface UsuariosDAO {
    void adicionarUsuario(Usuarios usuarios);
    void atualizarUsuario(Usuarios usuarios);
    void deletarUsuario(int id);
    Usuarios buscarUsuarioPorId(int id);
    Usuarios buscarUsuarioPorEmailESenha(String Email_usuarios, String Senha_usuarios);
    List<Usuarios> listarTodosUsuarios();
}