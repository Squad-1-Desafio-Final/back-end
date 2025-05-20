package br.com.acabouMony.service;

import br.com.acabouMony.dto.CadastroUsuarioDTO;
import br.com.acabouMony.dto.ListagemUsuarioDTO;
import br.com.acabouMony.entity.Usuario;
import br.com.acabouMony.mapper.UsuarioMapper;
import br.com.acabouMony.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository motoristaRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Transactional
    public ListagemUsuarioDTO saveUsuario(CadastroUsuarioDTO usuarioDTO) {
        if (motoristaRepository.existsByEmail(usuarioDTO.email())) {
            throw new RuntimeException("Este e-mail já está cadastrado.");
        }
        var usuario = new Usuario(usuarioDTO);
        motoristaRepository.save(usuario);
        return usuarioMapper.toListagemUsuarioDTO(usuario);
    }

}
