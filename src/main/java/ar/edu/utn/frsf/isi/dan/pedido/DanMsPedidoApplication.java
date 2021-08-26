package ar.edu.utn.frsf.isi.dan.pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Leandro Heraldo Tricarique
 * @author Francisco Tomas Gautero
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class DanMsPedidoApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(DanMsPedidoApplication.class, args);
	}
}
