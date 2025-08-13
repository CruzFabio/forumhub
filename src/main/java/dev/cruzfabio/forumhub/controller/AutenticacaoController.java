package dev.cruzfabio.forumhub.controller;

import dev.cruzfabio.forumhub.domain.usuario.DadosAutenticacao;
import dev.cruzfabio.forumhub.domain.usuario.Usuario;
import dev.cruzfabio.forumhub.infra.exception.security.DadosTokenJWT;
import dev.cruzfabio.forumhub.infra.exception.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autenticacao = manager.authenticate(authToken);
        var tokenJWT = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }


}
