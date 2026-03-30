package pdl.backend;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Main entry point for the Spring Boot Backend Application.
 * Configures the application and enables asynchronous processing capabilities.
 */
@SpringBootApplication
@EnableAsync
public class Application implements AsyncConfigurer {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Configures the ThreadPool for background asynchronous tasks (e.g., image processing).
   *
   * @return A custom Executor configured based on available CPU cores.
   */
  @Bean(name = "taskExecutor")
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    int processors = Runtime.getRuntime().availableProcessors();

    // Set pool sizes dynamically based on the host system's hardware
    executor.setCorePoolSize(processors);
    executor.setMaxPoolSize(processors);
    executor.setQueueCapacity(10);

    // If the queue is full, the task runs in the caller's thread rather than being rejected
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.setThreadNamePrefix("AsyncWorker-");
    executor.initialize();

    return executor;
  }

  /**
   * Handles uncaught exceptions occurring in threads running @Async methods.
   */
  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return (throwable, method, params) -> {
      log.error(
        "CRITICAL: Unhandled async exception in method: {} with parameters: {}",
        method.getName(),
        params,
        throwable
      );
    };
  }
}
