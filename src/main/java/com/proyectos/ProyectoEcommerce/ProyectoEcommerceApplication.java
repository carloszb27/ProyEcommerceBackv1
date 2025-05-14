package com.proyectos.ProyectoEcommerce;

//import com.proyectos.ProyectoEcommerce.persistence.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoEcommerceApplication.class, args);
	}

/*
	@Autowired
	CarritoItemRepository carritoItemRepository;

	@Autowired
	CarritoRepository carritoRepository;

	@Autowired
	DetalleUsuarioRepository detalleUsuarioRepository;

	@Autowired
	LoteRepository loteRepository;

	@Autowired
	OrdenVentaRepository ordenVentaRepository;

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	ProveedorRepository proveedorRepository;

	@Autowired
	UserRepository userRepository;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			StringBuilder sb = new StringBuilder();

			String mensaje = sb.append("Lista de carrito Items:")
							.append(carritoItemRepository.findAll())
					.append("\n Lista de carrito")
					.append(carritoRepository.findAll())
					.append("\n Lista de detalle usuario")
					.append(detalleUsuarioRepository.findAll())
					.append("\n Lista de lote")
					.append(loteRepository.findAll())
					.append("\n Lista de ordenVenta")
					.append(ordenVentaRepository.findAll())
					.append("\n Lista de producto")
					.append(productoRepository.findAll())
					.append("\n Lista de Proveedor")
					.append(proveedorRepository.findAll())
					.append("\n Lista de User")
					.append(userRepository.findAll())
					.toString();

			System.out.println(mensaje);

        };
    }
	*/
}
