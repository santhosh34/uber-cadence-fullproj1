package io.sannette.eis.mega.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadenceWorkflowConfiguration {

    @Value("${cadence.domain}")
    private String domain;

    @Value("${cadence.task-list-queue}")
    private String taskListQueue;

    @Value("${cadence.workflow-max-duration}")
    private String workflowMaxDuration;

    @Bean
    public String cadenceDomain(){
        return domain;
    }

    @Bean
    public String getTaskListQueue(){
        return taskListQueue;
    }

    @Bean
    public Integer getWorkflowMaxDuration(){
        return Integer.parseInt(workflowMaxDuration);
    }

}
