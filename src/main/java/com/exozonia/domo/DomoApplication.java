package com.exozonia.domo;

import com.exozonia.domo.model.Usuario;
import com.exozonia.domo.service.UsuarioService;
import com.exozonia.domo.service.BrainyacLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "com.exozonia.domo.repository")
public class DomoApplication implements CommandLineRunner {

	private final UsuarioService usuarioService;
	private final BrainyacLearningService brainyacLearningService;

	@Autowired
	public DomoApplication(UsuarioService usuarioService, BrainyacLearningService brainyacLearningService) {
		this.usuarioService = usuarioService;
		this.brainyacLearningService = brainyacLearningService;
	}

	public static void main(String[] args) {
		System.out.println("🚀 Iniciando aplicação Spring Boot...");
		SpringApplication.run(DomoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("✅ Método run() executado!");

		if (usuarioService == null) {
			System.err.println("❌ usuarioService está NULL!");
		}
		if (brainyacLearningService == null) {
			System.err.println("❌ brainyacLearningService está NULL!");
		}

		// Cadastra usuário
//		Usuario usuario = new Usuario();
//		usuario.setNome("Fernanda");
//		usuario.setGamerTag("ExoMistes");
//		usuario.setEmail("Lucas@ss.com");
//		usuario.setTelefone("11999999999");
//		usuario.setEndereco("Rua das Galáxias, 49");
//		usuario.setCidade("araraCity");
//		usuario.setEstado("SP");
//		usuario.setPais("Brasil");
//
//		usuarioService.salvar(usuario);
//		System.out.println("🔥 Usuário cadastrado com sucesso!");

		// Ensinar o Brainyac (salvar no banco)
		brainyacLearningService.ensinar("qual eh seu nome ?", "Brainyac ?");
		brainyacLearningService.ensinar("Como se sente", "feliz.");
//
////		// Consultar e imprimir as respostas
//		System.out.println(" brainyacLearningService thiago teste: " + brainyacLearningService);
////		System.out.println("Pergunta: qual seu nome?");

		brainyacLearningService.ensinar("Qual carro vc gosta?", "Porche!");
		System.out.println("Resposta: " + brainyacLearningService.consultar("Qual carro vc gosta?"));


		//System.out.println("Resposta: " + brainyacLearningService.consultar("banana?"));



	}
}
