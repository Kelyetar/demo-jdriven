package personal.nieuwdorp.demojdriven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {
    private final TaskExecutor taskExecutor;
    private final Collection<Runnable> runnables;

    public ApplicationRunner(SchedulingTaskExecutor taskExecutor, Collection<Runnable> runnables) {
        this.taskExecutor = taskExecutor;
        this.runnables = runnables;
    }

    @Override
    public void run(ApplicationArguments args) {
        for (Runnable runnable : runnables) {
            taskExecutor.execute(runnable);
        }
    }
}
