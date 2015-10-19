package de.siemering;

import de.siemering.mapping.NeedText;
import de.siemering.mapping.Text;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Locale;

public class App {

    private EntityManager em;

    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hsqldb");
        em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        long needTextId = write();
        em.getTransaction().commit();

        NeedText singleResult = em.createQuery("select n from NeedText n where n.id = :id", NeedText.class).setParameter("id", needTextId).getSingleResult();

        System.out.println("German text:" + singleResult.getText(Locale.GERMAN));
        System.out.println("English text:" + singleResult.getText(Locale.ENGLISH));

        em.close();
        entityManagerFactory.close();
    }


    public long write() {
        //Create first text
        Text text = new Text();
        text.locale = Locale.GERMAN;
        text.text = "Hallo Welt!";
        em.persist(text);

        //Create second text with same id
        Text text2 = new Text();
        text2.id = text.id;
        text2.locale = Locale.ENGLISH;
        text2.text = "Hello world!";
        em.persist(text2);

        //Create NeedText Entity
        NeedText needText = new NeedText();
        needText.textId = text.id;
        em.persist(needText);
        em.refresh(needText);

        //If you use creation db by hibernate, hibernate will create an unwanted uc on needtext(text_id)
        //so this won't be possible....
//        needText = new NeedText();
//        needText.textId = text.id;
//        em.persist(needText);
//        em.refresh(needText);

        return needText.getId();
    }

}
