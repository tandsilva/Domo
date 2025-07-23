package com.exozonia.domo.service;

// Importações dos DTOs, modelos e repositórios
import com.exozonia.domo.dto.UsuarioDto;
import com.exozonia.domo.dto.UsuarioUpdateDto;
import com.exozonia.domo.mapper.UsuarioMapper;
import com.exozonia.domo.model.Login;
import com.exozonia.domo.model.Usuario;
import com.exozonia.domo.model.Weapon;
import com.exozonia.domo.repository.UsuarioRepository;
import com.exozonia.domo.repository.WeaponRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço responsável pelas regras de negócio relacionadas ao usuário.
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // Repositório de armas (não está sendo injetado corretamente ainda)
    private WeaponRepository weaponRepository;

    // DTO declarado mas não utilizado (pode ser removido)
    private UsuarioDto dto;

    /**
     * Salva um novo usuário após validações.
     */
    public Usuario salvar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        // Verifica se já existe usuário com o mesmo nome ou email
        if (repository.existsByNome(usuario.getNome())) {
            throw new IllegalArgumentException("Nome já cadastrado");
        }
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        return repository.save(usuario);
    }

    /**
     * Retorna todos os usuários cadastrados.
     */
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    /**
     * Busca um usuário pelo ID.
     */
    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Deleta um usuário pelo ID.
     */
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    /**
     * Atualiza os dados de um usuário com base em um DTO de atualização.
     */
    public Usuario atualizar(Long id, UsuarioUpdateDto dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Atualiza apenas os campos não nulos
        UsuarioMapper.merge(usuario, dto);

        return repository.save(usuario);
    }

    // Injeção duplicada do repositório de usuário (pode ser unificada com o de cima)
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registra um login para o usuário, adicionando um novo objeto Login à lista.
     */
    public void registrarLogin(Usuario usuario) {
        if (usuario.getLogins() == null) {
            usuario.setLogins(new ArrayList<>());
        }

        Login login = new Login();
        login.setDataHora(LocalDateTime.now());

        usuario.getLogins().add(login);
        usuarioRepository.save(usuario);
    }

    /**
     * Associa uma arma a um usuário, evitando duplicatas.
     */
    public boolean associarArma(Long usuarioId, Long weaponId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Weapon> weaponOpt = weaponRepository.findById(weaponId);

        if (usuarioOpt.isEmpty() || weaponOpt.isEmpty()) {
            return false; // Usuário ou arma não encontrados
        }

        Usuario usuario = usuarioOpt.get();
        Weapon weapon = weaponOpt.get();

        if (usuario.getArmas() == null) {
            usuario.setArmas(new ArrayList<>());
        }

        // Evita adicionar a mesma arma mais de uma vez
        if (!usuario.getArmas().contains(weapon)) {
            usuario.getArmas().add(weapon);
            usuarioRepository.save(usuario);
        }

        return true;
    }
}
