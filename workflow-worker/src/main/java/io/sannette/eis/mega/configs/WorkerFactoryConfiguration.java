package io.sannette.eis.mega.configs;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerFactoryConfiguration {

    @Autowired
    private WorkflowClient workflowClient;

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient){
        return WorkerFactory.newInstance(workflowClient);
    }
}
