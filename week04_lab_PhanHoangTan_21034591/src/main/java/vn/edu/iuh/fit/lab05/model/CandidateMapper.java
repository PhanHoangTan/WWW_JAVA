package vn.edu.iuh.fit.lab05.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateMapper implements RowMapper<Candidate> {

    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(rs.getLong("id"));
        String lastName = rs.getString("last_Name");
        String middleName = rs.getString("middle_Name");
        String firstName = rs.getString("first_Name");
        String fullName = lastName + " " + middleName + " " + firstName;
        candidate.setFull_Name(fullName);
        candidate.setDob(rs.getString("dob"));
        candidate.setEmail(rs.getString("email"));
        candidate.setAddress(rs.getString("address"));
        candidate.setPhone(rs.getString("phone"));
        return candidate;
    }
}