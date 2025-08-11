import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> ulohy = new ArrayList<>();

        while (true) {
            vypisMenu(); // zobrazí hlavné menu
            System.out.print("Zvoľ možnosť: ");
            String volba = sc.nextLine().trim();

            switch (volba) {
                case "1": // pridanie úlohy
                    System.out.print("Zadaj novú úlohu: ");
                    String uloha = sc.nextLine().trim();
                    if (!uloha.isEmpty()) {
                        ulohy.add(uloha);
                        System.out.println("Pridané.");
                    } else {
                        System.out.println("Prázdny text sa nepridáva.");
                    }
                    break;

                case "2":// zobrazenie úloh
                    vypisUlohy(ulohy);
                    break;

                case "3": // označenie úlohy ako hotovo
                    vypisUlohy(ulohy);
                    if (ulohy.isEmpty()) break;
                    int idxDone = readIndex(sc, ulohy.size(), "Číslo úlohy na označenie ako hotovo");
                    if (idxDone == -1) break;
                    String povodna = ulohy.get(idxDone);
                    if (!povodna.endsWith(" (hotovo)")) {
                        ulohy.set(idxDone, povodna + " (hotovo)");
                        System.out.println("Označené ako hotovo.");
                    } else {
                        System.out.println("Už je hotovo.");
                    }
                    break;

                case "4": // zmazanie úlohy
                    vypisUlohy(ulohy);
                    if (ulohy.isEmpty()) break;
                    int idxDel = readIndex(sc, ulohy.size(), "Číslo úlohy na zmazanie");
                    if (idxDel == -1) break;
                    ulohy.remove(idxDel);
                    System.out.println("Zmazané.");
                    break;

                case "0": // ukončenie aplikácie
                    System.out.println("Koniec. Pekný deň!");
                    return;

                default: // neplatná voľba
                    System.out.println("Neplatná voľba.");
            }
            System.out.println();
        }
    }

    // vypíše textové menu
    private static void vypisMenu() {
        System.out.println("=== TODO MENU ===");
        System.out.println("1) Pridaj úlohu");
        System.out.println("2) Zobraz úlohy");
        System.out.println("3) Označ ako hotovo");
        System.out.println("4) Zmaž úlohu");
        System.out.println("0) Koniec");
    }

    // vypíše zoznam úloh s číslovaním
    private static void vypisUlohy(List<String> ulohy) {
        if (ulohy.isEmpty()) {
            System.out.println("(Žiadne úlohy)");
            return;
        }
        for (int i = 0; i < ulohy.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, ulohy.get(i));
        }
    }

    // načíta platný index úlohy od používateľa alebo -1 pre zrušenie výberu
    private static int readIndex(Scanner sc, int max, String prompt) {
        while (true) {
            System.out.print(prompt + " (1-" + max + ", Enter = späť): ");
            String s = sc.nextLine().trim();
            if (s.isEmpty()) return -1; // zrušiť výber
            try {
                int n = Integer.parseInt(s);
                if (n >= 1 && n <= max) return n - 1;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Zadaj číslo v rozsahu 1-" + max + " alebo stlač Enter.");
        }
    }
}