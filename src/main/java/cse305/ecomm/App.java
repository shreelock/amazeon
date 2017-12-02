package cse305.ecomm;

import cse305.ecomm.basicauth.AppAuthenticator;
import cse305.ecomm.basicauth.AppAuthorizer;
import cse305.ecomm.basicauth.User;
import cse305.ecomm.controller.AmzRESTController;
import cse305.ecomm.healthcheck.AppHealthCheck;
import cse305.ecomm.healthcheck.HealthCheckController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
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
		
		//Setup Basic Security
		e.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new AppAuthenticator())
                .setAuthorizer(new AppAuthorizer())
                .setRealm("App Security")
                .buildAuthFilter()));
        e.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        e.jersey().register(RolesAllowedDynamicFeature.class);
	}

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}