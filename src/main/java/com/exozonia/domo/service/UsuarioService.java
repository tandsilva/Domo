package com.exozonia.domo.service;

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

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    private WeaponRepository weaponRepository;
    private UsuarioDto dto;
public Usuario salvar(Usuario usuario) {
    if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
        throw new IllegalArgumentException("Nome não pode ser vazio");
    }
    // Verifica se já existe usuário com mesmo nome ou email
    if (repository.existsByNome(usuario.getNome())) {
        throw new IllegalArgumentException("Nome já cadastrado");
    }
    if (repository.existsByEmail(usuario.getEmail())) {
        throw new IllegalArgumentException("Email já cadastrado");
    }
    return repository.save(usuario);
}

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
    public Usuario atualizar(Long id, UsuarioUpdateDto dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        UsuarioMapper.merge(usuario, dto);

        return repository.save(usuario);
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarLogin(Usuario usuario) {
        if (usuario.getLogins() == null) {
            usuario.setLogins(new ArrayList<>());
        }

        Login login = new Login();
        login.setDataHora(LocalDateTime.now());

        usuario.getLogins().add(login);
        usuarioRepository.save(usuario);
    }
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

        // Evitar adicionar arma duplicada
        if (!usuario.getArmas().contains(weapon)) {
            usuario.getArmas().add(weapon);
            usuarioRepository.save(usuario);
        }

        return true;
    }
//    public UsuarioDto associarArma(Long usuarioId, Long weaponId) {
//        Usuario usuario = usuarioRepository.findById(usuarioId)
//                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
//
//        Weapon weapon = weaponRepository.findById(weaponId)
//                .orElseThrow(() -> new RuntimeException("Arma não encontrada"));
//
//        if (usuario.getArmas() == null) {
//            usuario.setArmas(new ArrayList<>());
//        }
//
//        usuario.getArmas().add(weapon);
//
//        Usuario usuarioSalvo = usuarioRepository.save(usuario);
//
//        // Aqui chamamos o método estático do mapper direto pela classe
//        return UsuarioMapper.toDto(usuarioSalvo);
//    }
}


