package com.project.sharenter.controller;

import com.project.sharenter.dto.ListingDto;
import com.project.sharenter.model.Listing;
import com.project.sharenter.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ListingController {
    @Autowired
    private ListingService listingService;


    @GetMapping("renter/browse-listings")
    public String browseListings(Model model) {
        return findPaginated(1, "title", "asc", model);
    }


    @GetMapping("/renter/listing-details")
    public String listingDetails() {
        return "renter/listing-details";
    }


    @GetMapping("/renter/browse-listings/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortBy") String sortBy,
                                Model model) {
        int pageSize = 3;

        Page<Listing> listingPage = listingService.findPaginated(pageNo, pageSize, sortField, sortBy);
        List<Listing> displayListings = listingPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", listingPage.getTotalPages());
        model.addAttribute("totalItems", listingPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("reverseSortBy", sortBy.equals("asc") ? "desc" : "asc");
        model.addAttribute("displayListings", displayListings);
        return "renter/browse-listings";
    }

    @GetMapping("/sharer/new-listing")
    public String newListing(Model model) {
        //Model attribute to bind form data
        model.addAttribute("listing", new Listing());
        return "sharer/new-listing";
    }

    @PostMapping("/sharer/new-listing")
    public String saveNewListing(Model model, @Valid @ModelAttribute("listing") ListingDto listingDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sharer/new-listing";
        }

        listingService.createListing(listingDto);
        return "redirect:/sharer/new-listing?success";
    }

    @GetMapping("/sharer/edit-listing")
    public String editListing(@PathVariable ( value = "id") long id, Model model) {
        Listing listing = listingService.getListingById(id);
        model.addAttribute("listing", listing);
        return "sharer/edit-listing";
    }

    //Method handler to delete an employee
    @GetMapping("/sharer/delete-listing/{id}")
    public String deleteListing(@PathVariable (value = "id") long id) {

        // call delete employee method
        this.listingService.deleteListingById(id);
        return "redirect:/sharer/dashboard";
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

