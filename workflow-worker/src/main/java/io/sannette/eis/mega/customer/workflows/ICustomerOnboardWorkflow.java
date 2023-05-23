package io.sannette.eis.mega.customer.workflows;

import com.uber.cadence.workflow.QueryMethod;
import com.uber.cadence.workflow.SignalMethod;
import com.uber.cadence.workflow.WorkflowMethod;
import io.sannette.eis.mega.customer.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public interface ICustomerOnboardWorkflow {

    @WorkflowMethod
    String onboardCustomer(Customer customer);

    @SignalMethod
    void approveCustomerOnboard();

    @SignalMethod
    void rejectCustomerOnboard();

    @QueryMethod
    String getCustomerOnboardingStatus();
}
