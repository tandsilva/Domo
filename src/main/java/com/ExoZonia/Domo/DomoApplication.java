package com.ExoZonia.Domo;

import com.ExoZonia.Domo.model.Usuario;
import com.ExoZonia.Domo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomoApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(DomoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome("Fernanda");
		usuario.setGamerTag("ExoMistes");
		usuario.setEmail("Lucas@ss.com");
		usuario.setTelefone("11999999999");
		usuario.setEndereco("Rua das Galáxias, 49");
		usuario.setCidade("araraCity");
		usuario.setEstado("SP");
		usuario.setPais("Brasil");

		usuarioService.salvar(usuario);

		System.out.println("🔥 Usuário cadastrado com sucesso!");
	}
}
