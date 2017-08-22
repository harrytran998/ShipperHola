/*
 * Copyright © 2017 XVideos Team
 */
package dao;

import java.util.List;
import javax.sql.DataSource;
import model.Student;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Le Cao Nguyen
 */
public class StudentDao extends AbstractGenericDao<Student, String> {

    private static final RowMapper<Student> MAPPER = (rs, rowNum) -> new Student(
            rs.getString("id"),
            rs.getString("name"),
            rs.getBoolean("gender"),
            rs.getDate("dateOfBirth")
    );

    public StudentDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Student> getAll() {
        return jdbcTemplate.query("SELECT * FROM Student", MAPPER);
    }

    @Override
    public Student getById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Student WHERE id = ?", new Object[]{id}, MAPPER);
    }

    @Override
    public boolean add(Student student) {
        return jdbcTemplate.update("INSERT INTO Student(id, name, gender, dateOfBirth) VALUES (?, ?, ?, ?)", student.getId(), student.getName(), student.isGender(), student.getDateOfBirth()) > 0;
    }

    @Override
    public boolean update(Student student) {
        return jdbcTemplate.update("UPDATE Student SET name = ?, gender = ?, dateOfBirth = ? WHERE id = ?", student.getName(), student.isGender(), student.getDateOfBirth(), student.getId()) > 0;
    }

    @Override
    public boolean delete(String id) {
        return jdbcTemplate.update("DELETE FROM Student WHERE id = ?", id) > 0;
    }

}
