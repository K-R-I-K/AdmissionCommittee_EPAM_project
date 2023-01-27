package org.my.model.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.my.model.dao.implementations.ApplicantFacultyDaoImpl;
import org.my.model.db.ConnectionPool;
import org.my.model.db.DBException;
import org.my.model.entities.ApplicantFaculty;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class ApplicantFacultyDaoTest {
    private Connection connection;
    private ApplicantFacultyDao applicantFacultyDao;

    @Before
    public void before() {
        connection = ConnectionPool.getConnection();
        applicantFacultyDao = new ApplicantFacultyDaoImpl(connection);
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBException("Cannot close connection", e);
        }
    }

    @Test
    public void add() {
        ApplicantFaculty applicantFaculty = ApplicantFaculty.builder()
                .id(0L)
                .userId(4L)
                .facultyId(1L)
                .mark1(111)
                .mark2(111)
                .mark3(111)
                .avgCertificateMark(11)
                .passed(false)
                .build();

        applicantFacultyDao.add(applicantFaculty);
        ApplicantFaculty actual = applicantFacultyDao.findByUserAndFaculty(4L, 1L);
        if(actual != null) {
            applicantFacultyDao.deleteEntity(actual);
        }

        Assert.assertNotNull(actual);
    }

    @Test
    public void findAll() {
        ApplicantFaculty applicantFaculty = ApplicantFaculty.builder()
                .id(0L)
                .userId(4L)
                .facultyId(1L)
                .mark1(111)
                .mark2(111)
                .mark3(111)
                .avgCertificateMark(11)
                .passed(false)
                .build();

        applicantFacultyDao.add(applicantFaculty);
        List<ApplicantFaculty> list = applicantFacultyDao.findAll();
        applicantFacultyDao.deleteEntity(list.get(list.size() - 1));
        Assert.assertNotNull(list);
    }

    @Test
    public void deleteById() {
        Throwable exception = Assert.assertThrows(NoSuchElementException.class, () -> {
            ApplicantFaculty applicantFaculty = ApplicantFaculty.builder()
                    .id(0L)
                    .userId(4L)
                    .facultyId(1L)
                    .mark1(111)
                    .mark2(111)
                    .mark3(111)
                    .avgCertificateMark(11)
                    .passed(false)
                    .build();

            applicantFacultyDao.add(applicantFaculty);
            ApplicantFaculty tmp = applicantFacultyDao.findByUserAndFaculty(4L, 1L);
            if(tmp != null) {
                applicantFacultyDao.delete(tmp.getId());
            }

            ApplicantFaculty expected = applicantFacultyDao.findByUserAndFaculty(4L, 1L);
            if (expected == null) {
                throw new NoSuchElementException("no such element");
            }
        });
        Assert.assertEquals(exception.getMessage(), "no such element");
    }

    @Test
    public void findApplicantFaculty() {
        ApplicantFaculty applicantFaculty = ApplicantFaculty.builder()
                .id(0L)
                .userId(4L)
                .facultyId(1L)
                .mark1(111)
                .mark2(111)
                .mark3(111)
                .avgCertificateMark(11)
                .passed(false)
                .build();

        applicantFacultyDao.add(applicantFaculty);
        ApplicantFaculty expected = applicantFacultyDao.findByUserAndFaculty(4L, 1L);
        if(expected != null) {
            applicantFacultyDao.deleteEntity(expected);
        }
        Assert.assertNotNull(expected);
    }

    @Test
    public void update() {
        ApplicantFaculty applicantFaculty = ApplicantFaculty.builder()
                .id(0L)
                .userId(4L)
                .facultyId(1L)
                .mark1(111)
                .mark2(111)
                .mark3(111)
                .avgCertificateMark(11)
                .passed(false)
                .build();

        applicantFacultyDao.add(applicantFaculty);
        ApplicantFaculty applicantFaculty1 = applicantFacultyDao.findByUserAndFaculty(4L, 1L);

        applicantFaculty = ApplicantFaculty.builder()
                .id(applicantFaculty1.getId())
                .userId(3L)
                .facultyId(1L)
                .mark1(111)
                .mark2(111)
                .mark3(111)
                .avgCertificateMark(11)
                .passed(false)
                .build();

        applicantFacultyDao.update(applicantFaculty);
        ApplicantFaculty applicantFaculty2 = applicantFacultyDao.findByUserAndFaculty(3L, 1L);

        Assert.assertNotEquals(applicantFaculty1, applicantFaculty2);

        if(applicantFaculty1 != null) {
            applicantFacultyDao.deleteEntity(applicantFaculty1);
        }
        if(applicantFaculty2 != null) {
            applicantFacultyDao.deleteEntity(applicantFaculty2);
        }
    }
}
