package de.siemering.mapping;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Locale;

/**
 * <h1>Nutzung in anderen Entitäten</h1>
 * <p>Texte können als Map<Locale,Text> in andere Entitäten eingebunden werden.</p>
 *
 * <p>
 *     {@literal @}OneToMany <br>
 *     {@literal @}MapKey(name = "lang")<br>
 *     {@literal @}JoinColumn(name = "ID", referencedColumnName = "FRAGE_TEXT_ID", insertable = false, updatable = false)<br>
 *     private Map<Locale, Text> questionText = new HashMap<Locale, Text>();
 *
 *     {@literal @}Column(name = "FRAGE_TEXT_ID")<br>
 *     private Long questionTextId;
 * </p>
 *
 * <p>
 *     Diese Maps sind nur zum Lesen der Texte. Die Map wird durch das Laden bzw durch ein Refresh gefüllt.
 *     Um die zugehörigen Texte zu ändern, muss die entsprechende ID geändert, die Entität gespeichert und anschließend neu geladen werden.<br>
 * </p>
 */
@Entity
@IdClass(TextPK.class)
@Table(name = "TEXT", uniqueConstraints = @UniqueConstraint(name = "", columnNames ={"ID","LOCALE"}))
public class Text{

    @Id
    @GenericGenerator(name = "text_gen", strategy = "de.siemering.mapping.TextIdGenerator")
    @GeneratedValue(generator = "text_gen")
    public Long id;

    @Id
    @Column(name = "LOCALE")
    public Locale locale;

    @Column
    public String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text = (Text) o;

        if (!id.equals(text.id)) return false;
        return locale.equals(text.locale);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + locale.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return text;
    }
}
