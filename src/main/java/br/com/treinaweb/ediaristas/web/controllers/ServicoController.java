package br.com.treinaweb.ediaristas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.ediaristas.core.enums.Icone;
import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.core.repositories.ServiceRepository;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {

  @Autowired
  private ServiceRepository repository;

  @GetMapping
  public ModelAndView buscarTodos(){
    var modelAndView = new ModelAndView("admin/servicos/lista");
    modelAndView.addObject("servicos", repository.findAll());

    return modelAndView;
  }

  @GetMapping("/cadastrar")
  public ModelAndView cadastrar(){
        var modelAndView = new ModelAndView("admin/servicos/form");
        modelAndView.addObject("servico", new Servico());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Servico servico){
      repository.save(servico);

      return "redirect:/admin/servicos";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id){
      var modelAndView = new ModelAndView("admin/servicos/form");
      modelAndView.addObject("servico", repository.findById(id));
      return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Servico servico){
      repository.save(servico);

      return "redirect:/admin/servicos";

    }

    @ModelAttribute("icones")
    public Icone[] getIcone(){
      return Icone.values();
    }


    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
      repository.deleteById(id);
      return "redirect:/admin/servicos";
    }
}
