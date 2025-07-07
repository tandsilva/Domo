package com.exozonia.domo.service;

import com.exozonia.domo.dto.SkinDto;
import com.exozonia.domo.mapper.SkinMapper;
import com.exozonia.domo.model.Avatar;
import com.exozonia.domo.model.Skin;
import com.exozonia.domo.repository.AvatarRepository;
import com.exozonia.domo.repository.SkinRepository;
import com.exozonia.domo.util.AvatarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

//    public boolean adicionarSkinAoAvatar(Long idAvatar, SkinDto dto, MultipartFile imagem) {
//        Optional<Avatar> optionalAvatar = avatarRepository.findById(idAvatar);
//        if (optionalAvatar.isEmpty()) return false;
//
//        Avatar avatar = optionalAvatar.get();
//
//        if (avatar.getSkins() == null) {
//            avatar.setSkins(new ArrayList<>());
//        }
//
//        boolean existe = avatar.getSkins().stream()
//                .anyMatch(skin -> skin.getNome().equalsIgnoreCase(dto.getNome()));
//
//        if (existe) return false;
//
//        // Salvar imagem
//        String caminhoImagem = salvarImagem(imagem);
//        if (caminhoImagem == null) return false;
//
//        // Mapear SkinDto para Skin
//        Skin novaSkin = SkinMapper.toEntity(dto, avatar, caminhoImagem);
//
//        avatar.getSkins().add(novaSkin);
//        avatarRepository.save(avatar);
//        return true;
//    }
//
//    private String salvarImagem(MultipartFile imagem) {
//        String nomeImagem = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
//        String caminho = "uploads/skins/" + nomeImagem;
//        File destino = new File(caminho);
//
//        destino.getParentFile().mkdirs(); // Garante que o diretório existe
//
//        try {
//            imagem.transferTo(destino);
//            return caminho;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
@Autowired
private SkinRepository skinRepository;

    public boolean adicionarSkinAoAvatar(Long idAvatar, Long idSkin) {
        Optional<Avatar> optionalAvatar = avatarRepository.findById(idAvatar);
        if (optionalAvatar.isEmpty()) return false;

        Optional<Skin> optionalSkin = skinRepository.findById(idSkin);
        if (optionalSkin.isEmpty()) return false;

        Avatar avatar = optionalAvatar.get();
        Skin skin = optionalSkin.get();

        if (avatar.getSkins() == null) {
            avatar.setSkins(new ArrayList<>());
        }

        boolean existe = avatar.getSkins().stream()
                .anyMatch(s -> s.getId().equals(skin.getId()));

        if (existe) return false;

        avatar.getSkins().add(skin);
        avatarRepository.save(avatar);
        return true;
    }

    public boolean atualizarSkin(Long skinId, String nome, String cor) {
        List<Avatar> avatares = avatarRepository.findAll();

        for (Avatar avatar : avatares) {
            if (avatar.getSkins() != null) {
                for (Skin skin : avatar.getSkins()) {
                    if (skin.getId() != null && skin.getId().equals(skinId)) {
                        skin.setNome(nome);
                        skin.setCor(cor);
                        avatarRepository.save(avatar);
                        return true;
                    }
                }
            }
        }

        return false;
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