import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private int idProjeto;
    private String nomeProjeto;
    private String descricao;
    private List<Tarefa> listaTarefa;

    Projeto(int idProjeto, String nomeProjeto, String descricao) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
        this.listaTarefa = new ArrayList<>();
    }

    public int getId() {
        return idProjeto;
    }

    public String getnome() {
        return nomeProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void adicionarTarefa(Tarefa tarefa) {
        this.listaTarefa.add(tarefa);
    }

    public boolean removerTarefa(int idTarefa) {
        return listaTarefa.removeIf(tarefa -> tarefa.getId() == idTarefa);
    }

    public Tarefa buscarTarefa(int idTarefa) {
        for (Tarefa tarefa : listaTarefa) {
            if (tarefa.getId() == idTarefa) {
                return tarefa;
            }
        }
        return null;
    }

    public void listarTarefas() {
        for(Tarefa tarefa : listaTarefa) {
            System.out.println("\n\tID da Tarefa: " + tarefa.getId());
            System.out.println("\tNome: " + tarefa.getNome());
            System.out.println("\tDescrição: " + tarefa.getDescricao());
            System.out.println("\tStatus: " + tarefa.getStatus());
            System.out.println("\tData de Início: " + tarefa.getDataInicio());
            System.out.println("\tData de Fim: " + tarefa.getDataFim());
            System.out.println("\t==========================\n");
        }
    }
}
