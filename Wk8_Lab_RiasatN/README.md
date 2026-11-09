# COMP228 - Week 8 Lab: JavaFX Layout Panes

**Author:** Riasat Raihan Noor  
**Course:** COMP228 - Java Programming (Centennial College)  
**Technology:** Java 25, JavaFX 25

## Overview
A desktop **College Student Registration** application built entirely in pure Java code (without FXML) to demonstrate nested JavaFX layout managers, UI styling, and event-driven form validation.

## Layout Architecture
The interface combines four core JavaFX layout panes:
* **`BorderPane` (Root Layout):** Divides the main application window (`850x520`) into Top, Left, and Center regions.
* **`HBox` (Top Header & Action Buttons):** 
  * Top region displays the `"College Student Services | JavaFX Layout Lab"` banner.
  * Center form uses a secondary `HBox` to align the `Register` and `Clear` buttons horizontally.
* **`VBox` (Left Navigation & Center Container):**
  * Left region stacks the `"MENU"` label and navigation buttons (`Dashboard`, `Registration`, `Courses`, `Help`) vertically.
  * Center region stacks the form title, `GridPane` form, action buttons `HBox`, and status label.
* **`GridPane` (Registration Form):** Aligns labels and input controls in a 2-column, 4-row grid for:
  * **Student ID** (`TextField`)
  * **Full Name** (`TextField`)
  * **Email** (`TextField`)
  * **Program** (`ComboBox`)

## Features & Event Handling
* **Register Validation:** Trims text inputs and verifies that `Student ID`, `Full Name`, `Email`, and `Program` selection are all provided before displaying a confirmation message. Displays an error prompt if any field is empty.
* **Clear Functionality:** Resets all text fields, clears the `ComboBox` selection, and updates the status message to `"Form cleared."`

## Project Structure
```text
Wk8_Lab_RiasatN/
├── src/
│   ├── application/
│   │   ├── Main.java
│   │   └── application.css
│   └── module-info.java
└── README.md