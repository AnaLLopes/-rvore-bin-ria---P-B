import java.util.Scanner;

public class Menu {

    Scanner scan = new Scanner(System.in);
    BinarySearchTree tree = new BinarySearchTree();
    Utils utils = new Utils();
    int op;
    int value;

    public void startMenu() {

        do {

            utils.clearConsole();

            System.out.println("\nÁRVORE BINÁRIA DE PESQUISA AVL");
            System.out.println("---------------------------------------");
            System.out.println("1 | Adicionar nodo");
            System.out.println("2 | Remover nodo");
            System.out.println("3 | Pesquisar nodo");
            System.out.println("4 | Exibir a árvore");
            System.out.println("5 | Mostrar informações");
            System.out.println("6 | Mostrar caminhamentos");
            System.out.println("7 | Soma de valores entre nodos");
            System.out.println("8 | Esvaziar árvore");
            System.out.println("-");
            System.out.println("0 | Sair do programa");
            System.out.println("---------------------------------------");
            System.out.println("Digite a opção desejada: ");

            op = utils.choose(scan, 8);

            scan = new Scanner(System.in);

            utils.clearConsole();

            switch (op) {
                case 1 -> {
                    addNode();
                }
                case 2 -> {
                    removeNode();
                }
                case 3 -> {
                    searchNode();
                }
                case 4 -> {
                    printTree();
                }
                case 5 -> {
                    showInfo();
                }
                case 6 -> {
                    showOrders();
                }
                case 7 -> {
                    sumNodes();
                }
                case 8 -> {
                    clearTree();
                }
                case 0 -> {// Sair do progama
                }
                default -> {
                    System.out.println("Opção não encontrada!");
                }

            }
        } while (op != 0);

    }

    public void addNode() {
        System.out.println("\n---------- Adicionar Nodo ----------\n");
        System.out.println("Informe um valor inteiro:\n");
        value = scan.nextInt();
        tree.add(value);
        System.out.println("\nAdicionado!\n");
        System.out.println("1. Adicionar novamente");
        System.out.println("-");
        System.out.println("0. Voltar para o menu");
        int fim = utils.choose(scan, 1);
        utils.clearConsole();
        switch (fim) {
            case 1:
                addNode();
                break;

            case 0:
                startMenu();
                break;
        }
    }

    public void removeNode() {
        System.out.println("\n---------- Remover Nodo ----------\n");
        System.out.println("Informe um valor inteiro:\n");
        value = scan.nextInt();
        if (!tree.remove(value))
            System.out.println("\nValor não encontrado!\n");
        else
            System.out.println("\nRemovido!\n");
        System.out.println("1. Remover novamente");
        System.out.println("-");
        System.out.println("0. Voltar para o menu");
        int fim = utils.choose(scan, 1);
        utils.clearConsole();
        switch (fim) {
            case 1:
                removeNode();
                break;

            case 0:
                startMenu();
                break;
        }
    }

    public void searchNode() {
        System.out.println("\n---------- Pesquisar Nodo ----------\n");
        System.out.println("Informe um valor inteiro:\n");
        value = scan.nextInt();
        if (tree.contains(value) != null)
            System.out.print("\nValor encontrado!\n");
        else
            System.out.println("\nValor não encontrado!\n");

        System.out.println("1. Pesquisar novamente");
        System.out.println("-");
        System.out.println("0. Voltar para o menu");
        int fim = utils.choose(scan, 1);
        utils.clearConsole();
        switch (fim) {
            case 1:
                searchNode();
                break;

            case 0:
                startMenu();
                break;
        }
    }

    public void printTree() {
        System.out.println("\n---------- Árvore ----------\n");
        tree.printTree();
        System.out.println("\n0. Voltar para o menu");
        int fim = utils.choose(scan, 1);
        utils.clearConsole();
        switch (fim) {
            case 1:
                startMenu();
                break;

            case 0:
                startMenu();
                break;
        }
    }

    public void showInfo() {
        System.out.println("\n---------- Informações ----------\n");
        tree.treeInfo();
        System.out.println("\n0. Voltar para o menu");
        int fim = utils.choose(scan, 1);
        utils.clearConsole();
        switch (fim) {
            case 1:
                startMenu();
                break;

            case 0:
                startMenu();
                break;
        }
    }

    public void showOrders() {
        System.out.println("\n---------- Caminhamentos ----------\n");
        tree.orders();
        System.out.println("\n\n0. Voltar para o menu");
        int fim = utils.choose(scan, 1);
        utils.clearConsole();
        switch (fim) {
            case 1:
                startMenu();
                break;

            case 0:
                startMenu();
                break;
        }
    }

    public void sumNodes() {
        System.out.println("\n---------- Soma entre Nodos ----------\n");
        System.out.println("Informe um valor inteiro de início:\n");
        int start = scan.nextInt();
        System.out.println("Informe um valor inteiro de fim:\n");
        int end = scan.nextInt();

        System.out.println("\n Resultado: " + tree.sumBetween(start, end) + "!");

        System.out.println("1. Somar novamente");
        System.out.println("-");
        System.out.println("0. Voltar para o menu");
        int fim = utils.choose(scan, 1);
        utils.clearConsole();
        switch (fim) {
            case 1:
                sumNodes();
                break;

            case 0:
                startMenu();
                break;
        }

    }

    public void clearTree() {
        System.out.println("\n---------- Limpar árvore ----------\n");
        tree.clearTree();
        System.out.println("\nA árvore foi limpa!\n");

        System.out.println("\n0. Voltar para o menu");
        int fim = utils.choose(scan, 1);
        utils.clearConsole();
        switch (fim) {
            case 1:
                startMenu();
                break;

            case 0:
                startMenu();
                break;
        }
    }
}