package io.sannette.eis.mega.customer.workflows;

import com.uber.cadence.workflow.QueryMethod;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;
import io.sannette.eis.mega.common.ServiceConstants;
import io.sannette.eis.mega.customer.models.Customer;

public interface ICustomerOnboardWorkflow {

    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 10, taskList = ServiceConstants.CUSTOMER_ONBOARDING_TASK_LIST)
    String onboardCustomer(Customer customer);

    @SignalMethod
    void approveCustomerOnboard();

    @SignalMethod
    void rejectCustomerOnboard();

    @QueryMethod
    String getCustomerOnboardingStatus();
}
