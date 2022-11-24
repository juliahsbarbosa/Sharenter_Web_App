package com.project.sharenter.controller;

import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.Listing;
import com.project.sharenter.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LisController {

    @Autowired
    private ListingService listingService;

    @GetMapping("/listings")
    public String newListing(Model model) {
        model.addAttribute("listing", new Listing());
        return "form";
    }


    @PostMapping("/listings")
    public String createNewListing(Model model, @Valid @ModelAttribute("listing") ListingDto listingDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listing", listingDto );
            return "form";
        }
        model.addAttribute("successListing", listingDto);
        listingService.createListing(listingDto);
        return "redirect:listings?success";
    }
}
//
//    @Autowired
//    private Listing listing;
//
//    @Autowired
//    private ListingService listing;
//
//    @GetMapping("/browse-listings")
//    public ResponseEntity buscarTodos(@RequestParam(value = "id", required = false) Integer id,
//                                      @RequestParam(value = "page") Integer page,
//                                      @RequestParam(value = "size") Integer size) {
//        return anuncioService.buscarTodos(id, page, size);
//    }
//
//    /**
//     * Busca as possiveis autocomplete
//     *
//     * @param text - texto que sera base da pesquisa
//     * @return uma lista de Auto Completes
//     **/
//    @ApiOperation("Busca todos os possíveis autocomplete para o campo desejado")
//    @Cacheable
//    @GetMapping("/v1/autocomplete")
//    public ResponseEntity buscaTodosAutoComplete(@RequestParam(value = "pesquisa") String text) {
//        return anuncioService.buscaTodosAutoComplete(text);
//    }
//
//    /**
//     * Buscar todos os anúncios by rua, bairro e cidade
//     *
//     * @param page     - pagina selecionada
//     * @param size     - tamanho da pagina selecionada
//     * @param pesquisa - texto para ser base da pesquisa
//     * @return List of anuncios
//     */
//    @ApiOperation("Busca todos os anúncios com os parâmetros passados")
//    @Cacheable
//    @GetMapping("/v1/search")
//    public ResponseEntity buscarTodosPorParametros(
//            @RequestParam(value = "pesquisa", defaultValue = "") String pesquisa,
//            @RequestParam(value = "page") Integer page,
//            @RequestParam(value = "size") Integer size) {
//        return anuncioService.buscarTodosPorParametros(pesquisa, page, size);
//    }
//
//
//    /**
//     * Buscar por id response entity.
//     *
//     * @param id the id
//     * @return the response entity
//     */
//    @ApiOperation("Busca apenas um anúncio pelo o ID")
//    @Cacheable
//    @GetMapping("/v1/{id}")
//    public ResponseEntity buscarPorId(@PathVariable("id") Integer id) {
//        return anuncioService.buscarPorId(id);
//    }
//
//    /**
//     * Salvar response entity.
//     *
//     * @param anuncio the anuncio
//     * @param id      the id
//     * @param result  the result
//     * @return the response entity
//     */
//    @ApiOperation("Salva o anúncio")
//    @CacheEvict(value = Constantes.CACHE_ANUNCIOS, allEntries = true)
//    @PostMapping("/v1/{id}")
//    public ResponseEntity salvar(@Valid @RequestBody Anuncio anuncio, @PathVariable("id") Integer id, BindingResult result) {
//        return anuncioService.salvar(anuncio, id, result);
//    }
//
//    /**
//     * Alterar response entity.
//     *
//     * @param anuncio the anuncio
//     * @param result  the result
//     * @return the response entity
//     */
//    @ApiOperation("Altera o anúncio")
//    @CacheEvict(value = Constantes.CACHE_ANUNCIOS, allEntries = true)
//    @PutMapping("/v1")
//    public ResponseEntity alterar(@Valid @RequestBody Anuncio anuncio, BindingResult result) {
//        return anuncioService.alterar(anuncio, result);
//    }
//
//    /**
//     * Excluir por id response entity.
//     *
//     * @param id the id
//     * @return the response entity
//     */
//    @ApiOperation("Exclui o anúncio pelo ID")
//    @CacheEvict(value = Constantes.CACHE_ANUNCIOS, allEntries = true)
//    @DeleteMapping("/v1/{id}")
//    public ResponseEntity excluirPorId(@PathVariable("id") Integer id) {
//        return anuncioService.excluirPorId(id);
//    }
//
//    /**
//     * Cria o relátorio da listagem de imoveis a venda
//     *
//     * @param idUsuario   - id do usuário logado
//     * @param tipoNegocio - é o tipo de negócio que será baseado o relatorio
//     * @return relatorio
//     **/
//    @ApiOperation("Gera o relatório de listagem de anúncios a venda ou para alugar do usuário")
//    @GetMapping("/relatorio/venda/{idUsuario}")
//    public ResponseEntity listagemVendaAluguel(@PathVariable("idUsuario") Integer idUsuario, @RequestParam TipoNegocio tipoNegocio, @RequestParam TipoRelatorio tipoRelatorio, @RequestParam TipoTemplate tipoTemplate) {
//        return anuncioService.relatorioVendaAluguel(idUsuario, tipoNegocio, tipoRelatorio, tipoTemplate);
//    }
//
//}
//}
////
////    private final ListingService listingService;
////    @Value("${maps.api.key}")
////    private String mapsApiKey;
////
////    @GetMapping
////    public ModelAndView viewAllListings() {
////        final List<Listing> listings = listingService.getAll();
////        final ModelAndView modelAndView = new ModelAndView("listings");
////        modelAndView.addObject("listings", listings);
////        modelAndView.addObject("gmapsApiKey", mapsApiKey);
////        modelAndView.addObject("listing", new Listing());
////        return modelAndView;
////    }
////
////    @PostMapping
////    public ModelAndView createListing(@ModelAttribute Listing listing) {
////        listingService.create(listing);
////        return new ModelAndView("redirect:/");
////    }
//////
//////    @GetMapping("/api/listings")
//////    @ResponseBody
//////    public ResponseEntity<List<Listing>> getAllUsers() {
//////        final List<Listing> listings = listingService.getAll().stream().filter(e -> e.getGeocoding() != null).collect(Collectors.toList());
//////        return ResponseEntity.ok(listings);
//////    }
////
////    private final String UPLOAD_DIR = "./uploads/";
////
////
////    @PostMapping("/upload")
////    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
////
////        // check if file is empty
////        if (file.isEmpty()) {
////            attributes.addFlashAttribute("message", "Please select a file to upload.");
////            return "redirect:/";
////        }
////
////        // normalize the file path
////        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
////
////        // save the file on the local file system
////        try {
////            Path path = Paths.get(UPLOAD_DIR + fileName);
////            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        // return success response
////        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
////
////        return "redirect:/";
////    }
////}

