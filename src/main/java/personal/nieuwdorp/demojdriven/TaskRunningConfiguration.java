package personal.nieuwdorp.demojdriven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Slf4j
public class TaskRunningConfiguration {
    @Bean
    TaskDecorator taskDecorator() {
        return new LoggingTaskDecorator();
    }

    private static class LoggingTaskDecorator implements TaskDecorator {
        @Override
        public Runnable decorate(Runnable runnable) {
            return () -> {
                log.info("Starting execution of {}", runnable.getClass().getSimpleName());
                try {
                    runnable.run();
                    log.info("Finished execution of {}", runnable.getClass().getSimpleName());
                } catch (Exception e) {
                    log.warn("Exception during runnable execution", e);
                }
            };
        }
    }
}
