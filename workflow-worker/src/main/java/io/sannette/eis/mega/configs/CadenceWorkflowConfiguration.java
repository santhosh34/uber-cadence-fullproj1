package io.sannette.eis.mega.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadenceWorkflowConfiguration {

    @Value("${cadence.domain}")
    private String domain;

    @Value("${cadence.task-list-queue}")
    private String taskListQueueName;

    @Value("${cadence.workflow-max-duration}")
    private String workflowMaxDuration;

    @Value("${cadence.schedule-to-close-timeout-seconds}")
    private String scheduleToCloseTimeoutSeconds;

    @Bean
    public String cadenceDomain(){
        return domain;
    }

    @Bean
    public String taskListQueueName(){
        return taskListQueueName;
    }

    @Bean
    public Integer workflowMaxDuration(){
        return Integer.parseInt(workflowMaxDuration);
    }

    @Bean
    public Integer scheduleToCloseTimeoutSeconds(){
        return Integer.parseInt(scheduleToCloseTimeoutSeconds);
    }

}
