
package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.expression.spel.SpelEvaluationException;
// import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import com.todo.service.TaskService;
import com.todo.entity.Task;

import java.time.LocalDateTime;

@Component
public class TestRunner implements ApplicationRunner, ExitCodeGenerator, ExitCodeExceptionMapper {
  // private static final Logger logger =
  // LoggerFactory.getLogger(TestRunner.class);

  private int exitCode;

  @Autowired
  TaskService taskService;

  @Override
  public void run(ApplicationArguments args) {
    // var tasks = taskService.findAll();
    // for (var task : tasks) {
    //   System.out.println(String.format("Task: {}", task));
    // }

    // var newTask = new Task();
    // newTask.setTitle("hoge");
    // newTask.setDescription("fuga");
    // newTask.setCreated_at(LocalDateTime.now());
    // newTask.setUpdated_at(LocalDateTime.now());
    // newTask.setDueDate(LocalDateTime.now().plusMonths(5));

    // taskService.save(newTask);

    // tasks = taskService.findAll();
    // for (var task : tasks) {
    //   System.out.println(String.format("Task: {}", task));
    // }
  }

  /**
   * Provides exit code for application.
   * <p>
   * If not present this implementation, when normal end always return 0, when
   * abnormal end return exit code that determine exception handling.
   * </p>
   */
  @Override
  public int getExitCode() {
    return exitCode;
  }

  /**
   * Implements processing that map exception to exit code.
   * <p>
   * If not present this implementation, always exit with 1.
   * </p>
   */
  @Override
  public int getExitCode(Throwable exception) {
    return exception.getCause() != null && exception.getCause() instanceof SpelEvaluationException ? 3 : 1;
  }

}