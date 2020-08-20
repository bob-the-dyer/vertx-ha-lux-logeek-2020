package ru.kupchinolabs.vertx.vertx_ha_lux_logeek_2020.cluster_classloader;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;

abstract public class ByteCodeSharingVerticle extends AbstractVerticle {

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
        VertxClusterByteCodeStorage.storeClass(vertx, this);
        VertxDeploymentManagerHacker.hack(vertx);
    }

}
