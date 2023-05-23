package io.sannette.eis.mega.configs;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.internal.compatibility.Thrift2ProtoAdapter;
import com.uber.cadence.internal.compatibility.proto.serviceclient.IGrpcServiceStubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CadenceWorkflowClientConfiguration {

    @Autowired
    public String cadenceDomain;

    @Bean
    public WorkflowClient getWorkflowClient(Thrift2ProtoAdapter thrift2ProtoAdapter,WorkflowClientOptions workflowClientOptions){
        return WorkflowClient.newInstance(thrift2ProtoAdapter,workflowClientOptions);
    }

    @Bean
    public WorkflowClientOptions getWorkflowClientOptions(){
        return WorkflowClientOptions
                .newBuilder()
                .setDomain(cadenceDomain).build();
    }

    @Bean
    public Thrift2ProtoAdapter getThrift2ProtoAdapter(){
        return new Thrift2ProtoAdapter(getIGrpcServiceStubs());
    }

    @Bean
    public IGrpcServiceStubs getIGrpcServiceStubs(){
        return IGrpcServiceStubs.newInstance();
    }

}

//    @Bean
//    public Thrift2ProtoAdapter getThrift2ProtoAdapter(){
//        return new Thrift2ProtoAdapter(IGrpcServiceStubs.newInstance());
//    }
