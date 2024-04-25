package org.example.service;

import lombok.AllArgsConstructor;
import org.example.DTO.TaskDTO;
import org.example.entity.Task;
import org.example.exceptions.BadRequestException;
import org.example.repository.ToDoListRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final ToDoListRepository toDoListRepository;

    public Task create(TaskDTO dto) throws BadRequestException {
        Date date1 = new Date();
        int NoteLength = dto.getNote().length();
        int TitleLength = dto.getTitle().length();

        if(date1.after(dto.getDue_date())) {
            throw new BadRequestException("due date is invalid");
        }

        if(NoteLength>2048){
           throw new BadRequestException("length must be less than 2048");
        }

        if(TitleLength>2048){
            throw new BadRequestException("length must be less than 2048");
        }

            Task task = Task.builder()
                    .title(dto.getTitle())
                    .note(dto.getNote())
                    .due_date(dto.getDue_date())
                    .created_at(new Date())
                    .build();
            return toDoListRepository.save(task);
    }

    public List<Task> readAll(Sort sort){
        List<Task> all = toDoListRepository.findAll(sort);
        return all;
    }
    public void delete(Integer id){
        toDoListRepository.deleteById(id);
    }
}
