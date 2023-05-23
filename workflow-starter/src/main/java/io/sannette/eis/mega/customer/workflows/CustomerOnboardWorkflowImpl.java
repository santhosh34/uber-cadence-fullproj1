package io.sannette.eis.mega.customer.workflows;

import io.sannette.eis.mega.customer.activities.ICustomerOnboardActivities;
import io.sannette.eis.mega.customer.models.Customer;
import com.uber.cadence.workflow.Workflow;

public class CustomerOnboardWorkflowImpl implements ICustomerOnboardWorkflow {

    private Customer customer;

    private final ICustomerOnboardActivities activities =
            Workflow.newActivityStub(ICustomerOnboardActivities.class);

    @Override
    public String onboardCustomer(Customer customer) {

        this.customer = customer;

        this.customer = activities.draftCustomerOnboard(this.customer);
        if(this.customer.getStatus().equalsIgnoreCase("DRAFT")) {
            this.customer = activities.review1CustomerOnboard(this.customer);
        }
        Workflow.await(() -> "APPROVED".equalsIgnoreCase(this.customer.getStatus()));
        this.customer = activities.sendWelcomeEmail(this.customer);
        return "customer kaaka id: " + this.customer.getId() + ":Status:" + this.customer.getStatus();
    }

    @Override
    public void approveCustomerOnboard() {
        this.customer.setStatus("APPROVED");
    }

    @Override
    public void rejectCustomerOnboard() {
        this.customer.setStatus("REJECTED");
    }

    @Override
    public String getCustomerOnboardingStatus() {
        return this.customer.getStatus();
    }
}
