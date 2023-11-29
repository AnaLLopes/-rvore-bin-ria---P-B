import java.util.*;

public class Utils {

    public void clearLine(int quantidade) {
        System.out.print(String.format("\033[%dA", quantidade)); // vai pra cima
        System.out.print("\033[2K"); // Limpa linha
    }

    public void clearLine() {
        clearLine(1);
    }

    public void clearConsole() {
        System.out.print(String.format("\033[2J"));
    }

    public int choose(Scanner sc, int max) {

        int pesquisa = -1;
        while (pesquisa < 0) {
            try {
                int p = sc.nextInt(max + 1);
                pesquisa = p;
            } catch (Exception e) {
                clearLine();
                if (pesquisa != -2)
                    System.out.println("Opção não encontrada!");

                sc = new Scanner(System.in);
                pesquisa = -2;
            }
        }

        sc.nextLine();

        return pesquisa;
    }

};