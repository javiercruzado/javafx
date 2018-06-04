package app.notes.model;

import java.time.LocalDate;

import javafx.beans.property.*;

import javax.persistence.*;

/**
 * An entity that represents some notes that a user want to store
 * <p>
 * It uses JavaFX properties. See
 * https://gist.github.com/james-d/e485ac525c71e20bb453
 *
 * @author javier
 */

@Entity
@Access(AccessType.PROPERTY)
public class Note {

    private int id;
    private IntegerProperty idProperty;

    private String title;
    private StringProperty titleProperty;

    private LocalDate dateEntered;
    private SimpleObjectProperty<LocalDate> dateEnteredProperty;

    private String description;
    private StringProperty descriptionProperty;

    private String tag;
    private StringProperty tagProperty;

    public Note() {
    }

    public Note(String title, LocalDate localDateTime, String description, String tag) {
        this.titleProperty = new SimpleStringProperty(title);
        this.dateEnteredProperty = new SimpleObjectProperty<>(localDateTime);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.tagProperty = new SimpleStringProperty(tag);
    }

    //id
    @Id
    @Column(name = "noteId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        if (idProperty == null) {
            return id;
        } else {
            return idProperty.get();
        }
    }

    public void setId(int id) {
        if (this.idProperty == null) {
            this.id = id;
        } else {
            this.idProperty.set(id);
        }
    }

    public IntegerProperty idProperty() {
        if (idProperty == null) {
            idProperty = new SimpleIntegerProperty(this, "id", id);
        }
        return idProperty;
    }

    // title
    @Column(name = "title")
    public String getTitle() {
        if (titleProperty == null) {
            return title;
        } else {
            return titleProperty.getValue();
        }
    }

    public void setTitle(String title) {
        if (this.titleProperty == null) {
            this.title = title;
        } else {
            this.titleProperty.set(title);
        }
    }

    public StringProperty titleProperty() {
        if (titleProperty == null) {
            titleProperty = new SimpleStringProperty(this, "title", title);
        }
        return titleProperty;
    }

    // date
    @Column(name = "dateEntered")
    public LocalDate getDateEntered() {
        if (dateEnteredProperty == null) {
            return dateEntered;
        } else {
            return dateEnteredProperty.get();
        }
    }

    public void setDateEntered(LocalDate dateEntered) {
        if (dateEnteredProperty == null) {
            this.dateEntered = dateEntered;
        } else {
            dateEnteredProperty.set(dateEntered);
        }
    }

    public SimpleObjectProperty<LocalDate> dateEnteredProperty() {
        if (dateEnteredProperty == null) {
            dateEnteredProperty = new SimpleObjectProperty<>(this, "dateEntered", dateEntered);
        }
        return dateEnteredProperty;
    }

    // description
    @Column(name = "description")
    public String getDescription() {
        if (descriptionProperty == null) {
            return description;
        } else {
            return descriptionProperty.getValue();
        }
    }

    public void setDescription(String description) {
        if (this.descriptionProperty == null) {
            this.description = description;
        } else {
            this.descriptionProperty.set(description);
        }
    }

    public StringProperty descriptionProperty() {
        if (descriptionProperty == null) {
            descriptionProperty = new SimpleStringProperty(this, "description", description);
        }
        return descriptionProperty;
    }

    // tag
    @Column(name = "tag")
    public String getTag() {
        if (tagProperty == null) {
            return tag;
        } else {
            return tagProperty.getValue();
        }
    }

    public void setTag(String tag) {
        if (this.tagProperty == null) {
            this.tag = tag;
        } else {
            this.tagProperty.set(tag);
        }
    }

    public StringProperty tagProperty() {
        if (tagProperty == null) {
            tagProperty = new SimpleStringProperty(this, "tag", tag);
        }
        return tagProperty;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Note other = (Note) obj;
        if (title == null) {
            return other.title == null;
        } else return title.equals(other.title);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Note [title=" + getTitle() + "]";
    }


}
