package com.example.springprojectlms.Service;

import com.example.springprojectlms.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> coursesList = new ArrayList<>();

    public ArrayList<Course> getCourses(){
        return coursesList;
    }

    public void addCourse(Course course){
        coursesList.add(course);
    }

    public boolean updateCourse(Course course, String id){
        for(int i =0; i<coursesList.size(); i++){
            if(coursesList.get(i).getId().equals(id)){
                coursesList.set(i,course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String id){
        for (Course c: coursesList){
            if (c.getId().equals(id)){
                coursesList.remove(c);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Course> getCoursesByHours(int hours){
      ArrayList<Course> coursesByHoursList = new ArrayList<>();
        for (Course c : coursesList){
            if (c.getHours() == hours){
                coursesByHoursList.add(c);
            }
        }
        return coursesByHoursList;
    }

    public ArrayList<Course> getCoursesByMajor(String major){
        ArrayList<Course> coursesByMajorList = new ArrayList<>();

        for (Course c: coursesList) {
            if (c.getId().equalsIgnoreCase(major)) {
                coursesByMajorList.add(c);
            }
        }
        return coursesByMajorList;
    }

    public Course getCourseByCode(String code){
        for (Course c: coursesList){
            if (c.getCode().equalsIgnoreCase(code)){
                return c;
            }
        }
        return null;
    }

    public void changeCourseCore(String id){
        for (Course c: coursesList){
            if (c.getId().equals(id)){
                if(c.isCore()){
                    c.setCore(false);
                }else {
                    c.setCore(true);
                }

            }
        }
    }


}
