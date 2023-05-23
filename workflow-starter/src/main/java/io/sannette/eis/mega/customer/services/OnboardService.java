package io.sannette.eis.mega.customer.services;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.DuplicateWorkflowException;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import io.sannette.eis.mega.customer.models.Customer;
import io.sannette.eis.mega.customer.workflows.ICustomerOnboardWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;

@Service
public class OnboardService {

    @Autowired
    public WorkflowClient getWorkflowClient;

    @Autowired
    private String getTaskListQueue;

    @Autowired
    private Integer getWorkflowMaxDuration;

    public String createCustomer(Customer customer) {
        String newCustomerId = generateNewCustomerId();
        customer.setId(newCustomerId);
        ICustomerOnboardWorkflow customerOnboardWorkflow =
                getWorkflowClient.newWorkflowStub(ICustomerOnboardWorkflow.class, getWorkflowOptions(newCustomerId));
        try {
            WorkflowExecution execution = WorkflowClient.start(customerOnboardWorkflow::onboardCustomer, customer);
        } catch (DuplicateWorkflowException e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
        }
        return newCustomerId;
        //  return this.getCustomerOnboardStatus(getCustomerWorkflowId(customer.getId()));
    }

    public String approveCustomer(String customerId) {
        System.out.println("Creating Customer");
        ICustomerOnboardWorkflow customerOnboardWorkflow =
                getWorkflowClient.newWorkflowStub(ICustomerOnboardWorkflow.class,
                        getCustomerWorkflowId(customerId));
        try {
            System.out.println("?????????? Comming");
            customerOnboardWorkflow.approveCustomerOnboard();
            System.out.println("?????????? Came");
        } catch (DuplicateWorkflowException e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
        }
        return "APPROVED";
       // return this.getCustomerOnboardStatus(getCustomerWorkflowId(customerId));
    }

    public String rejectCustomer(String customerId) {
        System.out.println("Creating Customer");
        ICustomerOnboardWorkflow customerOnboardWorkflow =
                getWorkflowClient.newWorkflowStub(ICustomerOnboardWorkflow.class,
                        getCustomerWorkflowId(customerId));
        try {
            customerOnboardWorkflow.rejectCustomerOnboard();
        } catch (DuplicateWorkflowException e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
        }
        return "REJECTED";
       // return this.getCustomerOnboardStatus(getCustomerWorkflowId(customerId));
    }

    public String getCustomerOnboardStatus(String customerId) {

        ICustomerOnboardWorkflow customerOnboardWorkflow =
                getWorkflowClient.newWorkflowStub(ICustomerOnboardWorkflow.class,
                        getCustomerWorkflowId(customerId));
        System.out.println("Till this its fine");
        try {
            return customerOnboardWorkflow.getCustomerOnboardingStatus();
        } catch (DuplicateWorkflowException e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    private WorkflowOptions getWorkflowOptions(String newCustomerId) {
        return new WorkflowOptions.Builder()
                .setTaskList(getTaskListQueue)
                .setWorkflowId(getCustomerWorkflowId(newCustomerId))
                .setExecutionStartToCloseTimeout(Duration.ofDays(getWorkflowMaxDuration.intValue()))
                .build();
    }

    private String generateNewCustomerId() {
        LocalTime localTime = LocalTime.now();
        return localTime.getHour() + "-" + localTime.getMinute() + "-" + localTime.getSecond();
    }

    private String getCustomerWorkflowId(String customerId) {
        return "customer-" + customerId;
    }

}
