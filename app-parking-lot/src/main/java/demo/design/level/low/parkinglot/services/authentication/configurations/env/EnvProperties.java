package demo.design.level.low.parkinglot.services.authentication.configurations.env;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class EnvProperties {

	private static Config defaultConf = ConfigFactory.load();
	private static Config envConf     = ConfigFactory.systemEnvironment();
	public  static Config config      = defaultConf.withFallback(envConf);

}