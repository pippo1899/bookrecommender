package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.dao.LibroDao;
import bookrecommender.elaborazione.dao.daoimpl.ConsigliatoDaoImpl;
import bookrecommender.elaborazione.dao.daoimpl.LibroDaoImpl;
import bookrecommender.elaborazione.entities.Consigliato;
import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.menu.SceltaMenuMessaggi;
import bookrecommender.interfaccia.ricercalibro.RicercaLibroTitoloMessaggi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelezioneConsigliati {

  private Libro libro;

  public SelezioneConsigliati(String userId) {

    Consigliato consigliati;

    ConsigliatoDao consigliatoDao =new ConsigliatoDaoImpl();
    try {
      consigliati= consigliatoDao.getByUserId(userId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    List<Integer> idsLibriConsigliati=new ArrayList<>();

    for(String consigliato:consigliati.getLibriConsigliati()) {
      idsLibriConsigliati.add(Integer.parseInt(consigliato));
    }

    Integer idLibroSelezionato;

    List<Libro> opzioniTitoloAnno;

    try {
      LibroDao libroDao=new LibroDaoImpl();
      opzioniTitoloAnno=libroDao.getTitoloAndAnnoByIds(idsLibriConsigliati);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    NuovaSchermata.nuovaSchermata();

    RicercaLibroTitoloMessaggi.stampaOpzioniTitoloAnno(opzioniTitoloAnno);
    idLibroSelezionato = SceltaMenuMessaggi.inserimentoSceltaMenu(opzioniTitoloAnno.size());

    idLibroSelezionato=idsLibriConsigliati.get(idLibroSelezionato-1);

    try {
      LibroDao libroDao=new LibroDaoImpl();
      libro=libroDao.getById(idLibroSelezionato);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Libro getLibro() {
    return libro;
  }
}
