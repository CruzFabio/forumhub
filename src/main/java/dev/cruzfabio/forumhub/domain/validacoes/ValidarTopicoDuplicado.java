package dev.cruzfabio.forumhub.domain.validacoes;

import dev.cruzfabio.forumhub.domain.ValidacaoException;
import dev.cruzfabio.forumhub.domain.topico.DadosCadastroTopico;
import dev.cruzfabio.forumhub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidarTopicoDuplicado implements ValidadorCadastroTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DadosCadastroTopico dados) {

        var topicoDuplicado = topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem());

        if (topicoDuplicado) {
            throw new ValidacaoException("Já existe um tópico com esse título e mensagem cadastrados.");
        }
    }
}
