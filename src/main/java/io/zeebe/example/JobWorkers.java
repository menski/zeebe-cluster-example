package io.zeebe.example;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.ZeebeClientBuilder;
import io.zeebe.client.api.clients.JobClient;
import io.zeebe.client.api.response.ActivatedJob;
import java.time.Duration;
import java.util.Scanner;

public class JobWorkers {

  public static void main(String[] args) {
    ZeebeClientBuilder clientBuilder = ZeebeClient
        .newClientBuilder()
        .brokerContactPoint("localhost:36500");

    try (ZeebeClient client = clientBuilder.build()) {
      client.jobClient()
          .newWorker()
          .jobType("payment-service")
          .handler(JobWorkers::handleJob)
          .name("payment-worker")
          .pollInterval(Duration.ofSeconds(1))
          .open();

      client.jobClient()
          .newWorker()
          .jobType("inventory-service")
          .handler(JobWorkers::handleJob)
          .name("inventory-worker")
          .pollInterval(Duration.ofSeconds(1))
          .open();

      client.jobClient()
          .newWorker()
          .jobType("shipment-service")
          .handler(JobWorkers::handleJob)
          .name("shipment-worker")
          .pollInterval(Duration.ofSeconds(1))
          .open();

      new Scanner(System.in).nextLine();
    }
  }


  private static void handleJob(JobClient jobClient, ActivatedJob job) {
    jobClient.newCompleteCommand(job.getKey()).send().join();
    System.out.println("Completed job " + job.getKey() + " of type " + job.getType() + " with payload " + job.getPayload());
  }
}
