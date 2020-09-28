package at.spg.model;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.activation.MimeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Attachment extends Element{

    public Attachment() {
    }

    public Attachment(MimeType contentType, String language, String url, int size, String title, LocalDate creation) {
        this.contentType = contentType;
        this.language = language;
        this.url = url;
        this.size = size;
        this.title = title;
        this.creation = creation;
    }

    @Column(name="at_contentType")
    private MimeType contentType;
    @Column(name="at_language")
    private String language;

    //private Base64 date;??
    @Column(name="at_url")
    private String url;
    @Column(name="at_size")
    private int size;

    //private Base64 hash;??
    @Column(name="at_title")
    private String title;
    @Column(name="at_creation")
    private LocalDate creation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return size == that.size &&
                Objects.equals(contentType, that.contentType) &&
                Objects.equals(language, that.language) &&
                Objects.equals(url, that.url) &&
                Objects.equals(title, that.title) &&
                Objects.equals(creation, that.creation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentType, language, url, size, title, creation);
    }
}
