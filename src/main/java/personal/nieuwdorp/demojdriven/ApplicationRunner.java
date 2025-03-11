package personal.nieuwdorp.demojdriven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collection;

@Service
@Slf4j
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {
    private final TaskScheduler taskExecutor;
    private final TaskDecorator taskDecorator;
    private final Collection<Runnable> runnables;

    public ApplicationRunner(TaskScheduler taskExecutor, TaskDecorator taskDecorator, Collection<Runnable> runnables) {
        this.taskExecutor = taskExecutor;
        this.taskDecorator = taskDecorator;
        this.runnables = runnables;
    }

    @Override
    public void run(ApplicationArguments args) {
        for (Runnable runnable : runnables) {
            taskExecutor.scheduleAtFixedRate(taskDecorator.decorate(runnable), Duration.ofSeconds(30));
        }
    }
}
