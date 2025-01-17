package bookrecommender.interfaccia.menu;

/**
 * Classe che ha la funzione di stampare i
 * messaggi del menu iniziale.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public final class MenuInizialeMessaggi {

    //COSTRUTTORE

    private MenuInizialeMessaggi() {

    }

    //METODO

    /**
     * Stampa il menu iniziale.
     */

    public static void menu() {

        System.out.println("-----------Menu iniziale-------------");
        System.out.println("| 1) Accedi                         |");
        System.out.println("| 2) Registrazione                  |");
        System.out.println("| 3) Continua senza registrazione   |");
        System.out.println("-------------------------------------");
    }
}
