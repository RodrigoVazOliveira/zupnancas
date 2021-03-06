package br.com.zup.zupnancas.controllers;

import br.com.zup.zupnancas.dto.conta.AtualizarContaDTO;
import br.com.zup.zupnancas.dto.conta.CadastrarContaDTO;
import br.com.zup.zupnancas.dto.conta.SaidaCadastrarContaDTO;
import br.com.zup.zupnancas.enumerates.Status;
import br.com.zup.zupnancas.models.Conta;
import br.com.zup.zupnancas.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Constraint;
import javax.validation.Valid;

@RestController
@RequestMapping("contas/")
public class ContaController {

    private ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaCadastrarContaDTO gravarNovaConta(@RequestBody @Valid CadastrarContaDTO cadastrarContaDTO) {
        Conta conta = contaService.gravarNovaConta(cadastrarContaDTO.converterCadastrarContaDtoParaConta());
        return SaidaCadastrarContaDTO.converterContaParaSaidaCadastrarContaDTO(conta);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaCadastrarContaDTO atualizarConta(@RequestBody @Valid AtualizarContaDTO atualizarContaDTO) {
        Conta conta = contaService.atualizarConta(atualizarContaDTO.converterAtualizarContaDtoParaConta());
        return SaidaCadastrarContaDTO.converterContaParaSaidaCadastrarContaDTO(conta);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SaidaCadastrarContaDTO> pesquisarContaPorStatus(
            @RequestParam(name = "status")
                    Status status) {    
            return SaidaCadastrarContaDTO.converterListaContaParaListContaDto(
                    contaService.pesquisarContaPorStatus(status)
            );
    }
}