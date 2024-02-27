package com.example.task_management_system;

import com.example.task_management_system.entity.*;
import com.example.task_management_system.service.CommentService;
import com.example.task_management_system.service.TaskService;
import com.example.task_management_system.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
@RequiredArgsConstructor
public class TaskManagementSystemApplication {

	final UserService userService;
	final TaskService taskService;
	final CommentService commentService;

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementSystemApplication.class, args);
	}

	@PostConstruct
	public void setUp(){
		User ivan = new User("Ivan", "ivan@ya.ru", "$2a$10$4p8Ppk362xiOcJH5dnuFCuDt6oStyqip4F0vWjgwD582G.bq.Ul9q",
				Collections.singleton(Role.USER));
		User serg = new User("Serg", "serg@ya.ru", "$2a$10$4p8Ppk362xiOcJH5dnuFCuDt6oStyqip4F0vWjgwD582G.bq.Ul9q",
				Collections.singleton(Role.USER));
		User lena = new User("Lena", "lena@ya.ru", "$2a$10$4p8Ppk362xiOcJH5dnuFCuDt6oStyqip4F0vWjgwD582G.bq.Ul9q",
				Collections.singleton(Role.USER));

		userService.create(ivan);
		userService.create(serg);
		userService.create(lena);

		Task task1 = new Task("Task#1", "first task", Status.IN_PROGRESS, Priority.LOW, ivan, serg);
		Task task2 = new Task("Task#2", "second task", Status.IN_DELAY, Priority.MIDDLE, ivan, lena);
		Task task3 = new Task("Task#3", "third task", Status.FINISHED, Priority.HIGH, lena, lena);
		Task task4 = new Task("Task#4", "four task", Status.FINISHED, Priority.HIGH, serg, lena);
		Task task5 = new Task("Task#5", "five task", Status.FINISHED, Priority.HIGH, serg, lena);

		taskService.create(task1);
		taskService.create(task2);
		taskService.create(task3);
		taskService.create(task4);
		taskService.create(task5);

		Comment comment = new Comment("Good job, team!", ivan, task3);
		Comment comment1 = new Comment("Good job, team!", ivan, task3);

		commentService.create(comment);
		commentService.create(comment1);
	}

}
