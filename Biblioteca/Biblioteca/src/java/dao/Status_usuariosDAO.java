    package dao;

    import model.Usuarios;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;

    public class Status_usuariosDAO implements UsuariosDAO {

        @Override
        public void adicionarUsuario(Usuarios usuario) {
            String sql = "INSERT INTO usuarios (Nome_usuarios, Datanascimento_usuarios, Email_usuarios, Senha_usuarios, Status_usuarios) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usuario.getNome_usuarios());
                stmt.setDate(2, new java.sql.Date(usuario.getDatanascimento_usuarios().getTime()));
                stmt.setString(3, usuario.getEmail_usuarios());
                stmt.setString(4, usuario.getSenha_usuarios());
                stmt.setString(5, usuario.getStatus_usuarios());

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao adicionar usuário: ", e);
            }
        }

        @Override
        public void atualizarUsuario(Usuarios usuario) {
            String sql = "UPDATE usuarios SET Nome_usuarios = ?, Datanascimento_usuarios = ?, Email_usuarios = ?, Senha_usuarios = ?, Status_usuarios = ? WHERE Id_usuarios = ?";
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usuario.getNome_usuarios());
                stmt.setDate(2, new java.sql.Date(usuario.getDatanascimento_usuarios().getTime()));
                stmt.setString(3, usuario.getEmail_usuarios());
                stmt.setString(4, usuario.getSenha_usuarios());
                stmt.setString(5, usuario.getStatus_usuarios());
                stmt.setInt(6, usuario.getId_usuarios()); 

                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao atualizar usuário: ", e);
            }
        }

        @Override
        public void deletarUsuario(int id) {
            String sql = "DELETE FROM usuarios WHERE Id_usuarios = ?";
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao deletar usuário: ", e);
            }
        }

        @Override
        public Usuarios buscarUsuarioPorId(int id) {
            String sql = "SELECT * FROM usuarios WHERE Id_usuarios = ?";
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return mapearUsuario(rs);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao buscar usuário por ID: ", e);
            }
            return null;
        }

        @Override
        public Usuarios buscarUsuarioPorEmailESenha(String Email_usuarios, String Senha_usuarios) {
            String sql = "SELECT * FROM usuarios WHERE Email_usuarios = ? AND Senha_usuarios = ?";
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, Email_usuarios);
                stmt.setString(2, Senha_usuarios);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return mapearUsuario(rs);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao autenticar usuário: ", e);
            }
            return null;
        }

        @Override
        public List<Usuarios> listarTodosUsuarios() {
            List<Usuarios> lista = new ArrayList<>();
            String sql = "SELECT * FROM usuarios ORDER BY Id_usuarios";

            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearUsuario(rs));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao listar usuários: ", e);
            }
            return lista;
        }

      private Usuarios mapearUsuario(ResultSet rs) throws SQLException {
    Usuarios usuario = new Usuarios();

    usuario.setId_usuarios(rs.getInt("id_usuarios"));
    usuario.setNome_usuarios(rs.getString("nome_usuarios"));
    usuario.setDatanascimento_usuarios(rs.getDate("datanascimento_usuarios"));
    usuario.setEmail_usuarios(rs.getString("email_usuarios"));
    usuario.setSenha_usuarios(rs.getString("senha_usuarios"));
    usuario.setStatus_usuarios(rs.getString("status_usuarios"));

    return usuario;
}
    }