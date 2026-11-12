public class Main {
    public static void main(String[] args) {
        Biblioteca bib = new Biblioteca(10);
        bib.adicionar(new Livro("L1", "Dom Casmurro"));
        bib.adicionar(new Livro("L2", "Clean Code"));
        bib.adicionar(new Livro("L3", "Estruturas de Dados"));
        bib.adicionar(new Revista("R1", "Superinteressante"));
        bib.adicionar(new Revista("R2", "Veja"));

        Aluno juliano = new Aluno("Juliano Rafael");
        Professor michele = new Professor("Michele");

        System.out.println("=== Acervo inicial ===");
        bib.listarAcervo();

        System.out.println("\n=== Empréstimos ===");
        bib.emprestar(juliano, "L1");
        bib.emprestar(juliano, "L2");
        bib.emprestar(juliano, "R1");
        bib.emprestar(juliano, "R2");   // recusado: limite atingido
        bib.emprestar(michele, "L1");   // recusado: já emprestado
        bib.emprestar(michele, "R2");

        System.out.println("\n=== Devolução com atraso ===");
        bib.devolver(juliano, "R1", 3);

        System.out.println("\n=== Acervo final ===");
        bib.listarAcervo();
    }
}