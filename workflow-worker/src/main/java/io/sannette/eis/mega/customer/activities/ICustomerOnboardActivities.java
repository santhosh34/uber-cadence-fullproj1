package io.sannette.eis.mega.customer.activities;

import com.uber.cadence.activity.ActivityMethod;
import io.sannette.eis.mega.customer.models.Customer;

public interface ICustomerOnboardActivities {

    @ActivityMethod(scheduleToCloseTimeoutSeconds = 2)
    Customer draftCustomerOnboard(Customer customer);

    @ActivityMethod(scheduleToCloseTimeoutSeconds = 2)
    Customer review1CustomerOnboard(Customer customer);

    @ActivityMethod(scheduleToCloseTimeoutSeconds = 2)
    Customer review2CustomerOnboard(Customer customer);

    @ActivityMethod(scheduleToCloseTimeoutSeconds = 2)
    Customer sendWelcomeEmail(Customer customer);

}
