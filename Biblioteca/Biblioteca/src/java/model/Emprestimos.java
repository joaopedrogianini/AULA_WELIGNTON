package model;

import java.util.Date;

public class Emprestimos {
    private int Id_emprestimos;
    private Date Data_emprestimos;
    private Date Dataprevista_devolucao;
    private Date Datareal_devolucao;
    private double multa;
    private String status_emprestimos;
    private int Id_usuarios;
    private int Id_livros;

  
    public Emprestimos() {
    }

    
    public Emprestimos(int Id_emprestimos, Date Data_emprestimos, Date Dataprevista_devolucao, Date Datareal_devolucao, double multa, String status_emprestimos, int Id_usuarios, int Id_livros) {
        this.Id_emprestimos = Id_emprestimos;
        this.Data_emprestimos = Data_emprestimos;
        this.Dataprevista_devolucao = Dataprevista_devolucao;
        this.Datareal_devolucao = Datareal_devolucao;
        this.multa = multa;
        this.status_emprestimos = status_emprestimos;
        this.Id_usuarios = Id_usuarios;
        this.Id_livros = Id_livros;
    }

    public int getId_emprestimos() {
        return Id_emprestimos;
    }

    public void setId_emprestimos(int Id_emprestimos) {
        this.Id_emprestimos = Id_emprestimos;
    }

    public Date getData_emprestimos() {
        return Data_emprestimos;
    }

    public void setData_emprestimos(Date Data_emprestimos) {
        this.Data_emprestimos = Data_emprestimos;
    }

    public Date getDataprevista_devolucao() {
        return Dataprevista_devolucao;
    }

    public void setDataprevista_devolucao(Date Dataprevista_devolucao) {
        this.Dataprevista_devolucao = Dataprevista_devolucao;
    }

    public Date getDatareal_devolucao() {
        return Datareal_devolucao;
    }

    public void setDatareal_devolucao(Date Datareal_devolucao) {
        this.Datareal_devolucao = Datareal_devolucao;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public String getStatus_emprestimos() {
        return status_emprestimos;
    }

    public void setStatus_emprestimos(String status_emprestimos) {
        this.status_emprestimos = status_emprestimos;
    }

    public int getId_usuarios() {
        return Id_usuarios;
    }

    public void setId_usuarios(int Id_usuarios) {
        this.Id_usuarios = Id_usuarios;
    }

    public int getId_livros() {
        return Id_livros;
    }

    public void setId_livros(int Id_livros) {
        this.Id_livros = Id_livros;
    }

    
}
