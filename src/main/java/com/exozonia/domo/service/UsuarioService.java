package com.exozonia.domo.service;

import com.exozonia.domo.dto.UsuarioUpdateDto;
import com.exozonia.domo.mapper.UsuarioMapper;
import com.exozonia.domo.model.Login;
import com.exozonia.domo.model.Skin;
import com.exozonia.domo.model.Usuario;
import com.exozonia.domo.model.Weapon;
import com.exozonia.domo.repository.SkinRepository;
import com.exozonia.domo.repository.UsuarioRepository;
import com.exozonia.domo.repository.WeaponRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pelas regras de negócio relacionadas a usuários.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final SkinRepository skinRepository;
    private final WeaponRepository weaponRepository;

    @Autowired
    public UsuarioService(
            UsuarioRepository usuarioRepository,
            SkinRepository skinRepository,
            WeaponRepository weaponRepository) {
        this.usuarioRepository = usuarioRepository;
        this.skinRepository = skinRepository;
        this.weaponRepository = weaponRepository;
    }

    /**
     * Salva um novo usuário com validações básicas.
     */
    public Usuario salvar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (usuarioRepository.existsByNome(usuario.getNome())) {
            throw new IllegalArgumentException("Nome já cadastrado");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    /**
     * Lista todos os usuários.
     */
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /**
     * Busca um usuário pelo ID, retorna null se não existir.
     */
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    /**
     * Deleta um usuário pelo ID.
     */
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Atualiza um usuário com os dados do DTO de atualização.
     */
    public Usuario atualizar(Long id, UsuarioUpdateDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        UsuarioMapper.merge(usuario, dto);
        return usuarioRepository.save(usuario);
    }

    /**
     * Registra um login para o usuário adicionando um novo objeto Login.
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
     * Associa uma arma a um usuário, evitando duplicação.
     */
    public boolean associarArma(Long usuarioId, Long weaponId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Weapon> weaponOpt = weaponRepository.findById(weaponId);

        if (usuarioOpt.isEmpty() || weaponOpt.isEmpty()) {
            return false;
        }

        Usuario usuario = usuarioOpt.get();
        Weapon weapon = weaponOpt.get();

        if (usuario.getArmas() == null) {
            usuario.setArmas(new ArrayList<>());
        }

        if (!usuario.getArmas().contains(weapon)) {
            usuario.getArmas().add(weapon);
            usuarioRepository.save(usuario);
        }

        return true;
    }

    /**
     * Associa uma skin a um usuário, evitando duplicação.
     */

    public boolean associarSkin(Long usuarioId, Long skinId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Skin> skinOpt = skinRepository.findById(skinId);

        if (usuarioOpt.isEmpty() || skinOpt.isEmpty()) {
            return false; // usuário ou skin não encontrados
        }

        Usuario usuario = usuarioOpt.get();
        Skin skin = skinOpt.get();

        if (usuario.getSkins() == null) {
            usuario.setSkins(new ArrayList<>());
        }

        if (!usuario.getSkins().contains(skin)) {
            usuario.getSkins().add(skin);
            usuarioRepository.save(usuario);
        }

        return true;
    }

}
