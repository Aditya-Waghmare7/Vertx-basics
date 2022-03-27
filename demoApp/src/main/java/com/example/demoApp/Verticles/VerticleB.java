package com.example.demoApp.Verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class VerticleB extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(VerticleB.class);

  @Override
  public void start(final Promise<Void> startPromise)throws Exception{
    LOG.debug("Start "+getClass().getName());
    startPromise.complete();
  }
}
