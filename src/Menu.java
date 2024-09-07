import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu {
    private final GerenciadorProjeto gerenciador;
    private final Scanner scanner;

    Menu (GerenciadorProjeto gerenciador) {
        this.gerenciador = gerenciador;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;
        do {
            mostrarOpcoes();
            opcao = scanner.nextInt();
            realizarEscolha(opcao);
        } while (opcao != 8);
    }

    public void mostrarOpcoes() {
        System.out.println("\n===== Menu de Opções =====");
        System.out.println("Selecione a opção desejada:\n");
        System.out.println("1- Adicionar Projeto");
        System.out.println("3- Remover Projeto");
        System.out.println("2- Adicionar Tarefa a um projeto");
        System.out.println("4- Remover Tarefa de um projeto");
        System.out.println("5- Atualizar status de uma tarefa");
        System.out.println("6- Buscar projeto pelo ID");
        System.out.println("7- Listar Projetos");
        System.out.println("8- Sair");
    }

    public void realizarEscolha(int opcao) {
        switch (opcao) {
            case 1:
                adicionarProjeto();
                break;

            case 2:
                removerProjeto();
                break;

            case 3:
                adicionarTarefa();
                break;

            case 4:
                removerTarefa();
                break;

            case 5:
                atualizarStatus();
                break;

            case 6:
                buscarProjeto();
                break;

            case 7:
                listarProjetos();
                break;

            case 8:
                System.out.println("Saindo do programa");
                break;

            default:
                System.out.println("Digite uma opção válida");

        }
    }

    public void adicionarProjeto() {
        System.out.println("\n===== Adicionar Projeto =====");
        System.out.println("Digite o ID do projeto: ");
        int idProjeto = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o nome do projeto: ");
        String nomeProjeto = scanner.nextLine();
        System.out.println("Digite a descrição do Projeto: ");
        String descricaoProjeto = scanner.nextLine();
        gerenciador.adicionarProjeto(idProjeto, nomeProjeto, descricaoProjeto);
        System.out.println("Projeto adicionado com sucesso!");

    }

    public void removerProjeto() {
        System.out.println("\n===== Remover Projeto =====");
        System.out.println("Digite o ID do projeto: ");
        int idProjeto = scanner.nextInt();
        scanner.nextLine();
        boolean removido = gerenciador.removerProjeto(idProjeto);
        if (removido) {
            System.out.println("Projeto removido com sucesso!");
        }
        else {
            System.out.println("Projeto não encontrado.");
        }
    }

    public void adicionarTarefa() {
        System.out.println("\n===== Adicionar Tarefa =====");
        System.out.println("Digite o ID do projeto para adicionar a tarefa: ");
        int idProjeto = scanner.nextInt();
        scanner.nextLine();
        Projeto projeto = gerenciador.getProjeto(idProjeto);
        if(projeto == null) {
            System.out.println("Projeto não encontrado.");
            return;
        }

        System.out.println("Digite o ID da tarefa: ");
        int idTarefa = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o nome da tarefa: ");
        String nomeTarefa = scanner.nextLine();

        System.out.println("Digite a descrição da tarefa:");
        String descricaoTarefa = scanner.nextLine();

        LocalDate dataInicio = lerData("Digite a data de início (AAAA-MM-DD): ");
        LocalDate dataFim = lerData("Digite a data de término (AAAA-MM-DD): ");

        Tarefa tarefa = new Tarefa(idTarefa,nomeTarefa,descricaoTarefa,dataInicio,dataFim);
        projeto.adicionarTarefa(tarefa);
    }

    public void removerTarefa() {
        System.out.println("\n===== Remover Tarefa =====");
        System.out.println("Digite o ID do projeto para remover a tarefa: ");
        int idProjeto = scanner.nextInt();
        scanner.nextLine();
        Projeto projeto = gerenciador.getProjeto(idProjeto);
        if(projeto == null) {
            System.out.println("Projeto não encontrado.");
            return;
        }

        System.out.println("Digite o ID da tarefa: ");
        int idTarefa = scanner.nextInt();
        scanner.nextLine();
        boolean removido = projeto.removerTarefa(idTarefa);
        if (removido) {
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Tarefa não encontrada!");
        }
    }

    public void atualizarStatus() {
        System.out.println("\n===== Atualizar Status de uma tarefa =====");
        System.out.println("Digite o ID do projeto: ");
        int idProjeto = scanner.nextInt();
        scanner.nextLine();
        Projeto projeto = gerenciador.getProjeto(idProjeto);
        if(projeto == null) {
            System.out.println("Projeto não encontrado.");
            return;
        }

        System.out.println("Digite o ID da tarefa: ");
        int idTarefa = scanner.nextInt();
        scanner.nextLine();
        Tarefa tarefa = projeto.buscarTarefa(idTarefa);
        if(tarefa == null) {
            System.out.println("Tarefa não encontrada.");
            return;
        }

        System.out.println("Status atual da tarefa " + tarefa.getStatus());
        Status novoStatus = opcoesStatus();
        if(novoStatus == null) {
            System.out.println("Modificação de status cancelada.");
        } else {
            tarefa.setStatus(novoStatus);
            System.out.println("Status da tarefa modificado para " + novoStatus + " com sucesso!");
        }
    }

    public Status opcoesStatus() {
        System.out.println("\nEscolha o novo Status: ");
        System.out.println("1- Pendente");
        System.out.println("2- Em andamento");
        System.out.println("3- Concluída");

        int escolha = scanner.nextInt();
        return switch (escolha) {
            case 1 -> Status.PENDENTE;
            case 2 -> Status.EM_ANDAMENTO;
            case 3 -> Status.CONCLUIDA;
            default -> null;
        };

    }

    public void buscarProjeto() {
        System.out.println("\n===== Buscar Projeto =====");
        System.out.println("Digite o ID do projeto: ");
        int idProjeto = scanner.nextInt();
        scanner.nextLine();
        Projeto projeto = gerenciador.getProjeto(idProjeto);

        if(projeto == null) {
            System.out.println("Projeto não encontrado.");
        } else {
            System.out.println(projeto);
        }
    }

    public void listarProjetos() {
        System.out.println("\n===== Listar Projetos =====");
        for (Projeto projeto : gerenciador.listarProjetos().values()) {
            System.out.println("ID do Projeto: " + projeto.getId());
            System.out.println("Nome: " + projeto.getnome());
            System.out.println("Descrição: " + projeto.getDescricao());
            System.out.println("Tarefas: ");
            projeto.listarTarefas();
        }
    }

    public LocalDate lerData(String mensagem) {
    while(true) {
        try{
            System.out.println(mensagem);
            String entrada = scanner.nextLine().trim();
            return LocalDate.parse(entrada);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido! Utilize o formato AAAA-MM-DD.");
        }
        }
    }

}
