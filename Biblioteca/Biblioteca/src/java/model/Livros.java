package model;

public class Livros {
    private int Id_livros;
    private String Nome_livros;
    private String Autor_livros;
    private int Quantidade_livros;
    private String Faixaetaria_livros;
    private String Categoria_livros;
    private int Anopublicacao_livros;

 
    public Livros() {
        
    }

  
    public Livros(int Id_livros, String Nome_livros, String Autor_livros, int Quantidade_livros, String Faixaetaria_livros, String Categoria_livros, int Anopublicacao_livros) {
        this.Id_livros = Id_livros;
        this.Nome_livros = Nome_livros;
        this.Autor_livros = Autor_livros;
        this.Quantidade_livros = Quantidade_livros;
        this.Faixaetaria_livros = Faixaetaria_livros;
        this.Categoria_livros = Categoria_livros;
        this.Anopublicacao_livros = Anopublicacao_livros;
    }

    public int getId_livros() {
        return Id_livros;
    }

    public void setId_livros(int Id_livros) {
        this.Id_livros = Id_livros;
    }

    public String getNome_livros() {
        return Nome_livros;
    }

    public void setNome_livros(String Nome_livros) {
        this.Nome_livros = Nome_livros;
    }

    public String getAutor_livros() {
        return Autor_livros;
    }

    public void setAutor_livros(String Autor_livros) {
        this.Autor_livros = Autor_livros;
    }

    public int getQuantidade_livros() {
        return Quantidade_livros;
    }

    public void setQuantidade_livros(int Quantidade_livros) {
        this.Quantidade_livros = Quantidade_livros;
    }

    public String getFaixaetaria_livros() {
        return Faixaetaria_livros;
    }

    public void setFaixaetaria_livros(String Faixaetaria_livros) {
        this.Faixaetaria_livros = Faixaetaria_livros;
    }

    public String getCategoria_livros() {
        return Categoria_livros;
    }

    public void setCategoria_livros(String Categoria_livros) {
        this.Categoria_livros = Categoria_livros;
    }

    public int getAnopublicacao_livros() {
        return Anopublicacao_livros;
    }

    public void setAnopublicacao_livros(int Anopublicacao_livros) {
        this.Anopublicacao_livros = Anopublicacao_livros;
    }

   
}
