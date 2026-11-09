package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * - COMP228 
 * - Week 8 Lab: JavaFX Layout Panes
 * - Student Registration Form using BorderPane, GridPane, HBox, and VBox.
 * 
 * @author Riasat Raihan Noor
 */

/*
 * Use BorderPane as the root layout.
 * Place an HBox at the top for the application title.
 * Place a VBox on the left with Dashboard, Registration, Courses, and Help buttons.
 * Place a VBox in the centre for the registration form and action buttons.
 * Use GridPane for Student ID, Full Name, Email, and Program fields.
 * Use an HBox for Register and Clear buttons.
 */

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // 1. TOP: HBox places the header items side by side.
        Label titleLabel = new Label("College Student Services");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label subtitleLabel = new Label("| JavaFX Layout Lab");
        subtitleLabel.setStyle("-fx-font-size: 14px;");

        HBox header = new HBox(10, titleLabel, subtitleLabel);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #dceeff;");

        // 2. LEFT: VBox stacks navigation buttons vertically.
        Label menuTitle = new Label("MENU");
        menuTitle.setStyle("-fx-font-weight: bold;");
        Button dashboardButton = new Button("Dashboard");
        Button registrationButton = new Button("Registration");
        Button coursesButton = new Button("Courses");
        Button helpButton = new Button("Help");

        VBox navigation = new VBox(12,
                menuTitle, dashboardButton, registrationButton,
                coursesButton, helpButton);
        navigation.setPadding(new Insets(15));
        navigation.setPrefWidth(160);
        navigation.setStyle("-fx-background-color: #f1f3f5;");

        // 3. CENTER: GridPane aligns labels and input controls in rows.
        Label formTitle = new Label("Student Registration");
        formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField idField = new TextField();
        idField.setPromptText("Enter student ID");
        idField.setPrefWidth(260);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter full name");
        idField.setPrefWidth(260);
        nameField.setPrefWidth(260);

        TextField emailField = new TextField();
        emailField.setPromptText("Enter college email");
        emailField.setPrefWidth(260);

        ComboBox<String> programBox = new ComboBox<>();
        programBox.getItems().addAll(
                "Computer Programming",
                "Software Engineering Technology",
                "Computer Systems Technician",
                "Cybersecurity"
        );
        programBox.setPromptText("Select a program");
        programBox.setPrefWidth(260);

        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(14);

        form.add(new Label("Student ID:"), 0, 0);
        form.add(idField, 1, 0);
        form.add(new Label("Full Name:"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Email:"), 0, 2);
        form.add(emailField, 1, 2);
        form.add(new Label("Program:"), 0, 3);
        form.add(programBox, 1, 3);

        // HBox for Register and Clear buttons
        Button registerButton = new Button("Register");
        Button clearButton = new Button("Clear");

        HBox actions = new HBox(10, registerButton, clearButton);
        actions.setAlignment(Pos.CENTER_LEFT);
        actions.setPadding(new Insets(10, 0, 0, 0));

        Label statusLabel = new Label("Complete the form and click Register.");

        // VBox combines form title, grid, buttons, and status label
        VBox centerContent = new VBox(15, formTitle, form, actions, statusLabel);
        centerContent.setPadding(new Insets(20));

        // 4. BUTTON EVENTS: Validate input on Register and reset form on Clear.
        registerButton.setOnAction(event -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String program = programBox.getValue();

            if (id.isEmpty() || name.isEmpty() || email.isEmpty() || program == null) {
                statusLabel.setText("Please complete all required fields.");
            } else {
                statusLabel.setText("Registration accepted for " + name + " (" + program + ").");
            }
        });

        clearButton.setOnAction(event -> {
            idField.clear();
            nameField.clear();
            emailField.clear();
            programBox.getSelectionModel().clearSelection();
            statusLabel.setText("Form cleared.");
        });

        // 5. BORDERPANE combines the regions into one application window.
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setLeft(navigation);
        root.setCenter(centerContent);
        root.setStyle("-fx-background-color: white;");

        BorderPane.setMargin(centerContent, new Insets(5));

        Scene scene = new Scene(root, 850, 520);
        stage.setTitle("JavaFX Layout Managers Lab");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}