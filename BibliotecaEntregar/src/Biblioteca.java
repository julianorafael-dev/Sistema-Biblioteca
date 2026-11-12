public class Biblioteca {
    private final ItemBiblioteca[] acervo;
    private final Usuario[] emprestadoPara;
    private int totalItens = 0;

    public Biblioteca(int capacidade) {
        acervo = new ItemBiblioteca[capacidade];
        emprestadoPara = new Usuario[capacidade];
    }

    public boolean adicionar(ItemBiblioteca item) {
        if (totalItens >= acervo.length) {
            return false;
        }
        acervo[totalItens] = item;
        totalItens++;
        return true;
    }

    private int buscarIndice(String codigo) {
        for (int i = 0; i < totalItens; i++) {
            if (acervo[i].getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public boolean emprestar(Usuario usuario, String codigo) {
        int i = buscarIndice(codigo);
        if (i < 0) {
            System.out.println("RECUSADO: item " + codigo + " não existe.");
            return false;
        }
        if (!acervo[i].isDisponivel()) {
            System.out.println("RECUSADO: \"" + acervo[i].getTitulo() + "\" já está emprestado.");
            return false;
        }
        if (!usuario.podeEmprestar()) {
            System.out.println("RECUSADO: " + usuario.getNome() + " atingiu o limite de "
                    + usuario.getLimiteItens() + " itens.");
            return false;
        }
        acervo[i].marcarEmprestado();
        usuario.registrarEmprestado();
        emprestadoPara[i] = usuario;
        System.out.println("OK: " + usuario.getNome() + " pegou \"" + acervo[i].getTitulo()
                + "\" (prazo: " + acervo[i].getPrazoDias() + " dias).");
        return true;
    }

    public double devolver(Usuario usuario, String codigo, int diasAtraso) {
        int i = buscarIndice(codigo);
        if (i < 0 || emprestadoPara[i] != usuario) {
            System.out.println("RECUSADO: devolução inválida.");
            return 0.0;
        }
        acervo[i].marcarDevolvido();
        usuario.registrarDevolucao();
        emprestadoPara[i] = null;
        double multa = acervo[i].calcularMulta(diasAtraso);
        System.out.printf("OK: %s devolveu \"%s\". Multa: R$ %.2f%n",
                usuario.getNome(), acervo[i].getTitulo(), multa);
        return multa;
    }

    public void listarAcervo() {
        for (int i = 0; i < totalItens; i++) {
            System.out.println(acervo[i]);
        }
    }
}