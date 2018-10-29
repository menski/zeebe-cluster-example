package io.zeebe.example;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.ZeebeClientBuilder;
import io.zeebe.client.api.events.WorkflowInstanceEvent;
import java.util.Collections;
import java.util.UUID;

public class StartWorkflow {

  public static void main(String[] args) {
    ZeebeClientBuilder clientBuilder = ZeebeClient
        .newClientBuilder()
        .brokerContactPoint("localhost:36500");

    try (ZeebeClient client = clientBuilder.build()) {
      String orderId = UUID.randomUUID().toString();

      WorkflowInstanceEvent workflowInstance = client.workflowClient()
          .newCreateInstanceCommand()
          .bpmnProcessId("order-process")
          .latestVersion()
          .payload(Collections.singletonMap("orderId", orderId))
          .send()
          .join();

      System.out.println("Order ID: " + orderId);
      System.out.println(workflowInstance);
    }
  }

}
