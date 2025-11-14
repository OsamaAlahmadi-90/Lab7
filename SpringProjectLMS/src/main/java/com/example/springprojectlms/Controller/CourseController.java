package com.example.springprojectlms.Controller;

import com.example.springprojectlms.Api.ApiResponse;
import com.example.springprojectlms.Model.Course;
import com.example.springprojectlms.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lms/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getCourses(){
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course has been added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody @Valid Course course, @PathVariable String id, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if (courseService.updateCourse(course,id)){
            return ResponseEntity.status(200).body(new ApiResponse("Course: " + course.getTitle() + " with ID "+ id + " has been updated."));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Course with id: " + id + " wasn't found" ));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id){
        if (courseService.deleteCourse(id)){
            return ResponseEntity.status(200).body("Course with ID: "+ id + " Has been removed");
        }
        return ResponseEntity.status(400).body("Course with ID: "+ id + " was not found");
    }


    @GetMapping("/get-by-hours/{hours}")
    public ResponseEntity<?> getCoursesByHours(@PathVariable int hours){
        if (courseService.getCoursesByHours(hours).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There aren't any Courses with " + hours + " hours"));
        }
        return  ResponseEntity.status(200).body(courseService.getCoursesByHours(hours));
    }

    @GetMapping("/get-by-major/{major}")
    public ResponseEntity<?> getCoursesByMajor(@PathVariable String major){
        if(courseService.getCoursesByMajor(major).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There aren't any Courses with the major" + major));
        }
        return  ResponseEntity.status(200).body(courseService.getCoursesByMajor(major));
    }

    @GetMapping("/get-by-code/{code}")
    public ResponseEntity<?> getCourseByCode(@PathVariable String code){
        if(courseService.getCourseByCode(code) == null){
            return ResponseEntity.status(400).body(new ApiResponse("There aren't any Courses with the code" + code));
        }
        return  ResponseEntity.status(200).body(courseService.getCourseByCode(code));
    }

    @PutMapping("/change-core/{id}")
    public ResponseEntity<?> changeCourseCore(@PathVariable String id){
        courseService.changeCourseCore(id);
        return ResponseEntity.status(200).body(new ApiResponse("Course's core with id: "+ id +" has been changed"));
    }





}
