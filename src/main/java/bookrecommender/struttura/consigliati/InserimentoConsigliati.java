package bookrecommender.struttura.consigliati;

import bookrecommender.elaborazione.entities.Libro;
import bookrecommender.interfaccia.NuovaSchermata;
import bookrecommender.interfaccia.consigliati.InserimentoConsigliatiMessaggi;
import bookrecommender.struttura.ricercalibro.RicercaLibro;
import

public class InserimentoConsigliati {

  public InserimentoConsigliati(String UserID, Libro l) {





   /* var ricercaLibro=new RicercaLibro(2);
    Libro libro = ricercaLibro.getLibro();
    int scelta = ricercaLibro.getScelta();
*/
    NuovaSchermata.nuovaSchermata();



    InserimentoConsigliatiMessaggi.consigliatoAggiunto();

  }

}
