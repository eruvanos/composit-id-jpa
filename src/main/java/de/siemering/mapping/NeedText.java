package de.siemering.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Entity
@Table
public class NeedText implements Serializable {

    private static final long serialVersionUID = 1157303554067461839L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10)
    private Long id;

    @OneToMany
    @MapKey(name = "locale")
    @JoinColumn(name = "ID", referencedColumnName = "TEXT_ID", insertable = false, updatable = false )
    private Map<Locale, Text> questionText = new HashMap<Locale, Text>();

    @Column(name = "TEXT_ID")
    public Long textId;

    public Text getText(Locale locale) {
        return questionText != null ? questionText.get(locale) : null;
    }

    public long getId() {
        return id;
    }

}
