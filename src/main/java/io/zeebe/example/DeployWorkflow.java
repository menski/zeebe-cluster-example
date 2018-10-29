package io.zeebe.example;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.ZeebeClientBuilder;
import io.zeebe.client.api.events.DeploymentEvent;

public class DeployWorkflow {

  public static void main(String[] args) {
    ZeebeClientBuilder clientBuilder = ZeebeClient
        .newClientBuilder()
        .brokerContactPoint("localhost:36500");

    try (ZeebeClient client = clientBuilder.build()) {
      DeploymentEvent deploymentEvent = client.workflowClient()
          .newDeployCommand()
          .addResourceFromClasspath("./io/zeebe/example/order-process.bpmn")
          .send().join();

      System.out.println(deploymentEvent);
    }
  }

}
