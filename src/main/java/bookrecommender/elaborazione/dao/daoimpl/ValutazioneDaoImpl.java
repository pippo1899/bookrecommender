package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.dao.ValutazioneDao;
import bookrecommender.elaborazione.entities.Valutazione;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * Classe che implementa il design pattern DAO.
 * La sua funzione è quella di separare la logica
 * di accesso ai dati dalla logica di business.
 * Ha lo scopo di prelevare i dati del file csv
 * (ValutazioniLibri.dati.csv) e creare oggetti o
 * campi di classe entities/Valutazione.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class ValutazioneDaoImpl implements ValutazioneDao {


  //CAMPO

  /**
   * Costante che rappresenta l'header del file
   * ValutazioniLibri.dati.csv.
   */

  private static final String[] HEADERS = {"UserID","Libro",
      "Stile","tStile","Contenuto","tContenuto","Gradevolezza","tGradevolezza",
      "Originalita","tOriginalita","Edizione","tEdizione","Finale","tFinale"};


  //METODI

  /**
   * Restituisce una lista di oggetti
   * Valutazione che hanno come libroId
   * quello dato.
   *
   * @param libroId indica il libroId
   *
   * @return lista di oggetti Valutazione
   */

  @Override
  public List<Valutazione> getByLibroId(String libroId) throws IOException {

    List<Valutazione> valutazioni=new ArrayList<>();

    Reader in = new FileReader("data/ValutazioniLibri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    for (CSVRecord record : records) {

      String userId = record.get("UserID");
      String libroIdC = record.get("Libro");
      Double stile = (double) Integer.parseInt(record.get("Stile"));
      Double contenuto = (double) Integer.parseInt(record.get("Contenuto"));
      Double gradevolezza = (double) Integer.parseInt(record.get("Gradevolezza"));
      Double originalita = (double) Integer.parseInt(record.get("Originalita"));
      Double edizione = (double) Integer.parseInt(record.get("Edizione"));
      Double finale = (double) Integer.parseInt(record.get("Finale"));

      if (libroId.equals(libroIdC)) {
        valutazioni.add(new Valutazione(userId, libroId, stile, contenuto,
            gradevolezza, originalita, edizione, finale));
      }

    }

    return valutazioni;
  }

}
