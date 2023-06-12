package com.microhawk.webissshdemo.controller;

import com.microhawk.webissshdemo.model.entity.Student;
import com.microhawk.webissshdemo.model.request.StudentRequest;
import com.microhawk.webissshdemo.model.request.UpdateRequest;
import com.microhawk.webissshdemo.model.response.GlobalResponse;
import com.microhawk.webissshdemo.service.StudentImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StudentController extends BaseController {

    @Autowired
    private StudentImpl studentService;

    @PostMapping(value = "/student")
    public Mono<GlobalResponse<Student>> saveNewStudent(@RequestBody StudentRequest request) {
        return studentService.saveNewStudent(request).map(this::toGlobalResponse);
    }

    @GetMapping(value = "/student")
    public Mono<GlobalResponse<List<Student>>> getAllStudent() {
        return studentService.getAllStudent().map(this::toGlobalResponse);
    }

    @PutMapping(value = "/student")
    public Mono<GlobalResponse<Student>> updateStudent(@RequestBody UpdateRequest request) {
        return studentService.updateStudent(request).map(this::toGlobalResponse);
    }

    @DeleteMapping(value = "/student")
    public Mono<GlobalResponse<Student>> deleteStudentByRollNumber(@RequestParam("rn") int rn) {
        return studentService.deleteStudentByRollNumber(rn).map(this::toGlobalResponse);
    }
}
