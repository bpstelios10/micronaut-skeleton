package framework.samples.micronaut.web.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/private")
public class PrivateController {
    @Get("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public String status() {
        return "OK";
    }
}
