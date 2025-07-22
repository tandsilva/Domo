package com.exozonia.domo.service;

import com.exozonia.domo.enums.SkinTipo;
import com.exozonia.domo.model.Avatar;
import com.exozonia.domo.model.Skin;
import com.exozonia.domo.repository.AvatarRepository;
import com.exozonia.domo.repository.SkinRepository;
import com.exozonia.domo.util.AvatarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AvatarService {

    @Autowired
    private AvatarRepository avatarRepository;

    public boolean podeOuvirOutroAvatar(Long idAvatar1, Long idAvatar2) {
        Optional<Avatar> avatar1 = avatarRepository.findById(idAvatar1);
        Optional<Avatar> avatar2 = avatarRepository.findById(idAvatar2);
        return avatar1.isPresent() && avatar2.isPresent() && AvatarUtils.podeOuvir(avatar1.get(), avatar2.get());
    }

    public Avatar salvar(Avatar avatar) {
        // Não exigimos mais skins na criação do Avatar
        if (avatar.getPele() == null || avatar.getPele().isEmpty()) {
            throw new IllegalArgumentException("A pele não pode estar vazia.");
        }
        return avatarRepository.save(avatar);
    }

    public List<Avatar> listarTodos() {
        return avatarRepository.findAll();
    }

    public Avatar buscarPorId(Long id) {
        return avatarRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        avatarRepository.deleteById(id);
    }

    public void atualizarPosicao(Long avatarID, double x, double y) {
        avatarRepository.findById(avatarID).ifPresent(avatar -> {
            avatar.setPosX(x);
            avatar.setPosY(y);
            avatarRepository.save(avatar);
        });
    }


@Autowired
private SkinRepository skinRepository;
    public boolean adicionarSkinAoAvatar(Long idAvatar, Long idSkin) {
        Optional<Avatar> optionalAvatar = avatarRepository.findById(idAvatar);
        Optional<Skin> optionalSkin = skinRepository.findById(idSkin);

        if (optionalAvatar.isEmpty() || optionalSkin.isEmpty()) {
            return false; // Avatar ou Skin não encontrados
        }

        Avatar avatar = optionalAvatar.get();
        Skin skin = optionalSkin.get();

        if (avatar.getSkins() == null) {
            avatar.setSkins(new ArrayList<>());
        }

        // Verifica se a skin já está associada pelo ID
        boolean existe = avatar.getSkins().stream()
                .anyMatch(s -> s.getId().equals(skin.getId()));

        if (existe) {
            return false; // Skin já associada
        }

        // Associa a skin e salva o avatar
        avatar.getSkins().add(skin);
        avatarRepository.save(avatar);

        return true;
    }


    public boolean atualizarSkin(Long skinId, String nome, String tipo) {
        List<Avatar> avatares = avatarRepository.findAll();

        SkinTipo tipoEnum;
        try {
            tipoEnum = SkinTipo.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false; // tipo inválido, aborta atualização
        }

        for (Avatar avatar : avatares) {
            if (avatar.getSkins() != null) {
                for (Skin skin : avatar.getSkins()) {
                    if (skin.getId() != null && skin.getId().equals(skinId)) {
                        skin.setNome(nome);
                        skin.setTipo(tipoEnum);  // agora usa o enum
                        avatarRepository.save(avatar);
                        return true;
                    }
                }
            }
        }

        return false; // skinId não encontrada
    }


    public boolean deletarSkinPorId(Long skinId) {
        List<Avatar> avatares = avatarRepository.findAll();

        for (Avatar avatar : avatares) {
            if (avatar.getSkins() != null) {
                Optional<Skin> skinOpt = avatar.getSkins().stream()
                        .filter(skin -> skin.getId() != null && skin.getId().equals(skinId))
                        .findFirst();

                if (skinOpt.isPresent()) {
                    avatar.getSkins().remove(skinOpt.get());
                    avatarRepository.save(avatar);
                    return true;
                }
            }
        }

        return false;
    }

}