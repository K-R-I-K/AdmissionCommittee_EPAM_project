package org.my.controller.command;

import org.my.model.dao.FacultyDao;
import org.my.model.entities.Faculty;

import javax.servlet.http.HttpServletRequest;

public class AddFaculty implements Command {
    private FacultyDao facultyDao;
    public AddFaculty(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }
    @Override
    public String execute(HttpServletRequest request) {
        try {
            Faculty faculty = Faculty.builder()
                    .id(0L)
                    .name(String.valueOf(request.getParameter("name")))
                    .budgetPlaces(Long.parseLong(request.getParameter("budget_places")))
                    .totalPlaces(Long.parseLong(request.getParameter("total_places")))
                    .build();
            if(facultyDao.isExisting(faculty)) {
                return "redirect:newFaculty.jsp";
            } else {
                facultyDao.add(faculty);
                request.getSession().setAttribute("faculties", facultyDao.findAll());
                return "redirect:faculties.jsp";
            }
        } catch (NullPointerException |  NumberFormatException e) {
            return "redirect:newFaculty.jsp";
        }
    }

}
