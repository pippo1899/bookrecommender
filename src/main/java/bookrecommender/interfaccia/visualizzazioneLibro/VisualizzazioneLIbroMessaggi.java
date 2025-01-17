package bookrecommender.interfaccia.visualizzazioneLibro;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.Valutazione;
import java.util.List;

public final class VisualizzazioneLIbroMessaggi {

    //COSTRUTTORE

    private VisualizzazioneLIbroMessaggi() {

    }

    //METODI

    public static void menuSceltaSenzaRegistrazione() {
        System.out.println("----------------------------------------------------------");
        System.out.println("| 1) Torna al menu di scelta della modalità di ricerca ");
        System.out.println("| 2) Torna al menu principale ");
        System.out.println("| 3) Esci dal programma");
        System.out.println("----------------------------------------------------------");
    }

    public static void menuSceltaUtenteRegistrato() {
        System.out.println("----------------------------------------------------------");
        System.out.println("| 1) Torna al menu di scelta della modalità di ricerca ");
        System.out.println("| 2) Torna al menu principale ");
        System.out.println("| 3) Log out");
        System.out.println("| 4) Esci dal programma");
        System.out.println("----------------------------------------------------------");
    }

    public static void menuSceltaUtenteConsigliati() {
        System.out.println("----------------------------------------------------------");
        System.out.println("| 1) Torna al menu principale ");
        System.out.println("| 2) Log out");
        System.out.println("| 3) Esci dal programma");
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Stampa l'intestazione della sezione di
     * inserimento dell'autore.
     */

    public static void intestazione() {
        System.out.println("---------------------------------Libro------------------------------------");
    }

    public static void intestazioneValutazione() {
        System.out.print("\n\n------------------------------Valutazione------------------------------");
    }

    public static void stampaDatiLibro(Libro libro, int utentiConsigliati, Valutazione media) {

        System.out.print("\n  Titolo: ");
        stampaTìtolo(libro.getTitolo());

        System.out.print("  Autori: ");
        stampaAutori(libro.getAutori());

        System.out.print("\n\n  Anno di pubblicazione: "+libro.getAnnoPubblicazione());

        System.out.print("\n\n  Editore: "+libro.getEditore().substring(0,1).toUpperCase()
            +libro.getEditore().substring(1));

        System.out.print("\n\n  Categorie: ");
        stampaCategorie(libro.getCategorie());

        System.out.print("\n\n  Consigliato da "+utentiConsigliati+" utenti.");

        intestazioneValutazione();
        stampaValutazione(media);

        System.out.print("\n\n");

    }

    private static void stampaTìtolo(String titolo) {

        titolo=titolo.substring(0,1).toUpperCase()+titolo.substring(1);

        int j=0;

        if(titolo.length()<50) {
            System.out.print(titolo);
        }

        else {

            for(int i=0;i<=titolo.length()/50;i++) {
                if(i==titolo.length()/50) {
                    System.out.print(titolo.substring(j,titolo.length()%50+(i)*50));
                }

                else {
                    System.out.print(titolo.substring(j,(i+1)*50));
                    System.out.print("\n          ");
                }

                j+=50;

            }
        }

        System.out.print("\n\n");
    }

    private static void stampaAutori(List<String> autori)  {

        for(int i=0;i<autori.size();i++) {
            System.out.print(autori.get(i).substring(0,1).toUpperCase()
                +autori.get(i).substring(1));

            if(i+1==autori.size()) {
                System.out.print(".");
            }

            else {
                System.out.print(", ");
            }

        }
    }

    private static void stampaCategorie(List<String> categorie)  {

        for(int i=0;i<categorie.size();i++) {
            System.out.print(categorie.get(i).substring(0,1).toUpperCase()+
                categorie.get(i).substring(1));

            if(i+1==categorie.size()) {
                System.out.print(".");
            }

            else {
                System.out.print(", ");
            }

        }
    }

    private static void stampaValutazione(Valutazione media) {

        if(media.getStile()==0&&media.getContenuto()==0&&media.getGradevolezza()==0
            &&media.getOriginalita()==0&&media.getEdizione()==0&&media.getFinale()==0) {

            System.out.print("\n\n Non ci sono valutazioni per questo libro.");
        }

        else {
            System.out.print("\n\n Stile: "+media.getStile()+"/5");
            System.out.print("\n\n Contenuto: "+media.getContenuto()+"/5");
            System.out.print("\n\n Gradevolezza: "+media.getGradevolezza()+"/5");
            System.out.print("\n\n Originalità: "+media.getOriginalita()+"/5");
            System.out.print("\n\n Edizione: "+media.getEdizione()+"/5");
            System.out.print("\n\n Finale: "+media.getFinale()+"/5");
        }

    }

}
