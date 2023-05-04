package br.com.springboot.controllers;

import br.com.springboot.model.Usuario;
import br.com.springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A sample greetings controller to return greeting text
 */
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "listaTodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        Usuario user = usuarioRepository.save(usuario);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario) {
        Usuario user = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> excluirUsuario(@RequestParam Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
        return new ResponseEntity<>("Usuário excluído", HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome) {
        List<Usuario> usuarios = usuarioRepository.findByNomeContainingIgnoreCase(nome);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorId")
    @ResponseBody
    public ResponseEntity<Usuario> buscarPorId(@RequestParam Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(new Usuario());
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
