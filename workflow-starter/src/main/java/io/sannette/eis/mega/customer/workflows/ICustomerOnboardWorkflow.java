package io.sannette.eis.mega.customer.workflows;

import io.sannette.eis.mega.configs.CadenceWorkflowConfiguration;
import io.sannette.eis.mega.customer.models.Customer;
import com.uber.cadence.workflow.QueryMethod;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public interface ICustomerOnboardWorkflow {

    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 10, taskList = "${cadence.task-list-queue}")
    String onboardCustomer(Customer customer);

    @SignalMethod
    void approveCustomerOnboard();

    @SignalMethod
    void rejectCustomerOnboard();

    @QueryMethod
    String getCustomerOnboardingStatus();
}
