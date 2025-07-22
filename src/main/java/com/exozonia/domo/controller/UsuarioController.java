    package com.exozonia.domo.controller;

    import com.exozonia.domo.dto.UsuarioDto;
    import com.exozonia.domo.dto.UsuarioUpdateDto;
    import com.exozonia.domo.mapper.UsuarioMapper;
    import com.exozonia.domo.model.Usuario;
    import com.exozonia.domo.repository.UsuarioRepository;
    import com.exozonia.domo.service.UsuarioService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.stream.Collectors;
    @CrossOrigin("*")
    @RestController
    @RequestMapping("/api/usuarios")
    public class UsuarioController {

        @Autowired
        private UsuarioService service;
        private UsuarioRepository repository;



        @PostMapping
        public ResponseEntity<UsuarioDto> criar(@RequestBody UsuarioDto usuario) {
            Usuario entity = UsuarioMapper.toEntity(usuario);
            Usuario salvo = service.salvar(entity);
            return ResponseEntity.status(201).body(UsuarioMapper.toDto(salvo));
        }

        @GetMapping
        public ResponseEntity<List<UsuarioDto>> listar() {
            List<UsuarioDto> usuarios = service.listarTodos()
                    .stream()
                    .map(UsuarioMapper::toDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(usuarios);
        }

        @GetMapping("/id/{id}")
        public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
            Usuario usuario = service.buscarPorId(id);
            if (usuario != null) {
                return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/id/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        @PutMapping("/id/{id}")
        public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody UsuarioUpdateDto dto) {
            try {
                Usuario atualizado = service.atualizar(id, dto);
                return ResponseEntity.ok(UsuarioMapper.toDto(atualizado));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }
        @PostMapping("/{usuarioId}/arma/{armaId}")
        public ResponseEntity<String> associarArmaAoUsuario(@PathVariable Long usuarioId, @PathVariable Long armaId) {
            boolean sucesso = service.associarArma(usuarioId, armaId);
            if (sucesso) {
                return ResponseEntity.ok("Arma associada ao usuário com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou arma não encontrados.");
            }
        }




    }
