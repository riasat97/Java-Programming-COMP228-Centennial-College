package application;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * - COMP228 - Week 10 In-Class Lab (5%)
 * - Student Name: Riasat Raihan Noor
 * - Purpose: JavaFX Employment Application form matching the worksheet design
 *   and saving records to MySQL using PreparedStatement via EmploymentFormDao.
 */
public class Main extends Application {

    private static final String INPUT_BORDER_STYLE =
            "-fx-border-color: #333333; -fx-border-width: 1; "
            + "-fx-background-color: white; -fx-border-radius: 0; -fx-background-radius: 0;";

    @Override
    public void start(Stage primaryStage) {

        EmploymentFormDao formDao = new EmploymentFormDao();

        primaryStage.setTitle("Employment Application - Riasat Raihan Noor");

        // =====================================================================
        // 1. TOP HEADER: Custom Company Logo on Left + Black Banner on Right
        // =====================================================================
        Label starLogo = new Label("✶");
        starLogo.setFont(Font.font("Segoe UI Symbol", FontWeight.BOLD, 36));
        starLogo.setMinHeight(Region.USE_PREF_SIZE);

        Label companyTitle = new Label("Riasat");
        companyTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        Label companySub = new Label("Company");
        companySub.setFont(Font.font("Tahoma", FontWeight.NORMAL, 13));
        VBox logoText = new VBox(0, companyTitle, companySub);
        logoText.setAlignment(Pos.CENTER_LEFT);

        HBox logoBox = new HBox(10, starLogo, logoText);
        logoBox.setAlignment(Pos.CENTER_LEFT);
        logoBox.setPadding(new Insets(12, 25, 12, 25));
        logoBox.setPrefWidth(210);

        Label bannerTitle = new Label("Employment Application");
        bannerTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
        bannerTitle.setStyle("-fx-text-fill: white;");

        HBox blackBanner = new HBox(bannerTitle);
        blackBanner.setAlignment(Pos.CENTER);
        blackBanner.setStyle("-fx-background-color: #0b0b0b;");
        blackBanner.setPadding(new Insets(20, 30, 20, 30));
        HBox.setHgrow(blackBanner, Priority.ALWAYS);

        HBox topHeader = new HBox(0, logoBox, blackBanner);
        topHeader.setAlignment(Pos.CENTER_LEFT);

        // =====================================================================
        // 2. SECTION 1: PERSONAL INFORMATION
        // =====================================================================
        Label personalHeader = createSectionBanner("Personal Information");

        // Top 2 full-width rows (Full Name, Current Address)
        GridPane personalTopGrid = new GridPane();
        personalTopGrid.setHgap(12);
        personalTopGrid.setVgap(12);
        ColumnConstraints wideLabelCol = new ColumnConstraints(175);
        ColumnConstraints wideFieldCol = new ColumnConstraints();
        wideFieldCol.setHgrow(Priority.ALWAYS);
        personalTopGrid.getColumnConstraints().addAll(wideLabelCol, wideFieldCol);

        TextField fullNameField = createStyledTextField("");
        personalTopGrid.add(createFormLabel("Full Name"), 0, 0);
        personalTopGrid.add(fullNameField, 1, 0);

        TextField addressField = createStyledTextField("");
        personalTopGrid.add(createFormLabel("Current Address"), 0, 1);
        personalTopGrid.add(addressField, 1, 1);

        // Bottom 2 split rows (Contact Number / Email Address & Education / Gender)
        GridPane personalSplitGrid = new GridPane();
        personalSplitGrid.setHgap(12);
        personalSplitGrid.setVgap(14);
        ColumnConstraints splitLabel1 = new ColumnConstraints(95);
        ColumnConstraints splitField1 = new ColumnConstraints(185);
        ColumnConstraints splitLabel2 = new ColumnConstraints(75);
        ColumnConstraints splitField2 = new ColumnConstraints();
        splitField2.setHgrow(Priority.ALWAYS);
        personalSplitGrid.getColumnConstraints().addAll(splitLabel1, splitField1, splitLabel2, splitField2);

        TextField contactField = createStyledTextField("");
        personalSplitGrid.add(createFormLabel("Contact\nNumber"), 0, 0);
        personalSplitGrid.add(contactField, 1, 0);

        TextField emailField = createStyledTextField("");
        personalSplitGrid.add(createFormLabel("Email\nAddress"), 2, 0);
        personalSplitGrid.add(emailField, 3, 0);

        TextField educationField = createStyledTextField("");
        personalSplitGrid.add(createFormLabel("Highest\nEducational\nAttainment"), 0, 1);
        personalSplitGrid.add(educationField, 1, 1);

        Label genderLabel = createFormLabel("Gender");
        RadioButton maleRadio = new RadioButton("Male");
        RadioButton femaleRadio = new RadioButton("Female");
        RadioButton otherRadio = new RadioButton("Other");

        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        otherRadio.setToggleGroup(genderGroup);
        maleRadio.setSelected(true);

        HBox genderRadios = new HBox(14, maleRadio, femaleRadio, otherRadio);
        genderRadios.setAlignment(Pos.CENTER_LEFT);
        VBox genderBox = new VBox(8, genderLabel, genderRadios);
        genderBox.setAlignment(Pos.CENTER_LEFT);
        personalSplitGrid.add(genderBox, 2, 1, 2, 1);

        // =====================================================================
        // 3. SECTION 2: EMPLOYMENT ELIGIBILITY
        // =====================================================================
        Label eligibilityHeader = createSectionBanner("Employment Eligibility");

        // Split row: Date Available & Desired Position
        GridPane eligibilitySplitGrid = new GridPane();
        eligibilitySplitGrid.setHgap(12);
        eligibilitySplitGrid.setVgap(12);
        eligibilitySplitGrid.getColumnConstraints().addAll(splitLabel1, splitField1, splitLabel2, splitField2);

        DatePicker availableDatePicker = new DatePicker();
        availableDatePicker.setPromptText("mm/dd/yyyy");
        availableDatePicker.setPrefHeight(32);
        availableDatePicker.setMaxWidth(Double.MAX_VALUE);
        availableDatePicker.setStyle(INPUT_BORDER_STYLE);

        eligibilitySplitGrid.add(createFormLabel("Date\nAvailable"), 0, 0);
        eligibilitySplitGrid.add(availableDatePicker, 1, 0);

        TextField positionField = createStyledTextField("");
        eligibilitySplitGrid.add(createFormLabel("Desired\nPosition"), 2, 0);
        eligibilitySplitGrid.add(positionField, 3, 0);

        // Wide row: Desired Salary
        GridPane salaryGrid = new GridPane();
        salaryGrid.setHgap(12);
        salaryGrid.getColumnConstraints().addAll(wideLabelCol, wideFieldCol);

        TextField salaryField = createStyledTextField("");
        salaryGrid.add(createFormLabel("Desired Salary"), 0, 0);
        salaryGrid.add(salaryField, 1, 0);

        // Radio Question 1: Legally authorized to work in the country?
        Label authLabel = createFormLabel("Are you legally authorized to work in the country?");
        RadioButton authYes = new RadioButton("Yes");
        RadioButton authNo = new RadioButton("No");
        ToggleGroup authGroup = new ToggleGroup();
        authYes.setToggleGroup(authGroup);
        authNo.setToggleGroup(authGroup);
        authYes.setSelected(true);
        HBox authRadios = new HBox(20, authYes, authNo);

        // Radio Question 2: Relatives working for our company?
        Label relativesLabel = createFormLabel("Do you have relatives working for our company?");
        RadioButton relYes = new RadioButton("Yes");
        RadioButton relNo = new RadioButton("No");
        ToggleGroup relGroup = new ToggleGroup();
        relYes.setToggleGroup(relGroup);
        relNo.setToggleGroup(relGroup);
        relNo.setSelected(true);
        HBox relRadios = new HBox(20, relYes, relNo);

        // If yes, please explain further
        GridPane explainGrid = new GridPane();
        explainGrid.setHgap(12);
        explainGrid.getColumnConstraints().addAll(wideLabelCol, wideFieldCol);

        TextField explainField = createStyledTextField("");
        explainGrid.add(createFormLabel("If yes, please explain further"), 0, 0);
        explainGrid.add(explainField, 1, 0);

        VBox eligibilityQuestions = new VBox(10,
                authLabel, authRadios,
                relativesLabel, relRadios,
                explainGrid);
        eligibilityQuestions.setPadding(new Insets(4, 0, 6, 0));

        // =====================================================================
        // 4. BOTTOM FOOTER: Policy Text on Left + Submit Button on Right
        // =====================================================================
        Label agreementText = createFormLabel(
                "By submitting this application, you agree\n"
                + "to adhere to company policies and\n"
                + "provide accurate information throughout\n"
                + "the employment process.");
        agreementText.setStyle("-fx-font-size: 12.5px; -fx-text-fill: #222222; -fx-line-spacing: 2px;");

        Button submitButton = new Button("Submit Application");
        submitButton.setPrefSize(195, 58);
        submitButton.setStyle("-fx-background-color: #0b0b0b; -fx-text-fill: white; "
                + "-fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand;");

        Region footerSpacer = new Region();
        HBox.setHgrow(footerSpacer, Priority.ALWAYS);

        // Only agreementText and submitButton (no "Applicant's Signature" label)
        HBox footerBox = new HBox(16, agreementText, footerSpacer, submitButton);
        footerBox.setAlignment(Pos.CENTER_LEFT);
        footerBox.setPadding(new Insets(16, 28, 18, 28));
        footerBox.setStyle("-fx-border-color: #222222 transparent transparent transparent; -fx-border-width: 1.5;");

        // =====================================================================
        // 5. SUBMIT BUTTON EVENT HANDLER
        // =====================================================================
        submitButton.setOnAction(actionEvent -> {
            String fullName = fullNameField.getText().trim();
            String currentAddress = addressField.getText().trim();
            String contactNumber = contactField.getText().trim();
            String emailAddress = emailField.getText().trim();
            String highestEducation = educationField.getText().trim();
            LocalDate availableDate = availableDatePicker.getValue();
            String desiredPosition = positionField.getText().trim();
            String salaryText = salaryField.getText().trim();
            String relativesExplain = explainField.getText().trim();

            RadioButton selectedGenderBtn = (RadioButton) genderGroup.getSelectedToggle();
            String gender = selectedGenderBtn != null ? selectedGenderBtn.getText() : "Male";

            boolean legallyAuthorized = authYes.isSelected();
            boolean relativesInCompany = relYes.isSelected();

            if (!StringPool.BLANK.equals(fullName)
                    && !StringPool.BLANK.equals(currentAddress)
                    && !StringPool.BLANK.equals(contactNumber)
                    && !StringPool.BLANK.equals(emailAddress)
                    && !StringPool.BLANK.equals(highestEducation)
                    && availableDate != null
                    && !StringPool.BLANK.equals(desiredPosition)
                    && !StringPool.BLANK.equals(salaryText)) {

                if (relativesInCompany && StringPool.BLANK.equals(relativesExplain)) {
                    this.alert("Error", "Please explain further about relatives working in the company!", AlertType.ERROR);
                    return;
                }

                double desiredSalary;
                try {
                    desiredSalary = Double.parseDouble(salaryText.replace(",", "").replace("$", ""));
                } catch (NumberFormatException ex) {
                    this.alert("Error", "Desired Salary must be a valid number (e.g. 65000)!", AlertType.ERROR);
                    return;
                }

                try {
                    EmploymentForm form = this.createEmploymentFormObject(
                            fullName, currentAddress, contactNumber, emailAddress,
                            highestEducation, gender, availableDate, desiredPosition,
                            desiredSalary, legallyAuthorized, relativesInCompany, relativesExplain);

                    int applicationId = formDao.saveApplication(form);
                    if (applicationId > 0) {
                        this.alert("Save", "Successful!", AlertType.INFORMATION);
                    } else {
                        this.alert("Error", "Failed!", AlertType.ERROR);
                    }
                } catch (SQLException ex) {
                    System.out.println("Exception occurred");
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getErrorCode());
                    System.out.println(ex.getSQLState());
                    this.alert("Error", "Database error: " + ex.getMessage(), AlertType.ERROR);
                }
            } else {
                this.alert("Error", "Please complete fields!", AlertType.ERROR);
            }
        });

        // Assemble the inner padded form sections
        VBox formSections = new VBox(12,
                personalHeader,
                personalTopGrid,
                personalSplitGrid,
                eligibilityHeader,
                eligibilitySplitGrid,
                salaryGrid,
                eligibilityQuestions);
        formSections.setPadding(new Insets(14, 28, 14, 28));

        // Assemble card with Top Header, Center Form Sections, and Bottom Edge-to-Edge Footer
        BorderPane card = new BorderPane();
        card.setTop(topHeader);
        card.setCenter(formSections);
        card.setBottom(footerBox);
        card.setStyle("-fx-background-color: white; -fx-border-color: #222222; -fx-border-width: 1.5;");

        BorderPane root = new BorderPane(card);
        root.setPadding(new Insets(18));
        root.setStyle("-fx-background-color: #ececec;");

        Scene scene = new Scene(root, 730, 770);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(730);
        primaryStage.setMinHeight(770);
        primaryStage.show();
    }

    // Helper: Creates a Label that never clips multi-line text vertically
    private Label createFormLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12.5));
        label.setStyle("-fx-text-fill: #1a1a1a;");
        label.setMinHeight(Region.USE_PREF_SIZE);
        label.setMinWidth(Region.USE_PREF_SIZE);
        return label;
    }

    // Helper: Creates a crisp, worksheet-styled TextField
    private TextField createStyledTextField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setPrefHeight(32);
        textField.setMaxWidth(Double.MAX_VALUE);
        textField.setStyle(INPUT_BORDER_STYLE);
        return textField;
    }

    // Helper: Creates the black section title bars ("Personal Information", "Employment Eligibility")
    private Label createSectionBanner(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 13.5));
        label.setStyle("-fx-background-color: #0b0b0b; -fx-text-fill: white; -fx-padding: 6 18 6 12;");
        label.setPrefWidth(220);
        label.setMinHeight(Region.USE_PREF_SIZE);
        VBox.setMargin(label, new Insets(4, 0, 2, 0));
        return label;
    }

    public void alert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public EmploymentForm createEmploymentFormObject(
            String fullName, String currentAddress, String contactNumber, String emailAddress,
            String highestEducation, String gender, LocalDate availableDate, String desiredPosition,
            double desiredSalary, boolean legallyAuthorized, boolean relativesInCompany,
            String relativesInCompanyExplain) {

        EmploymentForm form = new EmploymentForm();
        form.setFullName(fullName);
        form.setCurrentAddress(currentAddress);
        form.setContactNumber(contactNumber);
        form.setEmailAddress(emailAddress);
        form.setHighestEducation(highestEducation);
        form.setGender(gender);
        form.setAvailableDate(availableDate);
        form.setDesiredPosition(desiredPosition);
        form.setDesiredSalary(desiredSalary);
        form.setLegallyAuthorized(legallyAuthorized);
        form.setRelativesInCompany(relativesInCompany);
        form.setRelativesInCompanyExplain(relativesInCompanyExplain);

        return form;
    }

    public static void main(String[] args) {
        launch(args);
    }
}