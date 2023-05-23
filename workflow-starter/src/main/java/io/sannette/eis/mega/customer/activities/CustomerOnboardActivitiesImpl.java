package io.sannette.eis.mega.customer.activities;

import io.sannette.eis.mega.customer.models.Customer;

public class CustomerOnboardActivitiesImpl implements ICustomerOnboardActivities{
    @Override
    public Customer draftCustomerOnboard(Customer customer) {
        customer.setName(customer.getName());
        customer.setStatus("Draft");
        return customer;
    }

    @Override
    public Customer review1CustomerOnboard(Customer customer) {
        customer.setStatus("Reviewed1");
        customer.setReviewComment("Need to correct grammatical Mistakes");
        return customer;
    }

    @Override
    public Customer review2CustomerOnboard(Customer customer) {
        customer.setStatus("Reviewed2");// Later please remove.
        customer.setReviewComment("Need to correct semantic Mistakes");
        return customer;
    }

    @Override
    public Customer sendWelcomeEmail(Customer customer) {
        customer.setStatus("Onboarded");
        return customer;
    }
}
