package app.notes;

import app.notes.model.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class NotesAppController {

	@FXML
	Button bClear;

	@FXML
	Button bSave;

	@FXML
	TextField tfTitle;

	@FXML
	TextArea taDescription;

	@FXML
	DatePicker dpDate;

	@FXML
	TextField tfTag;

	@FXML
	TableView<Note> tViewNotes;

	@FXML
	TableColumn<Note, String> tcDate;

	@FXML
	TableColumn<Note, String> tcTitle;

	@FXML
	TableColumn<Note, String> tcTag;

	//
	private ObservableList<Note> notes;

	private Note workingNote = new Note();

	public void initialize() {

		// columns
		tcDate.setCellValueFactory(new PropertyValueFactory<Note, String>("dateEntered"));
		tcTitle.setCellValueFactory(new PropertyValueFactory<Note, String>("title"));
		tcTag.setCellValueFactory(new PropertyValueFactory<Note, String>("tag"));

		// table view
		notes = FXCollections.observableArrayList();
		tViewNotes.setItems(notes);

		// actions
		bClear.setOnAction(evt -> clearWorkinkNote());
		bSave.setOnAction(evt -> saveNote());

		// binding
		workingNote.titleProperty().bindBidirectional(tfTitle.textProperty());
		workingNote.dateEnteredProperty().bindBidirectional(dpDate.valueProperty());
		workingNote.descriptionProperty().bindBidirectional(taDescription.textProperty());
		workingNote.tagProperty().bindBidirectional(tfTag.textProperty());

		//
		tViewNotes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tViewNotes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			//System.out.println(newSelection == null ? "null" : newSelection);
			if (newSelection != null) {
				workingNote.setDateEntered(newSelection.getDateEntered());
				workingNote.setDescription(newSelection.getDescription());
				workingNote.setTitle(newSelection.getTitle());
				workingNote.setTag(newSelection.getTag());
			}
		});
	}

	/**
	 * Save a new note if it is valid
	 */
	private void saveNote() {
		if (isAValidNote()) {
			Note note = new Note(workingNote.getTitle(), workingNote.getDateEntered(), workingNote.getDescription(),
					workingNote.getTag());			
			notes.add(note);
			clearWorkinkNote();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Validation Error");
			alert.setContentText("The note to be saved has invalid values!");
			alert.showAndWait();
		}
	}

	/**
	 * 
	 * @return true is the current Note is valid
	 */
	private boolean isAValidNote() {

		boolean titleIsNotEmpty = workingNote.getTitle() != null && !"".equals(workingNote.getTitle().trim());
		boolean dateIsNotEmpty = dpDate.getValue() != null;
		boolean isANewTitle = notes
				.filtered(x -> x.titleProperty().getValue() == workingNote.titleProperty().getValue()).isEmpty();

		return titleIsNotEmpty && dateIsNotEmpty && isANewTitle;
	}

	private void clearWorkinkNote() {
		tfTitle.textProperty().setValue("");
		taDescription.textProperty().setValue("");
		dpDate.setValue(null);
		tfTag.setText("");
	}

}
