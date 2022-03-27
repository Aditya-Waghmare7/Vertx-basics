package com.example.demoApp.eventBus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponseExample {

  private static final Logger LOG = LoggerFactory.getLogger(RequestVerticle.class);
  static final String ADDRESS = "my.request.address";

  public static void main(String[] args) {
    var vertx = Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
    vertx.deployVerticle(new ResponseVerticle());
  }

  static class RequestVerticle extends AbstractVerticle{

    @Override
    public void start(final Promise<Void> startPromise) throws Exception{
      startPromise.complete();
      final String message = "Hello World!";
      LOG.debug("Sending: {}",message);
      var eventBus = vertx.eventBus();
      eventBus.<String>request(ADDRESS,message, reply -> {
        LOG.debug("Response : {}",reply.result().body());
      });
    }
  }

  static class ResponseVerticle extends AbstractVerticle{

    @Override
    public void start(final Promise<Void> startPromise) throws Exception{
      startPromise.complete();
      vertx.eventBus().<String>consumer(ADDRESS,message -> {
        LOG.debug("Received Message {}",message.body());
        message.reply("Received your message, Thanks!");
      });
    }
  }
}
