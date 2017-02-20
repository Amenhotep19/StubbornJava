package com.stubbornjava.common.undertow.handlers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stubbornjava.common.Metrics;
import com.stubbornjava.common.undertow.Exchange;
import com.stubbornjava.undertow.handlers.accesslog.Slf4jAccessLogReceiver;

import io.undertow.Handlers;
import io.undertow.predicate.Predicates;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.ExceptionHandler;
import io.undertow.server.handlers.accesslog.AccessLogHandler;
import io.undertow.server.handlers.encoding.ContentEncodingRepository;
import io.undertow.server.handlers.encoding.EncodingHandler;
import io.undertow.server.handlers.encoding.GzipEncodingProvider;

public class CustomHandlers {
    private static final Logger logger = LoggerFactory.getLogger(CustomHandlers.class);

    public static AccessLogHandler accessLog(HttpHandler next, Logger logger) {
        return new AccessLogHandler(next, new Slf4jAccessLogReceiver(logger), "combined", CustomHandlers.class.getClassLoader());
    }

    public static AccessLogHandler accessLog(HttpHandler next) {
        final Logger logger = LoggerFactory.getLogger("com.stubbornjava.accesslog");
        return accessLog(next, logger);
    }

    public static HttpHandler gzip(HttpHandler next) {
        return new EncodingHandler(new ContentEncodingRepository()
                  .addEncodingHandler("gzip",
                      // This 1000 is a priority, not exactly sure what it does.
                      new GzipEncodingProvider(), 1000,
                      // Anything under a content-length of 20 will not be gzipped
                      Predicates.parse("max-content-size(20)")))
                  .setNext(next);
    }

    public static StatusCodeHandler statusCodeMetrics(HttpHandler next) {
        return new StatusCodeHandler(next, "status.code");
    }

    public static TimingHttpHandler timed(String name, HttpHandler next) {
        return new TimingHttpHandler(next, name);
    }

    public static void metrics(HttpServerExchange exchange) {
        Exchange.body().sendJson(exchange, Metrics.registry());
    }

    public static ExceptionHandler exception(HttpHandler handler) {
        return Handlers.exceptionHandler((HttpServerExchange exchange) -> {
            try {
                handler.handleRequest(exchange);
            } catch (Throwable th) {
                logger.error("exception thrown at " + exchange.getRequestURI(), th);
                throw th;
            }
        });
    }
}
