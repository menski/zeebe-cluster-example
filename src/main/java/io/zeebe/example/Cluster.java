package io.zeebe.example;

import io.zeebe.broker.Broker;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Cluster {

  public static void main(String[] args) throws IOException {
    InputStream brokerCfg1 = Cluster.class.getResourceAsStream("zeebe.1.cfg.toml");
    Broker broker1 = new Broker(brokerCfg1, createTempDir(1), null);

    InputStream brokerCfg2 = Cluster.class.getResourceAsStream("zeebe.2.cfg.toml");
    Broker broker2 = new Broker(brokerCfg2, createTempDir(2), null);

    new Scanner(System.in).nextLine();
  }

  private static String createTempDir(int id) throws IOException {
    File dir = File.createTempFile("zeebe-broker-", "-" + id);
    dir.delete();
    dir.mkdirs();
    dir.deleteOnExit();
    return dir.getAbsolutePath();
  }
}
