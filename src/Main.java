public class Main {
    public static void main(String[] args) {
    GerenciadorProjeto gerenciador = new GerenciadorProjeto();
    Menu menu = new Menu(gerenciador);
    menu.exibir();
    }
}
