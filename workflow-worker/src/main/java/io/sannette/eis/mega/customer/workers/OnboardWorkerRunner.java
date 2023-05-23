package io.sannette.eis.mega.customer.workers;

import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import io.sannette.eis.mega.customer.activities.CustomerOnboardActivitiesImpl;
import io.sannette.eis.mega.customer.workflows.CustomerOnboardWorkflowImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class OnboardWorkerRunner implements ApplicationRunner {

    @Autowired
    private String taskListQueueName;

    @Autowired
    private WorkerFactory workerFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Worker worker = workerFactory.newWorker(taskListQueueName);
        worker.registerWorkflowImplementationTypes(CustomerOnboardWorkflowImpl.class);
        worker.registerActivitiesImplementations(new CustomerOnboardActivitiesImpl());
        workerFactory.start();
    }
}
