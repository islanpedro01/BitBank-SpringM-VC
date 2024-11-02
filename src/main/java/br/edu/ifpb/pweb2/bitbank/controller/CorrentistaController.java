package br.edu.ifpb.pweb2.bitbank.controller;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repositorio.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @RequestMapping("/form")
    public String getForm(Correntista correntista, Model model) {
        model.addAttribute("correntista", correntista);

        return "correntistas/form";

    }

    @RequestMapping("/save")
    public String save(Correntista correntista, Model model) {
        String mensagem;
    if (correntista.getNome().isEmpty() || correntista.getNome().length() > 50) {
        mensagem = "O nome é obrigatório e deve ter até 50 caracteres!";
        model.addAttribute("mensagem", mensagem);
        return "correntistas/form";
    }
    if (correntista.getEmail().isEmpty() || correntista.getSenha().isEmpty()) {
        mensagem = "Os campos E-MAIL e SENHA são obrigatórios!";
        model.addAttribute("mensagem", mensagem);
        return "correntistas/form";
        }
        correntistaRepository.save(correntista);
        model.addAttribute("correntistas", correntistaRepository.findAll());
        return "correntistas/list";
    }


   }
