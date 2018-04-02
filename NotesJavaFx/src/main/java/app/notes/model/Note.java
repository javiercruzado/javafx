package app.notes.model;

import java.time.LocalDate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * An entity that represents some notes that a user want to store
 * 
 * It uses JavaFX properties. See
 * https://gist.github.com/james-d/e485ac525c71e20bb453
 * 
 * @author javier
 */

public class Note {

	private String title;
	private StringProperty titleProperty;

	private LocalDate dateEntered;
	private SimpleObjectProperty<LocalDate> dateEnteredProperty;

	private String description;
	private StringProperty descriptionProperty;

	public Note() {
	}

	public Note(String title, LocalDate localDateTime, String description) {
		this.titleProperty = new SimpleStringProperty(title);
		this.dateEnteredProperty = new SimpleObjectProperty<>(localDateTime);
		this.descriptionProperty = new SimpleStringProperty(description);
	}

	// title
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
			dateEnteredProperty = new SimpleObjectProperty<LocalDate>(this, "dateEntered", dateEntered);
		}
		return dateEnteredProperty;
	}

	// description
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StringProperty descriptionProperty() {
		if (descriptionProperty == null) {
			descriptionProperty = new SimpleStringProperty(this, "description", description);
		}
		return descriptionProperty;
	}
}
