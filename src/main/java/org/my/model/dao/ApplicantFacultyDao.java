package org.my.model.dao;

import org.my.model.entities.ApplicantFaculty;
import org.my.model.entities.Faculty;
import org.my.model.entities.User;

import java.util.List;

/**
 * Interface ApplicantFacultyDao is used to access the information about faculties with registered applicants from the database.
 *
 * @author Kyrylo Shchupak
 */

public interface ApplicantFacultyDao extends Dao<ApplicantFaculty>{

    /**
     * The method gets ApplicantFaculties by faculty id.
     *
     * @param facultyId - faculty`s id.
     */
    List<ApplicantFaculty> findByFacultyId(Long facultyId);

    /**
     * The method gets faculties` ids by user id.
     *
     * @param userId - user`s id.
     */
    List<Long> getFacultiesWithApplicant(Long userId);

    /**
     * The method gets applicantFaculty by userId and facultyId.
     *
     * @param userId - user`s id.
     * @param facultyId - faculty`s id.
     */
    ApplicantFaculty findByUserAndFaculty(Long userId, Long facultyId);
}
