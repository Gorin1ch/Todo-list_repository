package org.example.Controller;


import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.example.DTO.TaskDTO;
import org.example.entity.Task;
import org.example.service.TaskService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskDTO dto){
        return new ResponseEntity<>(taskService.create(dto), HttpStatus.CREATED); //HttpStatus.CREATED - статус запроса 201
    }

    @GetMapping()
    public ResponseEntity<List<Task>> readAll(@RequestParam(name = "sort", defaultValue = "created_at")String sortParam){
         Sort sort = Sort.by(sortParam);
        return new ResponseEntity<>(taskService.readAll(sort), HttpStatus.OK); //HttpStatus.OK - статус запроса 200
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Integer id){
        taskService.delete(id);
        return HttpStatus.NO_CONTENT; //HttpStatus.NO_CONTENT - статус запроса 204
    }
}
