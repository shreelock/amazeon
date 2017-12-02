package cse305.ecomm;

import cse305.ecomm.controller.AmzRESTController;
import cse305.ecomm.healthcheck.AppHealthCheck;
import cse305.ecomm.healthcheck.HealthCheckController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;

public class App extends Application<Configuration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	@Override
	public void initialize(Bootstrap<Configuration> b) {
	}

	@Override
	public void run(Configuration c, Environment e) throws Exception 
	{
		LOGGER.info("Registering REST resources");
		
		e.jersey().register(new AmzRESTController(e.getValidator()));

		final Client client = new JerseyClientBuilder(e)
				.build("RESTClient");

		// Application health check
		e.healthChecks().register("APIHealthCheck", new AppHealthCheck(client));

		// Run multiple health checks
		e.jersey().register(new HealthCheckController(e.healthChecks()));

	}

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}