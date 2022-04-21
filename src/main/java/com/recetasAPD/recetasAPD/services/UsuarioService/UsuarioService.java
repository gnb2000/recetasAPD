package com.recetasAPD.recetasAPD.services.UsuarioService;

import com.recetasAPD.recetasAPD.entities.Usuario;
import org.springframework.web.multipart.MultipartFile;

public interface UsuarioService {
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(Usuario usuario);
    Usuario findByNicknameAndPassword(String nickname, String password) throws Exception;
    Usuario login(String nicknameOrMail, String password);
    Usuario findById(Integer idUsuario);
    void registerNewUser(String nickname, String mail);
    void updatePassword(Integer idUsuario, String password);
    void accountRecovery(Integer idUsuario);
    boolean checkRecoveryCode(Integer idUsuario, String code);
    String updateAvatar(Integer idUsuario, MultipartFile foto);

}
