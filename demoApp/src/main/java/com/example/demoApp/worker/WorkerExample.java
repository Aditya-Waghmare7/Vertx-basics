package com.example.demoApp.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerExample extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(WorkerExample.class);

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());
  }

  public void start(final Promise<Void> startPromise) throws Exception{
    vertx.deployVerticle(new WorkerVerticle(),
      new DeploymentOptions()
        .setWorker(true)
        .setWorkerPoolSize(1)
        .setWorkerPoolName("my-worker-verticle")
    );
    startPromise.complete();
    executeBlockingCode();

  }

  private void executeBlockingCode() {
    vertx.executeBlocking(event -> {
      LOG.debug("Executing blocking code");
      try{
        //Blocking code executed inside Worker thread
        Thread.sleep(5000);
        event.complete();
        //event.fail("Force Fail!");
      }
      catch (Exception e){
        LOG.error("Failed: ",e);
        event.fail(e);
      }
    },
      //Callback to ensure Blocking code execution is completed.
      result -> {
        if(result.succeeded()){
          LOG.debug("Blocking code done.");
        }
        else{
          LOG.debug("Blocking code failed due to "+ result.cause());
        }
    });
  }
}
