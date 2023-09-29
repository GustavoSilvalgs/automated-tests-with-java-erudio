package br.com.erudio;

import br.com.erudio.service.CourseService;

import java.util.ArrayList;
import java.util.List;

// SUT - System (Method) Under Test
public class CourseBusiness {

    // Course service is a Dependency
    private CourseService service;

    public CourseBusiness(CourseService service) {
        this.service = service;
    }

    public List<String> retrieveCoursesRelatedToSpring(String student) {
        var filteredCourses = new ArrayList<String>();
        var allCourses = service.retrieveCourses(student);

        for(String course : allCourses) {
            if (course.contains("Spring")) {
                filteredCourses.add(course);
            }
        }

        return filteredCourses;
    }
}
