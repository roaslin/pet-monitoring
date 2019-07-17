package com.pets.order.interfaces.web;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Histogram;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GetOrderController {

    private Histogram histogram;

    public GetOrderController(CollectorRegistry collectorRegistry) {
        this.histogram = Histogram.build()
                .name("get_orders_request_latency")
                .help("Get orders request latency")
                .register(collectorRegistry);
    }

    @GetMapping("/orders")
    public void getOrders() {

        long leftLimit = 100L;
        long rightLimit = 1000L;
        long latency = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));

        Histogram.Timer histogramTimer = histogram.startTimer();
        try {

            Thread.sleep(latency);
        } catch (InterruptedException e) {
            log.error("Responding orders" + e.getMessage());
        } finally {
            histogramTimer.observeDuration();
        }

        log.info("Responding orders latency [" + latency + "]");
        log.info("Responding orders...");

    }
}
