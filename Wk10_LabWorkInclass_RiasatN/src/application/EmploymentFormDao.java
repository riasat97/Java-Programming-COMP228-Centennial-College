package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * - COMP228 - Week 10 In-Class Lab
 * - Student Name: Riasat Raihan Noor
 * - Purpose: Data Access Object (DAO) that inserts EmploymentForm records
 *   into MySQL using PreparedStatement (following the UserDao template).
 */
public class EmploymentFormDao {

    public int saveApplication(EmploymentForm form) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);

            String query = "INSERT INTO employment_form("
                    + "full_name, current_address, contact_number, email_address, "
                    + "highest_education, gender, available_date, desired_position, "
                    + "desired_salary, legally_authorized, relatives_in_company, "
                    + "relatives_in_company_explain) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // Bind parameters in order using counter++ just like UserDao.java
            int counter = 1;
            statement.setString(counter++, form.getFullName());
            statement.setString(counter++, form.getCurrentAddress());
            statement.setString(counter++, form.getContactNumber());
            statement.setString(counter++, form.getEmailAddress());
            statement.setString(counter++, form.getHighestEducation());
            statement.setString(counter++, form.getGender());
            statement.setDate(counter++, Date.valueOf(form.getAvailableDate()));
            statement.setString(counter++, form.getDesiredPosition());
            statement.setDouble(counter++, form.getDesiredSalary());
            statement.setBoolean(counter++, form.isLegallyAuthorized());
            statement.setBoolean(counter++, form.isRelativesInCompany());
            statement.setString(counter++, form.getRelativesInCompanyExplain());

            statement.executeUpdate();
            connection.commit();

            // Retrieve the auto-generated primary key (id)
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            System.out.println("Database Exception: " + exception.getMessage());
            if (null != connection) {
                connection.rollback();
            }
            throw exception;
        } finally {
            if (null != resultSet) {
                resultSet.close();
            }
            if (null != statement) {
                statement.close();
            }
            if (null != connection) {
                connection.close();
            }
        }

        return 0;
    }
}