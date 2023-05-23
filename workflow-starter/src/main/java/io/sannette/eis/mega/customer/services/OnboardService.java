package io.sannette.eis.mega.customer.services;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.DuplicateWorkflowException;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import io.sannette.eis.mega.configs.CadenceWorkflowClientConfiguration;
import io.sannette.eis.mega.customer.models.Customer;
import io.sannette.eis.mega.customer.workflows.ICustomerOnboardWorkflow;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Calendar;

@Service
public class OnboardService {

    @Autowired
    public WorkflowClient getWorkflowClient;

    @Autowired
    private String getTaskListQueue;

    @Autowired
    private Integer getWorkflowMaxDuration;

    public String createCustomer(Customer customer) {
        System.out.println("Creating Customer");
        ICustomerOnboardWorkflow customerOnboardWorkflow =
                getWorkflowClient.newWorkflowStub(ICustomerOnboardWorkflow.class, getWorkflowOptions());

        try {
            WorkflowExecution execution = WorkflowClient.start(customerOnboardWorkflow::onboardCustomer, customer);
            System.out.println("started workflow execution" + execution);
        }catch(DuplicateWorkflowException e) {
            e.printStackTrace();
            System.out.println("Exception: "+e.getMessage());
        }
        System.out.println(getWorkflowClient);
        System.out.println("Created Customer");
        return "sth needed";
    }

    private WorkflowOptions getWorkflowOptions() {
        return new WorkflowOptions.Builder()
                .setTaskList(getTaskListQueue)
                .setWorkflowId("customer-" + generateNewCustomerId())
                .setExecutionStartToCloseTimeout(Duration.ofDays(getWorkflowMaxDuration.intValue()))
                .build();
    }

    private String generateNewCustomerId() {
        LocalTime localTime = LocalTime.now();
        return localTime.getHour() + "-" + localTime.getMinute() + "-" + localTime.getSecond();
    }

}
