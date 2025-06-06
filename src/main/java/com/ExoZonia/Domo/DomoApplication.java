package com.ExoZonia.Domo;

import com.ExoZonia.Domo.model.Usuario;
import com.ExoZonia.Domo.service.UsuarioService;
import com.ExoZonia.Domo.service.BrainyacLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomoApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BrainyacLearningService brainyacLearningService;

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

		// Ensinar o Brainyac (salvar no banco)

//
		brainyacLearningService.ensinar("qual seu nome?", "Meu nome é Brainyac.");
		brainyacLearningService.ensinar("onde voce mora?", "Eu vivo na nuvem, em servidores seguros.");

		// Consultar e imprimir as respostas
		System.out.println(" brainyacLearningService thiago teste: " + brainyacLearningService);
		System.out.println("Pergunta: qual seu nome?");
		System.out.println("Resposta: " + brainyacLearningService.consultar("qual seu nome?"));

		System.out.println("Pergunta: onde voce mora?");
		System.out.println("Resposta: " + brainyacLearningService.consultar("onde voce mora?"));
	}
}
