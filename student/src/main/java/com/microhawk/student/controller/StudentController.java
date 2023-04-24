package com.microhawk.student.controller;

import com.microhawk.student.model.Constants.ApiPath;
import com.microhawk.student.model.entity.Student;
import com.microhawk.student.model.request.AddStudentRequest;
import com.microhawk.student.model.request.UpdateStudentRequest;
import com.microhawk.student.service.api.StudentService;
import com.mongodb.client.result.DeleteResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = ApiPath.STUDENT)
    public Mono<Student> saveStudent(@RequestBody AddStudentRequest request) {
        return studentService.saveNewStudent(request);
    }

    @GetMapping(value = ApiPath.STUDENT + ApiPath.STUDENT_ID)
    public Mono<Student> getStudentByStudentId(@PathVariable("id") String id) {
        return studentService.getByStudentId(id);
    }

    @GetMapping(value = ApiPath.STUDENT)
    public Mono<Student> getStudentByStudentEmailId(@RequestParam("email") String email) {
        return studentService.getByStudentEmail(email);
    }

    @GetMapping(value = ApiPath.STUDENT + ApiPath.ALL)
    public Mono<List<Student>> getAllStudentsByDepartment(@RequestParam("department") String department) {
        return studentService.getAllStudentsByDepartment(department);
    }

    @PutMapping(value = ApiPath.STUDENT)
    public Mono<Student> updateStudent(@RequestBody UpdateStudentRequest request) {
        return studentService.updateStudentByStudentId(request);
    }

    @DeleteMapping(value = ApiPath.STUDENT + ApiPath.STUDENT_ID)
    public Mono<DeleteResult> deleteByStudentId(@PathVariable("id") String id) {
        return studentService.deleteByStudentId(id);
    }
}
