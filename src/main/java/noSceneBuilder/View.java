package noSceneBuilder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by joni on 25/04/17.
 */
public class View extends Stage {

    private Model model;
    private TableView<Reminder> table;
    private TextField subjectInput, descriptionInput, timeInput, dateInput;

    public View(Model model, Controller controller) {
        this.model = model;

        TableColumn<Reminder, String> subjectColumn = new TableColumn<>("Subject");
        subjectColumn.setMinWidth(200);
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));

        TableColumn<Reminder, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(200);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Reminder, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setMinWidth(200);
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Reminder, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        subjectInput = new TextField();
        subjectInput.setPromptText("Subject");
        subjectInput.setMinWidth(100);

        descriptionInput = new TextField();
        descriptionInput.setPromptText("Description");

        timeInput = new TextField();
        timeInput.setPromptText("Time");

        dateInput = new TextField();
        dateInput.setPromptText("Date");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> controller.addButtonClicked(subjectInput.getText(), descriptionInput.getText(), timeInput.getText(), dateInput.getText()));
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> controller.deleteButtonClicked());

        table = new TableView<>();
        table.setItems(model.getReminders());
        table.getColumns().addAll(subjectColumn, descriptionColumn, timeColumn, dateColumn);


        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(subjectInput, descriptionInput, timeInput, dateInput, addButton, deleteButton);
        VBox pane = new VBox(hBox, table);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("styles.css");
        setScene(scene);
        show();
        model.addObserver(this);
    }

    public TableView<Reminder> getTable() {
        return table;
    }

    public TextField getSubjectInput() {
        return subjectInput;
    }

    public TextField getDescriptionInput() {
        return descriptionInput;
    }

    public TextField getTimeInput() {
        return timeInput;
    }

    public TextField getDateInput() {
        return dateInput;
    }

}
