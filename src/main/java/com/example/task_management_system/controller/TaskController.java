package com.example.task_management_system.controller;

import com.example.task_management_system.dto.response.MessageResponseDTO;
import com.example.task_management_system.dto.request.TaskRequestDTO;
import com.example.task_management_system.dto.response.TaskResponseDTO;
import com.example.task_management_system.entity.Priority;
import com.example.task_management_system.entity.Status;
import com.example.task_management_system.entity.Task;
import com.example.task_management_system.entity.User;
import com.example.task_management_system.mapper.TaskResponseMapper;
import com.example.task_management_system.service.TaskService;
import com.example.task_management_system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Task Controller", description = "Контроллер для работы с заданиями")
@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    public static final String TASK_NOT_FOUND = "Task with this id not found.";
    public static final String USER_NOT_FOUND = "User with this id not found.";
    public static final String NOT_HAVE_PERMIT = "You not have permit for modify this task.";
    public static final String TASK_UPDATED = "Task updated!";
    public static final String STATUS_UPDATED = "Status updated!";
    public static final String DELETED = "Deleted!";

    private final TaskService taskService;
    private final UserService userService;

    @Operation(
            summary = "Получение всех заданий.",
            description = "Доступно всем залогиненным пользователям.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TaskResponseDTO.class)), mediaType = "application/json") }) })
    @GetMapping("/all")
    public ResponseEntity<Page<TaskResponseDTO>> getAll(@RequestParam("page") int pageIndex,
                                                        @RequestParam("size") int pageSize){
        return ResponseEntity.ok(taskService.getAll(PageRequest.of(pageIndex, pageSize)));
    }

    @Operation(
            summary = "Получение задания с указанным id.",
            description = "Доступно всем залогиненным пользователям.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = MessageResponseDTO.class), mediaType = "application/json") }) })
    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam(name = "id") long id){
        Task task = taskService.get(id);
        if (task == null) return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(new MessageResponseDTO(TASK_NOT_FOUND));
        TaskResponseDTO taskResponseDTO = TaskResponseMapper.INSTANCE.taskToTaskResponseDTO(task);
        return ResponseEntity.ok(taskResponseDTO);
    }

    @Operation(
            summary = "Сохнанение или обновление задания.",
            description = "Сохнанение нового задания или обновление существующего задания если пользователь является автором задания. " +
                    "Если пользователь является исполнителем, можно обновмть только статус.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = TaskResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = MessageResponseDTO.class), mediaType = "application/json") }) })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TaskRequestDTO taskRequestDTO) {
        Task task;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User executor = userService.get(taskRequestDTO.getExecutorId());

        if (taskRequestDTO.getId() > 0) {
            if (user.getId() == taskRequestDTO.getAuthorId()) {
                task = new Task(taskRequestDTO.getId(),
                        taskRequestDTO.getTitle(),
                        taskRequestDTO.getDescription(),
                        Status.valueOf(taskRequestDTO.getStatus()),
                        Priority.valueOf(taskRequestDTO.getPriority()),
                        user,
                        executor);

                taskService.update(task);
                return ResponseEntity.ok(new MessageResponseDTO(TASK_UPDATED));
            }
            else if (user.getId() == taskRequestDTO.getExecutorId()) {
                task = taskService.get(taskRequestDTO.getId());
                Status status = Status.valueOf(taskRequestDTO.getStatus());
                if (!task.getStatus().equals(status)) {
                    task.setStatus(status);
                    taskService.update(task);
                }
                return ResponseEntity.ok(new MessageResponseDTO(STATUS_UPDATED));
            }
            else return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(new MessageResponseDTO(NOT_HAVE_PERMIT));
        }
        else {
            task = new Task(taskRequestDTO.getTitle(),
                    taskRequestDTO.getDescription(),
                    Status.valueOf(taskRequestDTO.getStatus()),
                    Priority.valueOf(taskRequestDTO.getPriority()),
                    user,
                    executor);

            TaskResponseDTO taskResponseDTO = TaskResponseMapper.INSTANCE.taskToTaskResponseDTO(taskService.create(task));
            return ResponseEntity.ok(taskResponseDTO);
        }
    }
    @Operation(
            summary = "Удаление задания.",
            description = "Сохнанение нового задания или обновление существующего задания если пользователь является автором задания.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MessageResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = MessageResponseDTO.class), mediaType = "application/json") }) })
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Task task = taskService.get(id);
        if (task == null) return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(new MessageResponseDTO(TASK_NOT_FOUND));

        if (user.getId() == task.getAuthor().getId()) {
            taskService.delete(id);
            return ResponseEntity.ok(new MessageResponseDTO(DELETED));
        }
        return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(new MessageResponseDTO(NOT_HAVE_PERMIT));
    }

    @Operation(
            summary = "Получение заданий, где автор с указанным id.",
            description = "Доступно всем залогиненным пользователям.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TaskResponseDTO.class)), mediaType = "application/json") }) })
    @GetMapping("/get-by-author")
    public ResponseEntity<?> getTasksByAuthor(@RequestParam("id") long id,
                                              @RequestParam("page") int pageIndex,
                                              @RequestParam("size") int pageSize){
        User user = userService.get(id);
        if (user == null) return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(new MessageResponseDTO(USER_NOT_FOUND));
        return ResponseEntity.ok(taskService.getByAuthorId(id, PageRequest.of(pageIndex, pageSize)));
    }

    @Operation(
            summary = "Получение заданий, где есть исполнитель с указанным id.",
            description = "Доступно всем залогиненным пользователям.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = TaskResponseDTO.class)), mediaType = "application/json") }) })
    @GetMapping("/get-by-executor")
    public ResponseEntity<?> getTasksByExecutor(@RequestParam(name = "id") long id,
                                                @RequestParam("page") int pageIndex,
                                                @RequestParam("size") int pageSize){
        User user = userService.get(id);
        if (user == null) return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(new MessageResponseDTO(USER_NOT_FOUND));
        return ResponseEntity.ok(taskService.getByExecutorId(id, PageRequest.of(pageIndex, pageSize)));
    }
}
