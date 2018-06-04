package app.notes;

import app.notes.model.Note;
import app.notes.repository.NoteRepository;
import app.notes.util.EntityManagerUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

    private Note viewNote = new Note();

    public void initialize() {

        // columns
        tcDate.setCellValueFactory(new PropertyValueFactory<>("dateEntered"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcTag.setCellValueFactory(new PropertyValueFactory<>("tag"));

        // table view
        notes = FXCollections.observableArrayList();
        tViewNotes.setItems(notes);

        // actions
        bClear.setOnAction(evt -> clearWorkingNote());
        bSave.setOnAction(evt -> saveNote());

        // binding
        viewNote.titleProperty().bindBidirectional(tfTitle.textProperty());
        viewNote.dateEnteredProperty().bindBidirectional(dpDate.valueProperty());
        viewNote.descriptionProperty().bindBidirectional(taDescription.textProperty());
        viewNote.tagProperty().bindBidirectional(tfTag.textProperty());

        tViewNotes.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue.intValue() >= 0) {

                        int ix = newValue.intValue();
                        if ((ix == notes.size())) {
                            return; // invalid data
                        }

                        Note selectedNote = notes.get(ix);
                        viewNote.setId(selectedNote.getId());
                        viewNote.setTitle(selectedNote.getTitle());
                        viewNote.setDateEntered(selectedNote.getDateEntered());
                        viewNote.setDescription(selectedNote.getDescription());
                        viewNote.setTag(selectedNote.getTag());
                    }
                });

        //test
        //EntityManagerUtil.getEntityManager();
    }

    /**
     * Save a new note if it is valid
     */
    private void saveNote() {

        if (isAValidNote()) {

            FilteredList<Note> filteredNotes = notes
                    .filtered(x -> x.idProperty().getValue().equals(viewNote.idProperty().getValue()));

            boolean isANewTitle = filteredNotes.isEmpty();

            if (isANewTitle) {
                Note note = new Note(viewNote.getTitle(), viewNote.getDateEntered(), viewNote.getDescription(),
                        viewNote.getTag());
                NoteRepository repository = new NoteRepository();
                repository.persist(note);
                notes.add(note);
            } else {
                Note currentNote = filteredNotes.get(0);
                currentNote.setTitle(viewNote.getTitle());
                currentNote.setDateEntered(viewNote.getDateEntered());
                currentNote.setDescription(viewNote.getDescription());
                currentNote.setTag(viewNote.getTag());
            }

            clearWorkingNote();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setContentText("The note to be saved has invalid values!");
            alert.showAndWait();
        }
    }

    /**
     * @return true is the current Note is valid
     */
    private boolean isAValidNote() {

        boolean titleIsNotEmpty = viewNote.getTitle() != null && !"".equals(viewNote.getTitle().trim());
        boolean dateIsNotEmpty = dpDate.getValue() != null;

        return titleIsNotEmpty && dateIsNotEmpty;
    }

    private void clearWorkingNote() {
        viewNote.setId(0);
        tfTitle.textProperty().setValue("");
        taDescription.textProperty().setValue("");
        dpDate.setValue(null);
        tfTag.setText("");
        if (tViewNotes.getSelectionModel().selectedIndexProperty().get() >= 0) {
            tViewNotes.getSelectionModel().clearSelection();
        }
    }

}
