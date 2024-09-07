import java.util.HashMap;
import java.util.Map;

public class GerenciadorProjeto {
    private Map<Integer,Projeto> projetos;

    public GerenciadorProjeto() {
        projetos = new HashMap<Integer,Projeto>();
    }

    public Projeto getProjeto(int idProjeto) {
        return projetos.get(idProjeto);
    }

    public void adicionarProjeto(int idProjeto, String nomeProjeto, String descricao) {
        Projeto projeto = new Projeto(idProjeto, nomeProjeto, descricao);
        projetos.put(idProjeto, projeto);
    }

    public boolean removerProjeto(int idProjeto) {
        return projetos.remove(idProjeto) != null;
    }

    public Map<Integer, Projeto> listarProjetos() {
        return projetos;
    }
}
