package bookrecommender.struttura.visualizzazionelibro;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.dao.LibroDao;
import bookrecommender.elaborazione.dao.ValutazioneDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatoDaoImpl;
import bookrecommender.elaborazione.dao.daoimpl.LibroDaoImpl;
import bookrecommender.elaborazione.dao.daoimpl.ValutazioneDaoImpl;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.elaborazione.entities.Valutazione;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.visualizzazioneLibro.VisualizzazioneLIbroMessaggi;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Classe che ha la funzione di gestire la
 * sezione di visualizzazione del libro.
 *
 * @author Angelo Penafiel
 * @version 1.0
 */

public class VisualizzazioneLibro {


  //CAMPO

  /**
   * Campo che indica il numero di scelta
   * effettuata del menu di selezione.
   */

  private int scelta;


  //COSTRUTTORE

  /**
   * Restituisce l'oggetto di tipo VisualizzazioneLibro
   * e all'interno viene effettuata la stampa
   * dei dati del libro, dati il libro corrente
   * e il menu di provenienza (in base al valore del
   * menu di provenienza stampa un menu di selezione diverso).
   *
   * @param menuProvenienza indica il menu di provenienza
   *
   * @param libro indica il libro corrente
   */

  public VisualizzazioneLibro(int menuProvenienza, Libro libro) {

    HashMap<String, Integer> consigliatiCountedId;

    ConsigliatoDao consigliatoDao=new ConsigliatoDaoImpl();
    try {
      consigliatiCountedId=consigliatoDao.getLibriConsigliatiCountedByLibroId(libro.getId().toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    HashMap<String, Integer> consigliatiCountedTitolo = new HashMap<>();

    for(Entry<String, Integer> entry:consigliatiCountedId.entrySet()) {

      String titolo;
      LibroDao libroDao=new LibroDaoImpl();

      try {
        titolo=libroDao.getTitoloById(Integer.parseInt(entry.getKey()));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      consigliatiCountedTitolo.put(titolo,entry.getValue());
    }


    List<Valutazione> valutazioni;

    ValutazioneDao valutazioneDao=new ValutazioneDaoImpl();
    try {
      valutazioni=valutazioneDao.getByLibroId(libro.getId().toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Valutazione media;

    if(valutazioni.size()==0) {
      media=new Valutazione((double)0,(double)0,(double)0,(double)0,(double)0,(double)0);
    }

    else  {

      Valutazione somma=new Valutazione((double)0,(double)0,(double)0,(double)0,(double)0,(double)0);

      for(Valutazione valutazione:valutazioni) {
        somma.setStile(somma.getStile()+valutazione.getStile());
        somma.setContenuto(somma.getContenuto()+valutazione.getContenuto());
        somma.setGradevolezza(somma.getGradevolezza()+valutazione.getGradevolezza());
        somma.setOriginalita(somma.getOriginalita()+valutazione.getOriginalita());
        somma.setEdizione(somma.getEdizione()+valutazione.getEdizione());
        somma.setFinale(somma.getFinale()+valutazione.getFinale());
      }

      int size=valutazioni.size();

      media=new Valutazione(somma.getStile()/size,
          somma.getContenuto()/size, somma.getGradevolezza()/size,
          somma.getOriginalita()/size,somma.getEdizione()/size,
          somma.getFinale()/size);
    }

    NuovaSchermata.nuovaSchermata();
    VisualizzazioneLIbroMessaggi.intestazione();
    VisualizzazioneLIbroMessaggi.stampaDatiLibro(libro,consigliatiCountedTitolo,media,valutazioni.size());

    if(menuProvenienza==0) {
      VisualizzazioneLIbroMessaggi.menuSceltaSenzaRegistrazione();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(3);
    }

    if(menuProvenienza==1) {
      VisualizzazioneLIbroMessaggi.menuSceltaUtenteRegistrato();
      scelta = SceltaMenuMessaggi.inserimentoSceltaMenu(4);
    }

    if(menuProvenienza==2) {
      VisualizzazioneLIbroMessaggi.consigliati();
    }

  }

  /**
   * Restituisce la scelta effettuata
   * del menu di selezione.
   */

  public int getScelta() {
    return scelta;
  }

}
