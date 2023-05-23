package io.sannette.eis.mega.configs;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerOnboardWorkflow {

    @Autowired
    private WorkflowClient workflowClient;



}
