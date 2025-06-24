package com.exozonia.domo.service;
import com.exozonia.domo.model.Skin;
import java.util.ArrayList;
import com.exozonia.domo.model.Avatar;
import com.exozonia.domo.model.Usuario;
import com.exozonia.domo.repository.AvatarRepository;
import com.exozonia.domo.util.AvatarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.List;
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
    public Avatar salvar(Avatar avatar) {
        // Aqui você pode validar campos obrigatórios, tipo nome não nulo, email válido, etc.
        if (avatar.getSkins() == null || avatar.getPele().isEmpty()) {
            throw new IllegalArgumentException("voce ainda nao comprou uma skin");
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

    //atualizando posiçao do avatar
    public void atualizarPosicao(Long avatarID ,double x ,double y ){
        avatarRepository.findById(avatarID).ifPresent(avatar -> {
            avatar.setPosX(x);
            avatar.setPosY(y);
            avatarRepository.save(avatar);
        });

    }


//    public boolean adicionarSkinAoAvatar(Long idAvatar, Skin novaSkin) {
//        Optional<Avatar> optionalAvatar = avatarRepository.findById(idAvatar);
//
//        if (optionalAvatar.isPresent()) {
//            Avatar avatar = optionalAvatar.get();
//
//            if (avatar.getSkins() == null) {
//                avatar.setSkins(new ArrayList<>());
//            }
//
//            // Verifica se já existe uma skin com o mesmo nome
//            boolean existe = avatar.getSkins().stream()
//                    .anyMatch(skin -> skin.getNome().equalsIgnoreCase(novaSkin.getNome()));
//
//            if (!existe) {
//                // Associa o avatar à skin (relacionamento Neo4j)
//                novaSkin.setAvatar(avatar);
//
//                avatar.getSkins().add(novaSkin);
//                avatarRepository.save(avatar);
//                return true;
//            }
//        }
//        return false;
//    }


    public boolean adicionarSkinAoAvatar(Long idAvatar, String nome, String cor, MultipartFile imagem) {
        Optional<Avatar> optionalAvatar = avatarRepository.findById(idAvatar);

        if (optionalAvatar.isPresent()) {
            Avatar avatar = optionalAvatar.get();

            if (avatar.getSkins() == null) {
                avatar.setSkins(new ArrayList<>());
            }

            // Verifica se já existe uma skin com o mesmo nome
            boolean existe = avatar.getSkins().stream()
                    .anyMatch(skin -> skin.getNome().equalsIgnoreCase(nome));

            if (existe) {
                return false;
            }

            // Salva a imagem em disco
            String nomeImagem = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
            String caminho = "uploads/skins/" + nomeImagem;
            File destino = new File(caminho);

            try {
                imagem.transferTo(destino);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            // Cria a nova skin
            Skin novaSkin = new Skin();
            novaSkin.setNome(nome);
            novaSkin.setCor(cor);
            novaSkin.setAvatar(avatar);
            novaSkin.setImagemPath(caminho); // Adicione este campo na sua classe Skin

            avatar.getSkins().add(novaSkin);
            avatarRepository.save(avatar);

            return true;
        }

        return false;
    }

}






