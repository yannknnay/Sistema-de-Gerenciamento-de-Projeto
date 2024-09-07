import java.time.LocalDate;

enum Status {
    PENDENTE,
    EM_ANDAMENTO,
    CONCLUIDA
}

public class Tarefa {
    private int id;
    private String nome;
    private String descricao;
    private Status status;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    Tarefa(int idTarefa, String nomeTarefa, String descricao,LocalDate dataInicio, LocalDate dataFim) {

        this.id = idTarefa;
        this.nome = nomeTarefa;
        this.descricao = descricao;
        this.status = Status.PENDENTE;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status novoStatus) {
        this.status = novoStatus;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

}
