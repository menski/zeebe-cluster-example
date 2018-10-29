## Start in-memory cluster

```
mvn compile exec:java -Dexec.mainClass=io.zeebe.example.Cluster
```

## Deploy workflow

```
mvn compile exec:java -Dexec.mainClass=io.zeebe.example.DeployWorkflow
```

## Start workflow

```
mvn compile exec:java -Dexec.mainClass=io.zeebe.example.StartWorkflow
```


## Job workers

```
mvn compile exec:java -Dexec.mainClass=io.zeebe.example.JobWorkers
```
