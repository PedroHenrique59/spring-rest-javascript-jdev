package br.com.springboot.controllers;

import br.com.springboot.model.Usuario;
import br.com.springboot.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }

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
    public ResponseEntity<String> excluirUsuario(@RequestParam Long id) {
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>("Usuário excluído", HttpStatus.OK);
    }



}
