package model;

import java.util.Date;

public class Usuarios {

    private int Id_usuarios;
    private String Nome_usuarios;
    private Date Datanascimento_usuarios;
    private String Email_usuarios;
    private String Senha_usuarios;
    private String Status_usuarios;

 
    public Usuarios() {
        
    }

   
    public Usuarios(int Id_usuarios, String Nome_usuarios, Date Datanascimento_usuarios,
                   String Email_usuarios, String Senha_usuarios, String Status_usuarios) {
        this.Id_usuarios = Id_usuarios;
        this.Nome_usuarios = Nome_usuarios;
        this.Datanascimento_usuarios = Datanascimento_usuarios;
        this.Email_usuarios = Email_usuarios;
        this.Senha_usuarios = Senha_usuarios;
        this.Status_usuarios = Status_usuarios;
    }

 
    public int getId_usuarios() {
        return Id_usuarios;
    }

   
    public void setId_usuarios(int Id_usuarios) {
        this.Id_usuarios = Id_usuarios;
    }

    public String getNome_usuarios() {
        return Nome_usuarios;
    }

    
    public void setNome_usuarios(String Nome_usuarios) {
        this.Nome_usuarios = Nome_usuarios;
    }

   
    public Date getDatanascimento_usuarios() {
        return Datanascimento_usuarios;
    }

   
    public void setDatanascimento_usuarios(Date Datanascimento_usuarios) {
        this.Datanascimento_usuarios = Datanascimento_usuarios;
    }

  
    public String getEmail_usuarios() {
        return Email_usuarios;
    }

  
    public void setEmail_usuarios(String Email_usuarios) {
        this.Email_usuarios = Email_usuarios;
    }


    public String getSenha_usuarios() {
        return Senha_usuarios;
    }

    
    public void setSenha_usuarios(String Senha_usuarios) {
        this.Senha_usuarios = Senha_usuarios;
    }

    public String getStatus_usuarios() {
        return Status_usuarios;
    }

    
    public void setStatus_usuarios(String Status_usuarios) {
        this.Status_usuarios = Status_usuarios;
    }

   
}