package de.siemering.mapping;

import java.io.Serializable;
import java.util.Locale;


public class TextPK implements Serializable {

    private static final long serialVersionUID = 6980272199083441752L;

    private Long id;

    private Locale locale;

    public TextPK() {
    }

    public TextPK(Long id, Locale locale) {
        this.id = id;
        this.locale = locale;
    }

    public Long getId() {
        return id;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextPK textPK = (TextPK) o;

        if (!id.equals(textPK.id)) return false;
        return locale.equals(textPK.locale);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + locale.hashCode();
        return result;
    }
}
