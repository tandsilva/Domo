package com.exozonia.domo.service;

import com.exozonia.domo.model.Avatar;
import com.exozonia.domo.repository.AvatarRepository;
import com.exozonia.domo.util.AvatarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//indica que esta classe eh um serviço usado pelo spring
@Service
public class AvatarService {

    @Autowired
    private AvatarRepository avatarRepository;

    public boolean podeOuvirOutroAvatar(Long idAvatar1 ,Long idAvatar2) {
        Optional<Avatar> avatar1 = avatarRepository.findById(idAvatar1);
        Optional<Avatar> avatar2 = avatarRepository.findById(idAvatar2);
        if (avatar1.isPresent() && avatar2.isPresent()) {
            return AvatarUtils.podeOuvir(avatar1.get(),avatar2.get());

        }
            return false;

    }
        //atualizando posiçao do avatar
    public void atualizarPosicao(Long avatarID ,double x ,double y ){
        avatarRepository.findById(avatarID).ifPresent(avatar -> {
            avatar.setPosX(x);
            avatar.setPosY(y);
            avatarRepository.save(avatar);
        });

    }
    }






