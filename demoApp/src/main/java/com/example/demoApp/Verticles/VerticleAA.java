package com.example.demoApp.Verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class VerticleAA extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(VerticleAA.class);
  @Override
  public void start(final Promise<Void> startPromise)throws Exception{
    LOG.debug("Start {}"+getClass().getName());
    //To Signal verticle was complete
    startPromise.complete();
  }

  //Undeploy Verticle
  @Override
  public void stop(final Promise<Void> stopPromise) throws Exception {
    LOG.debug("Stop {}"+ getClass().getName());
    stopPromise.complete();
  }
}
