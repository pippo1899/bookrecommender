package bookrecommender.struttura.ricercalibro;

import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroMessaggi;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Classe che ha la funzione di gestire la
 * sezione del menu di scelta della ricerca
 * dei libri.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class RicercaLibro {

    //CAMPI

    /**
     * Costante che indica il numero massimo
     * di risultati stampati per pagina.
     */

    public static final Integer MAX_RISULTATI_PAGINA=7;

    /**
     * Campo che indica il numero di scelta
     * effettuata del menu di ricerca dei
     * libri.
     */

    private int scelta;


    //COSTRUTTORE

    /**
     * Gestisce l'indirizzamento del flusso del
     * programma in base alla scelta della modalità
     * di ricerca dei libri nel caso in cui l'utente
     * non ha effettuato il login.
     */

    public RicercaLibro(int continuaSenzaRegistrazione) {
        cercaLibro();
    }

    //METODI

    private void caricamentoLibri() throws IOException {

        Reader in = new FileReader("data/Libri.dati.csv");


        String[] HEADERS = {"Title","Authors","Description","Category","Publisher","Price Starting With ($)",
        "Publish Date (Month)","Publish Date (Year)"};

        var csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        List<CSVRecord> records = csvFormat.parse(in).getRecords();

        for (CSVRecord record : records) {

            String titolo=record.get("Title").toLowerCase();
            String authors=record.get("Authors").toLowerCase();
            Integer annoPubblicazione=Integer.parseInt(record.get("Publish Date (Year)"));
            String editore=record.get("Publisher").toLowerCase();
            String categories=record.get("Category").toLowerCase();
           }
    }

    /**
     * Gestisce l'indirizzamento del flusso del
     * programma in base alla scelta della modalità
     * di ricerca dei libri.
     */

    private void cercaLibro() {

        boolean controllo;

        do {

            controllo=true;

            NuovaSchermata.nuovaSchermata();
            RicercaLibroMessaggi.menuSenzaRegistrazione();

            scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(6);

            int modalitaRicerca=scelta;

            if(scelta==1) {
                var ricercaLibroTitolo=new RicercaLibroTitolo();

                if(ricercaLibroTitolo.isTornaIndietro()) {
                    controllo=false;
                }
            }

            if(scelta==2) {
                var ricercaLibroAutore=new RicercaLibroAutore();
                if(ricercaLibroAutore.isTornaIndietro()) {
                    controllo=false;
                }
            }

            if(scelta==3) {
                var ricercaLibroAutoreAnnoPubblicazione=new RicercaLibroAutoreAnnoPubblicazione();
                if(ricercaLibroAutoreAnnoPubblicazione.isTornaIndietro()) {
                    controllo=false;
                }
            }

            if(scelta==4) {

            }

            if(scelta==5) {

            }

        } while(!controllo);
    }

    /**
     * Restituisce la scelta effettuata
     * del menu di selezione della modalità
     * di ricerca dei libri.
     */

    public int getScelta() {
        return scelta;
    }

}
