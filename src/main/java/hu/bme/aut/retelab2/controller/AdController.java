package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.SecretGenerator;
import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {


    @Autowired
    private AdRepository adRepository;

    @PostMapping
    public Ad create(@RequestBody Ad ad) {
        ad.setId(null);
        ad.setCode(SecretGenerator.generate());
        //System.out.println(ad.getCode());
        return adRepository.save(ad);
    }

    @GetMapping
    public List<Ad> getAll() {

        return  adRepository.findAll();
    }

    @GetMapping("/filter")
    public List<Ad> getAdsByPriceRange(
            @RequestParam(name = "minPrice", required = false, defaultValue = "0") int minPrice,
            @RequestParam(name = "maxPrice", required = false, defaultValue = "10000000") int maxPrice) {
        return adRepository.findByPriceRange(minPrice, maxPrice);
    }


    @GetMapping("/admin")
    public List<Ad> getAllWithCode() {

        return  adRepository.privateFind();
    }

    @GetMapping("{id}")
    public ResponseEntity<Ad> getById(@PathVariable long id) {
        Ad a = adRepository.findById(id);
        if (a == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(a);
    }


    @PutMapping
    public ResponseEntity<Ad> update(@RequestBody Ad a) {

        Ad result =adRepository.updateById(a);

        if(result!=null){
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        }


    }




}
