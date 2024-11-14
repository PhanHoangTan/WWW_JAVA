package vn.edu.iuh.fit.lab05.respositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.lab05.model.Candidate;
import vn.edu.iuh.fit.lab05.model.CandidateMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class CandidateRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final Logger logger = Logger.getLogger(CandidateRepository.class.getName());


    public CandidateRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Candidate> findAll() {
        String sql = "SELECT * FROM candidate";
        return jdbcTemplate.query(sql, new CandidateMapper());
    }

    public boolean add(Candidate candidate) {
        String sql = "INSERT INTO candidate (last_Name, middle_Name, first_Name, dob, email, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql,candidate.getFull_Name().split(" ")[0], candidate.getFull_Name().split(" ")[1], candidate.getFull_Name().split(" ")[2], candidate.getDob(), candidate.getEmail(), candidate.getAddress(), candidate.getPhone());
        if (result > 0) {
            logger.info("Thêm thành công");
            return true;
        } else {
            logger.info("Thêm thất bại");
            return false;
        }

    }



}
