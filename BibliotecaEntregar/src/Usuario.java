public abstract class Usuario {
    private final String nome;
    private int quantidadeEmprestada = 0;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }


    public int getQuantidadeEmprestado() {
        return quantidadeEmprestada;
    }

    public abstract int getLimiteItens();

    void registrarEmprestado(){
        quantidadeEmprestada++;
    }

    void registrarDevolucao(){
        quantidadeEmprestada--;
    }



   public boolean podeEmprestar(){
        if(quantidadeEmprestada <getLimiteItens() ){
            return true;
        }
        return false;
   }
}
