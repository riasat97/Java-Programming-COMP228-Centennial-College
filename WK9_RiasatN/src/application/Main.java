package application;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * - COMP228 - Java Programming
 * - Lab Assignment: Hospital Management System (JavaFX UI & Event Handling)
 * - Student Name: Riasat Raihan Noor
 * - Student ID: 301554207
 * 
 * Screen Layout Architecture (Following Lab Instructions):
 * - Root Container: BorderPane
 * - Top Region: VBox containing the application title and subtitle.
 * - Center Region: HBox splitting the screen into:
 *     1) Left side: Patient Details Form (GridPane + Action Buttons HBox).
 *     2) Right side: Patient Records Area (Search/Filter HBox + TableView).
 * - Bottom Region: HBox containing the status message Label for success/error feedback.
 */
public class Main extends Application {

    // Instance of our DAO class to communicate with the MySQL database
    private final PatientDAO patientDAO = new PatientDAO();

    // Form input controls (declared as class fields so helper methods can read/clear them)
    private final TextField idField = new TextField();
    private final TextField nameField = new TextField();
    private final TextField ageField = new TextField();
    private final ComboBox<String> genderBox = new ComboBox<>();
    private final TextField phoneField = new TextField();
    private final TextField diagnosisField = new TextField();
    private final ComboBox<String> departmentBox = new ComboBox<>();
    private final ComboBox<String> statusBox = new ComboBox<>();
    private final DatePicker admissionDatePicker = new DatePicker();

    // Search bar, Date filter, and TableView controls
    private final TextField searchField = new TextField();
    private final DatePicker filterDatePicker = new DatePicker();
    private final TableView<Patient> patientTable = new TableView<>();

    // ObservableList automatically updates the TableView whenever items are added or removed
    private final ObservableList<Patient> patientList = FXCollections.observableArrayList();

    // Status label displayed at the bottom of the window
    private final Label statusLabel = new Label("Ready. Enter patient details or select a record.");

    @Override
    public void start(Stage stage) {

        // =====================================================================
        // 1. TOP REGION: VBox with Application Title and Subtitle
        // =====================================================================
        Label heading = new Label("Hospital Management System");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Label subtitle = new Label("Patient Registration, Department & Clinical Status Module");

        VBox header = new VBox(5, heading, subtitle);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: #dceeff;");

        // =====================================================================
        // 2. CENTER REGION: HBox with Left Form and Right Records TableView
        // =====================================================================
        VBox leftFormPane = buildFormPane();
        VBox rightRecordsPane = buildRecordsPane();
        // Allow the right table section to expand horizontally when the window is resized
        HBox.setHgrow(rightRecordsPane, Priority.ALWAYS);

        HBox centerContent = new HBox(15, leftFormPane, rightRecordsPane);
        centerContent.setPadding(new Insets(15));

        // =====================================================================
        // 3. BOTTOM REGION: Status Message Bar
        // =====================================================================
        HBox bottomBar = new HBox(statusLabel);
        bottomBar.setPadding(new Insets(10, 20, 10, 20));
        bottomBar.setStyle("-fx-background-color: #f1f3f5;");

        // =====================================================================
        // 4. BORDERPANE ROOT: Combine Top, Center, and Bottom into Scene
        // =====================================================================
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(centerContent);
        root.setBottom(bottomBar);

        stage.setTitle("Hospital Management System - Patient CRUD");
        stage.setScene(new Scene(root, 1220, 660));
        stage.show();

        // Load existing patient records from MySQL as soon as the app opens
        loadPatients();
    }

    /**
     * Builds the Left Pane containing the GridPane form and CRUD action buttons.
     */
    private VBox buildFormPane() {
        Label formTitle = new Label("Patient Details");
        formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Patient ID is auto-generated by MySQL (AUTO_INCREMENT), so make it read-only
        idField.setPromptText("Auto-generated");
        idField.setEditable(false);
        idField.setDisable(true);

        // Configure prompt text and dropdown options for each input control
        nameField.setPromptText("Full name");
        ageField.setPromptText("Age (0 - 125)");

        genderBox.getItems().addAll("Female", "Male", "Non-binary", "Prefer not to say");
        genderBox.setPromptText("Select gender");

        phoneField.setPromptText("e.g., 416-555-0199");
        diagnosisField.setPromptText("Diagnosis / reason for visit");

        // Optional Extension: Department ComboBox
        departmentBox.getItems().addAll(
                "General Medicine", "Cardiology", "Neurology",
                "Orthopedics", "Pediatrics", "Emergency");
        departmentBox.setPromptText("Select department");

        // Optional Extension: Patient Status ComboBox
        statusBox.getItems().addAll("Admitted", "Under Observation", "Discharged");
        statusBox.setValue("Admitted");

        // Default admission date to today's date
        admissionDatePicker.setValue(LocalDate.now());

        // GridPane aligns form labels (column 0) and input controls (column 1)
        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(12);
        form.setPadding(new Insets(8, 0, 8, 0));

        addRow(form, 0, "Patient ID:", idField);
        addRow(form, 1, "Full Name:", nameField);
        addRow(form, 2, "Age:", ageField);
        addRow(form, 3, "Gender:", genderBox);
        addRow(form, 4, "Phone:", phoneField);
        addRow(form, 5, "Diagnosis:", diagnosisField);
        addRow(form, 6, "Department:", departmentBox);
        addRow(form, 7, "Patient Status:", statusBox);
        addRow(form, 8, "Admission Date:", admissionDatePicker);

        // Set fixed width for label column and allow input field column to grow
        ColumnConstraints labels = new ColumnConstraints(120);
        ColumnConstraints fields = new ColumnConstraints(230);
        fields.setHgrow(Priority.ALWAYS);
        form.getColumnConstraints().addAll(labels, fields);

        // Row 1 Buttons: Primary CRUD operations using lambda event handlers
        Button addButton = new Button("Add Patient");
        addButton.setDefaultButton(true);
        addButton.setOnAction(e -> handleAdd());

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> handleUpdate());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> handleDelete());

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearForm());

        HBox crudButtons = new HBox(8, addButton, updateButton, deleteButton, clearButton);
        crudButtons.setAlignment(Pos.CENTER_LEFT);

        // Row 2 Buttons: Optional Extensions (Patient Details popup & Quick Discharge)
        Button viewDetailsButton = new Button("View Details");
        viewDetailsButton.setOnAction(e -> handleViewDetails());

        Button dischargeButton = new Button("Discharge Patient");
        dischargeButton.setOnAction(e -> handleDischarge());

        HBox extensionButtons = new HBox(8, viewDetailsButton, dischargeButton);
        extensionButtons.setAlignment(Pos.CENTER_LEFT);

        VBox formPane = new VBox(10, formTitle, form, crudButtons, extensionButtons);
        formPane.setPadding(new Insets(15));
        formPane.setPrefWidth(400);
        formPane.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: #dee2e6;");
        return formPane;
    }

    /**
     * Builds the Right Pane containing the Search Bar, Date Filter, and TableView.
     */
    @SuppressWarnings("unchecked")
    private VBox buildRecordsPane() {
        Label tableTitle = new Label("Patient Records");
        tableTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Search controls
        searchField.setPromptText("Name or phone...");
        searchField.setPrefWidth(170);

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> handleSearch());

        // Optional Extension: Filter by Admission Date controls
        filterDatePicker.setPromptText("Admission date");
        filterDatePicker.setPrefWidth(135);

        Button filterDateButton = new Button("Filter Date");
        filterDateButton.setOnAction(e -> handleFilterByDate());

        // Show All resets search/date filters and reloads all database rows
        Button showAllButton = new Button("Show All");
        showAllButton.setOnAction(e -> {
            searchField.clear();
            filterDatePicker.setValue(null);
            loadPatients();
        });

        HBox searchBar = new HBox(8,
                new Label("Search:"), searchField, searchButton,
                new Label("Date:"), filterDatePicker, filterDateButton,
                showAllButton);
        searchBar.setAlignment(Pos.CENTER_LEFT);

        // Define TableView columns and bind each column to the corresponding getter in Patient.java
        TableColumn<Patient, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getPatientId()).asObject());
        idCol.setPrefWidth(45);

        TableColumn<Patient, String> nameCol = new TableColumn<>("Full Name");
        nameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        nameCol.setPrefWidth(125);

        TableColumn<Patient, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getAge()).asObject());
        ageCol.setPrefWidth(45);

        TableColumn<Patient, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getGender()));
        genderCol.setPrefWidth(85);

        TableColumn<Patient, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getPhone()));
        phoneCol.setPrefWidth(105);

        TableColumn<Patient, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDepartment()));
        deptCol.setPrefWidth(120);

        TableColumn<Patient, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getStatus()));
        statusCol.setPrefWidth(115);

        TableColumn<Patient, String> diagCol = new TableColumn<>("Diagnosis");
        diagCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDiagnosis()));
        diagCol.setPrefWidth(140);

        TableColumn<Patient, LocalDate> dateCol = new TableColumn<>("Admitted");
        dateCol.setCellValueFactory(d -> new SimpleObjectProperty<>(d.getValue().getAdmissionDate()));
        dateCol.setPrefWidth(95);

        patientTable.getColumns().addAll(
                idCol, nameCol, ageCol, genderCol, phoneCol, deptCol, statusCol, diagCol, dateCol);
        patientTable.setItems(patientList);
        VBox.setVgrow(patientTable, Priority.ALWAYS);

        // Listener: When a user clicks a row in the TableView, copy its data into the left form
        patientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, selected) -> {
            if (selected != null) {
                populateForm(selected);
            }
        });

        VBox recordsPane = new VBox(12, tableTitle, searchBar, patientTable);
        recordsPane.setPadding(new Insets(15));
        return recordsPane;
    }

    // Helper method (from FXWithJDBCExample) to neatly add a label + control row to GridPane
    private void addRow(GridPane grid, int row, String text, Control control) {
        Label label = new Label(text);
        label.setStyle("-fx-font-weight: bold;");
        control.setMaxWidth(Double.MAX_VALUE);
        grid.add(label, 0, row);
        grid.add(control, 1, row);
    }

    // =========================================================================
    // INPUT VALIDATION METHOD
    // Checks all required fields, age range (0-125), phone format, and date
    // =========================================================================
    private Patient validateAndBuildPatient(int id) {
        String name = nameField.getText().trim();
        String ageVal = ageField.getText().trim();
        String gender = genderBox.getValue();
        String phone = phoneField.getText().trim();
        String diagnosis = diagnosisField.getText().trim();
        String department = departmentBox.getValue();
        String status = statusBox.getValue();
        LocalDate admDate = admissionDatePicker.getValue();

        // 1. Check that no required field is left blank
        if (name.isEmpty() || ageVal.isEmpty() || gender == null || phone.isEmpty()
                || diagnosis.isEmpty() || department == null || status == null || admDate == null) {
            setStatus("Please complete all required fields.", true);
            return null;
        }

        // 2. Check that Age is a valid whole number between 0 and 125
        int age;
        try {
            age = Integer.parseInt(ageVal);
            if (age < 0 || age > 125) {
                setStatus("Age must be between 0 and 125.", true);
                return null;
            }
        } catch (NumberFormatException ex) {
            setStatus("Age must be a whole number.", true);
            return null;
        }

        // 3. Basic format check for Phone number (digits, spaces, dashes, parentheses)
        if (!phone.matches("^[0-9+()\\-\\s]{7,20}$")) {
            setStatus("Enter a valid phone number (7-20 digits).", true);
            return null;
        }

        // 4. Admission Date cannot be in the future
        if (admDate.isAfter(LocalDate.now())) {
            setStatus("Admission date cannot be in the future.", true);
            return null;
        }

        // All checks passed: return a new Patient object ready for the DAO
        return new Patient(id, name, age, gender, phone, diagnosis, department, status, admDate);
    }

    // =========================================================================
    // BUTTON ACTION HANDLERS (Connecting UI Events to PatientDAO)
    // =========================================================================

    // CREATE handler
    private void handleAdd() {
        Patient patient = validateAndBuildPatient(0);
        if (patient == null) return; // Stop if validation failed

        try {
            if (patientDAO.insertPatient(patient)) {
                loadPatients();
                clearFields();
                setStatus("Patient added successfully!", false);
            }
        } catch (SQLException ex) {
            setStatus("Database error: " + ex.getMessage(), true);
        }
    }

    // READ handler
    private void loadPatients() {
        try {
            patientList.setAll(patientDAO.getAllPatients());
            setStatus("Loaded " + patientList.size() + " patient record(s).", false);
        } catch (SQLException ex) {
            setStatus("Database error: " + ex.getMessage(), true);
        }
    }

    // UPDATE handler
    private void handleUpdate() {
        if (idField.getText().isEmpty()) {
            setStatus("Select a patient from the table to update.", true);
            return;
        }
        Patient patient = validateAndBuildPatient(Integer.parseInt(idField.getText()));
        if (patient == null) return;

        try {
            if (patientDAO.updatePatient(patient)) {
                loadPatients();
                clearFields();
                setStatus("Patient updated successfully!", false);
            }
        } catch (SQLException ex) {
            setStatus("Database error: " + ex.getMessage(), true);
        }
    }

    // DELETE handler (with Confirmation Dialog)
    private void handleDelete() {
        if (idField.getText().isEmpty()) {
            setStatus("Select a patient from the table to delete.", true);
            return;
        }
        int id = Integer.parseInt(idField.getText());

        // Ask the user to confirm before permanently deleting the record
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete patient " + nameField.getText() + " (ID: " + id + ")?",
                ButtonType.OK, ButtonType.CANCEL);
        Optional<ButtonType> res = confirm.showAndWait();

        if (res.isPresent() && res.get() == ButtonType.OK) {
            try {
                if (patientDAO.deletePatient(id)) {
                    loadPatients();
                    clearFields();
                    setStatus("Patient deleted successfully!", false);
                }
            } catch (SQLException ex) {
                setStatus("Database error: " + ex.getMessage(), true);
            }
        }
    }

    // SEARCH handler
    private void handleSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            loadPatients();
            return;
        }
        try {
            patientList.setAll(patientDAO.searchPatients(keyword));
            setStatus("Search found " + patientList.size() + " matching record(s).", false);
        } catch (SQLException ex) {
            setStatus("Database error: " + ex.getMessage(), true);
        }
    }

    // OPTIONAL EXTENSION: Filter by Admission Date handler
    private void handleFilterByDate() {
        LocalDate filterDate = filterDatePicker.getValue();
        if (filterDate == null) {
            setStatus("Please choose a date in the Date filter first.", true);
            return;
        }
        try {
            patientList.setAll(patientDAO.filterByAdmissionDate(filterDate));
            setStatus("Found " + patientList.size() + " patient(s) admitted on " + filterDate + ".", false);
        } catch (SQLException ex) {
            setStatus("Database error: " + ex.getMessage(), true);
        }
    }

    // OPTIONAL EXTENSION: Quick Discharge handler
    private void handleDischarge() {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            setStatus("Select a patient from the table to discharge.", true);
            return;
        }
        try {
            if (patientDAO.dischargePatient(selected.getPatientId())) {
                loadPatients();
                statusBox.setValue("Discharged");
                setStatus("Patient " + selected.getName() + " marked as Discharged.", false);
            }
        } catch (SQLException ex) {
            setStatus("Database error: " + ex.getMessage(), true);
        }
    }

    // OPTIONAL EXTENSION: View Patient Details Popup Dialog
    private void handleViewDetails() {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            setStatus("Select a patient from the table to view details.", true);
            return;
        }
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Patient Clinical Summary");
        info.setHeaderText("Patient Record #" + selected.getPatientId() + " — " + selected.getName());
        info.setContentText(
                "Age: " + selected.getAge() + " (" + selected.getGender() + ")\n" +
                "Phone: " + selected.getPhone() + "\n" +
                "Department: " + selected.getDepartment() + "\n" +
                "Clinical Status: " + selected.getStatus() + "\n" +
                "Diagnosis: " + selected.getDiagnosis() + "\n" +
                "Admission Date: " + selected.getAdmissionDate() + "\n" +
                "Record Created: " + selected.getCreatedAt()
        );
        info.showAndWait();
    }

    // Copies the selected TableView row's values into the left-side form fields
    private void populateForm(Patient p) {
        idField.setText(String.valueOf(p.getPatientId()));
        nameField.setText(p.getName());
        ageField.setText(String.valueOf(p.getAge()));
        genderBox.setValue(p.getGender());
        phoneField.setText(p.getPhone());
        diagnosisField.setText(p.getDiagnosis());
        departmentBox.setValue(p.getDepartment());
        statusBox.setValue(p.getStatus());
        admissionDatePicker.setValue(p.getAdmissionDate());
        setStatus("Selected Patient ID: " + p.getPatientId(), false);
    }

    // Resets the form and updates the status message
    private void clearForm() {
        clearFields();
        setStatus("Form cleared.", false);
    }

    // Clears all input controls and table selection back to their default state
    private void clearFields() {
        idField.clear();
        nameField.clear();
        ageField.clear();
        genderBox.getSelectionModel().clearSelection();
        phoneField.clear();
        diagnosisField.clear();
        departmentBox.getSelectionModel().clearSelection();
        statusBox.setValue("Admitted");
        admissionDatePicker.setValue(LocalDate.now());
        patientTable.getSelectionModel().clearSelection();
    }

    // Displays status feedback in red for errors (#b00020) or green for success (#167545)
    private void setStatus(String message, boolean error) {
        statusLabel.setText(message);
        statusLabel.setStyle(error ? "-fx-text-fill: #b00020; -fx-font-weight: bold;"
                                   : "-fx-text-fill: #167545; -fx-font-weight: bold;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}