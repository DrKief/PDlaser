package pdl.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableAsync
public class BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  @Bean(name = "taskExecutor")
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    int processors = Runtime.getRuntime().availableProcessors();
    executor.setCorePoolSize(processors);
    executor.setMaxPoolSize(processors);
    executor.setQueueCapacity(10); // Strict queue limits memory trap
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // Enforce backpressure
    executor.setThreadNamePrefix("AsyncProcessor-");
    executor.initialize();
    return executor;
  }
}