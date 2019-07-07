package com.pets.order.interfaces.web;

import io.prometheus.client.Histogram;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PostOrderController {

    static final Histogram requestLatency = Histogram.build()
            .name("post_orders_request_latency")
            .help("Post orders request latency")
            .register();

    @PostMapping("orders")
    public void postOrder(@RequestBody OrderCommand newOrder) {

        long leftLimit = 100L;
        long rightLimit = 2000L;
        long latency = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        Histogram.Timer requestLatencyTimer = requestLatency.startTimer();

        try {

            Thread.sleep(latency);
        } catch (InterruptedException e) {
            log.error("Posting order" + e.getMessage());
        } finally {

            requestLatencyTimer.observeDuration();
        }

        log.info("Posting order latency [" + latency + "]");
        log.info("Post order " + newOrder.toString());
    }
}
