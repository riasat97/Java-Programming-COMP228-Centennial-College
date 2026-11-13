package application;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * - COMP228 - Java Programming
 * - Lab Assignment: Hospital Management System (JDBC Data Access Object)
 * - Student Name: Riasat Raihan Noor
 * - Student ID: 301554207
 * 
 * Purpose of this class:
 * Separates all MySQL database code from the JavaFX user interface (Optional Extension).
 * Uses DriverManager, PreparedStatement, ResultSet, and try-with-resources
 * to safely perform Create, Read, Update, Delete (CRUD) and Search operations.
 */
public class PatientDAO {

    // Database connection constants for local MySQL server
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    // =========================================================================
    // 1. CREATE — Insert a new patient record into the database
    // =========================================================================
    public boolean insertPatient(Patient patient) throws SQLException {
        // Using '?' placeholders prevents SQL injection attacks
        String sql = "INSERT INTO patients (name, age, gender, phone, diagnosis, department, status, admission_date) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // try-with-resources automatically closes Connection and PreparedStatement when done
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Bind each form value to its matching '?' placeholder (1-indexed)
            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getPhone());
            ps.setString(5, patient.getDiagnosis());
            ps.setString(6, patient.getDepartment());
            ps.setString(7, patient.getStatus());
            // Convert Java's LocalDate into java.sql.Date for MySQL DATE column
            ps.setDate(8, Date.valueOf(patient.getAdmissionDate()));

            // executeUpdate() returns the number of rows affected (1 means success)
            return ps.executeUpdate() == 1;
        }
    }

    // =========================================================================
    // 2. READ — Retrieve all patient records from MySQL to show in the TableView
    // =========================================================================
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT patient_id, name, age, gender, phone, diagnosis, department, status, admission_date, created_at "
                   + "FROM patients ORDER BY patient_id DESC";

        // Open connection, prepare SELECT query, and execute it to get a ResultSet
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Loop through every row returned by MySQL and convert it into a Patient object
            while (rs.next()) {
                list.add(mapRowToPatient(rs));
            }
        }
        return list;
    }

    // =========================================================================
    // 3. UPDATE — Edit an existing patient record using its primary key (patient_id)
    // =========================================================================
    public boolean updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE patients SET name = ?, age = ?, gender = ?, phone = ?, "
                   + "diagnosis = ?, department = ?, status = ?, admission_date = ? "
                   + "WHERE patient_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patient.getName());
            ps.setInt(2, patient.getAge());
            ps.setString(3, patient.getGender());
            ps.setString(4, patient.getPhone());
            ps.setString(5, patient.getDiagnosis());
            ps.setString(6, patient.getDepartment());
            ps.setString(7, patient.getStatus());
            ps.setDate(8, Date.valueOf(patient.getAdmissionDate()));
            ps.setInt(9, patient.getPatientId()); // WHERE patient_id = ?

            return ps.executeUpdate() == 1;
        }
    }

    // =========================================================================
    // 4. DELETE — Remove a selected patient record from MySQL using patient_id
    // =========================================================================
    public boolean deletePatient(int patientId) throws SQLException {
        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            return ps.executeUpdate() == 1;
        }
    }

    // =========================================================================
    // 5. SEARCH — Find patients by name or phone using a parameterized LIKE query
    // =========================================================================
    public List<Patient> searchPatients(String keyword) throws SQLException {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT patient_id, name, age, gender, phone, diagnosis, department, status, admission_date, created_at "
                   + "FROM patients WHERE name LIKE ? OR phone LIKE ? ORDER BY patient_id DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Adding '%' wildcards allows partial matching (e.g., "Sarah" or "555")
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToPatient(rs));
                }
            }
        }
        return list;
    }

    // =========================================================================
    // 6. OPTIONAL EXTENSION — Filter patients by exact Admission Date
    // =========================================================================
    public List<Patient> filterByAdmissionDate(LocalDate date) throws SQLException {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT patient_id, name, age, gender, phone, diagnosis, department, status, admission_date, created_at "
                   + "FROM patients WHERE admission_date = ? ORDER BY patient_id DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(date));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToPatient(rs));
                }
            }
        }
        return list;
    }

    // =========================================================================
    // 7. OPTIONAL EXTENSION — Quick Discharge Action (updates status to 'Discharged')
    // =========================================================================
    public boolean dischargePatient(int patientId) throws SQLException {
        String sql = "UPDATE patients SET status = 'Discharged' WHERE patient_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            return ps.executeUpdate() == 1;
        }
    }

    // Helper method: Reads the current row of a ResultSet and returns a Patient object
    private Patient mapRowToPatient(ResultSet rs) throws SQLException {
        Date admDate = rs.getDate("admission_date");
        return new Patient(
                rs.getInt("patient_id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getString("phone"),
                rs.getString("diagnosis"),
                rs.getString("department"),
                rs.getString("status"),
                admDate != null ? admDate.toLocalDate() : null,
                rs.getString("created_at")
        );
    }
}