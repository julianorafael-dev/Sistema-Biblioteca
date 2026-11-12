public abstract class ItemBiblioteca {
    private final String codigo;

    private final String titulo;

    private boolean disponivel = true;
    public ItemBiblioteca(String codigo,String titulo){
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public abstract int getPrazoDias();

    public abstract double getMultaPorDia();

    void marcarEmprestado() {
        disponivel = false;
    }

    void  marcarDevolvido() {
        disponivel = true;
    }

    public double calcularMulta(int diasAtraso) {
        if(diasAtraso > 0){
            return  diasAtraso*getMultaPorDia();
        }
        return  0.0;

    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + getClass().getSimpleName() + " - " + titulo
                + " | prazo: " + getPrazoDias() + " dias"
                + " | multa: R$ " + getMultaPorDia() + "/dia"
                + " | " + (disponivel ? "disponível" : "emprestado");
    }


}