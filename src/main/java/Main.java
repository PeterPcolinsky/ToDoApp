import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> ulohy = new ArrayList<>();

        while (true) {
            vypisMenu();
            System.out.print("Zvoľ možnosť: ");
            String volba = sc.nextLine().trim();

            switch (volba) {
                case "1":
                    System.out.print("Zadaj novú úlohu: ");
                    String uloha = sc.nextLine().trim();
                    if (!uloha.isEmpty()) {
                        ulohy.add(uloha);
                        System.out.println("Pridané.");
                    } else {
                        System.out.println("Prázdny text sa nepridáva.");
                    }
                    break;

                case "2":
                    vypisUlohy(ulohy);
                    break;

                case "3":
                    vypisUlohy(ulohy);
                    if (ulohy.isEmpty()) break;
                    System.out.print("Číslo úlohy na označenie ako hotovo: ");
                    String sIndexDone = sc.nextLine().trim();
                    // TODO: Pridať kontrolu čísla (try-catch) – doplniť neskôr
                    int idxDone = parseIndexOrMinusOne(sIndexDone) - 1;
                    if (idxDone >= 0 && idxDone < ulohy.size()) {
                        String povodna = ulohy.get(idxDone);
                        if (!povodna.endsWith(" (hotovo)")) {
                            ulohy.set(idxDone, povodna + " (hotovo)");
                            System.out.println("Označené ako hotovo.");
                        } else {
                            System.out.println("Už je hotovo.");
                        }
                    } else {
                        System.out.println("Neplatný index.");
                    }
                    break;

                case "4":
                    vypisUlohy(ulohy);
                    if (ulohy.isEmpty()) break;
                    System.out.print("Číslo úlohy na zmazanie: ");
                    String sIndexDel = sc.nextLine().trim();
                    // TODO: Pridať kontrolu čísla (try-catch) – doplniť neskôr
                    int idxDel = parseIndexOrMinusOne(sIndexDel) - 1;
                    if (idxDel >= 0 && idxDel < ulohy.size()) {
                        ulohy.remove(idxDel);
                        System.out.println("Zmazané.");
                    } else {
                        System.out.println("Neplatný index.");
                    }
                    break;

                case "0":
                    System.out.println("Koniec. Pekný deň!");
                    return;

                default:
                    System.out.println("Neplatná voľba.");
            }
            System.out.println();
        }
    }

    private static void vypisMenu() {
        System.out.println("=== TODO MENU ===");
        System.out.println("1) Pridaj úlohu");
        System.out.println("2) Zobraz úlohy");
        System.out.println("3) Označ ako hotovo");
        System.out.println("4) Zmaž úlohu");
        System.out.println("0) Koniec");
    }

    private static void vypisUlohy(List<String> ulohy) {
        if (ulohy.isEmpty()) {
            System.out.println("(Žiadne úlohy)");
            return;
        }
        for (int i = 0; i < ulohy.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, ulohy.get(i));
        }
    }

    // Pomocná funkcia – neskôr doplniť validáciu a ošetrenie chýb
    private static int parseIndexOrMinusOne(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}