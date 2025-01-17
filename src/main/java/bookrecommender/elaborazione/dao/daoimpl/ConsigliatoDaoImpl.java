package bookrecommender.elaborazione.dao.daoimpl;

import bookrecommender.elaborazione.dao.ConsigliatoDao;
import bookrecommender.elaborazione.entities.Consigliato;
import bookrecommender.elaborazione.entities.Libro;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
 * Ha lo scopo di prelevare i dati dai file csv e
 * creare oggetti o campi di classe Libreria.
 *
 * @author Angelo Penafiel, Lorenzo
 * @version 1.0
 */

public class ConsigliatoDaoImpl implements ConsigliatoDao {


  //CAMPO

  /**
   * Costante che rappresenta l'header del file
   * Librerie.dati.csv.
   */

  private static final String[] HEADERS = {"UserID","id libro consigliato 1",
      "id libro consigliato 2","id libro consigliato 3"};


  //METODI

  @Override
  public void add(String userId) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter("data/ConsigliLibri.dati.csv", true));
    writer.write("\""+userId+"\",-1,-1,-1");
    writer.close();
  }

  @Override
  public void addLibro(String userId, Libro libro) throws IOException {

    List<Consigliato> consigliati=getAll();

    String [] libriConsigliati = getByUserId(userId).getLibriConsigliati();

    BufferedWriter writer = new BufferedWriter(new FileWriter("data/ConsigliLibri.dati.csv", false));

    for(int i=0;i<HEADERS.length;i++) {
      writer.write(HEADERS[i]);
      if(i+1!= HEADERS.length) {
        writer.write(",");
      }
    }

    writer.write("\n");

    for(Consigliato consigliato:consigliati) {

      writer.write("\""+consigliato.getUserId()+"\",");

      if(userId.equals(consigliato.getUserId())) {

        boolean flag=false;

        for(int i=0;i<libriConsigliati.length;i++) {
          if(!flag&&libriConsigliati[i].equals("-1")) {
            writer.write(libro.getId().toString());
            flag=true;
          }

          else {
            writer.write(consigliato.getLibriConsigliati()[i]);
            writer.write(consigliato.getLibriConsigliati()[i]);
          }

          if(i+1!=consigliato.getLibriConsigliati().length) {
            writer.write(",");
          }
        }

      }

      else {
        for (int i=0;i<consigliato.getLibriConsigliati().length;i++) {

          writer.write(consigliato.getLibriConsigliati()[i]);

          if(i+1!=consigliato.getLibriConsigliati().length) {
            writer.write(",");
          }
        }
      }

      writer.write("\n");
    }

    writer.close();
  }

  @Override
  public List<Consigliato> getAll() throws IOException {

    List<Consigliato> consigliati = new ArrayList<>();

    Reader in = new FileReader("data/ConsigliLibri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    for (CSVRecord record : records) {

      String userId=record.get("UserID");
      String consigliato1=record.get("id libro consigliato 1");
      String consigliato2=record.get("id libro consigliato 2");
      String consigliato3=record.get("id libro consigliato 3");

      String[] consigliatiArray= new String[Consigliato.MAX_LIBRI_CONSIGLIATI];

      consigliatiArray[0]=consigliato1;
      consigliatiArray[1]=consigliato2;
      consigliatiArray[2]=consigliato3;

      consigliati.add(new Consigliato(userId, consigliatiArray));

    }

    return consigliati;
  }

  @Override
  public Consigliato getByUserId(String userId) throws IOException {

    Consigliato consigliato=null;

    Reader in = new FileReader("data/ConsigliLibri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    for (CSVRecord record : records) {

      String userIdC=record.get("UserID");
      String consigliato1=record.get("id libro consigliato 1");
      String consigliato2=record.get("id libro consigliato 2");
      String consigliato3=record.get("id libro consigliato 3");

      if(userIdC.equals(userId)) {
        String[] consigliati= new String[Consigliato.MAX_LIBRI_CONSIGLIATI];

        consigliati[0]=consigliato1;
        consigliati[1]=consigliato2;
        consigliati[2]=consigliato3;

        consigliato=new Consigliato(userIdC, consigliati);
      }

    }

    return consigliato;
  }

  @Override
  public List<Consigliato> getByLibroId(String libroId) throws IOException {

    List<Consigliato> consigliati=new ArrayList<>();

    Reader in = new FileReader("data/ConsigliLibri.dati.csv");

    CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
        .setHeader(HEADERS)
        .setSkipHeaderRecord(true)
        .build();

    List<CSVRecord> records = csvFormat.parse(in).getRecords();

    for (CSVRecord record : records) {

      String userIdC=record.get("UserID");
      String consigliato1=record.get("id libro consigliato 1");
      String consigliato2=record.get("id libro consigliato 2");
      String consigliato3=record.get("id libro consigliato 3");

      if(consigliato1.equals(libroId)||consigliato2.equals(libroId)
          ||consigliato3.equals(libroId)) {

        String[] consigliatiArray= new String[Consigliato.MAX_LIBRI_CONSIGLIATI];

        consigliatiArray[0]=consigliato1;
        consigliatiArray[1]=consigliato2;
        consigliatiArray[2]=consigliato3;

        consigliati.add(new Consigliato(userIdC,consigliatiArray));

      }

    }

    return consigliati;
  }

}
