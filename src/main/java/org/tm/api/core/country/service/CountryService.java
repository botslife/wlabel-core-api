package org.tm.api.core.country.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.tm.api.core.country.entity.Country;
import org.tm.api.core.country.mappers.CountryRequest;
import org.tm.api.core.country.mappers.CountryResponse;
import org.tm.api.core.country.mappers.CountryUpdateRequest;
import org.tm.api.core.country.repository.CountryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CountryService {

    private CountryRepository repository;

    public CountryService(CountryRepository repository){
        this.repository = repository;
    }

    public ResponseEntity<Mono<CountryResponse>> create(@RequestBody CountryRequest request){
        Mono<Country> country = repository.findByIso3(request.getIso3());
        if(country.share().block()!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
           return ResponseEntity.status(HttpStatus.CREATED).body(CountryResponse.from(repository.save(CountryRequest.from(request))));
        }
    }

    public Mono<Country> save(Country country){
        return repository.save(country);
    }


    public ResponseEntity<Mono<CountryResponse>> read(@RequestParam Integer id) {
        Mono<Country> country = repository.findById(id);
        if(country.share().block()!=null){
            return ResponseEntity.ok(CountryResponse.from(country));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono> delete(@RequestParam Integer id) {
        Mono<Country> country = repository.findById(id);
        if(country.share().block()!=null){
            return ResponseEntity.status(HttpStatus.OK).body(repository.deleteById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<CountryResponse>> update(@RequestBody CountryUpdateRequest request){
        Mono<Country> country = repository.findById(request.getId());
        if(country.share().block()!=null){
            return ResponseEntity.status(HttpStatus.OK).body(CountryResponse.from(repository.save(CountryUpdateRequest.from(request))));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public Flux<CountryResponse> all() {
        return CountryResponse.from(repository.findAll()).switchIfEmpty(Flux.<CountryResponse>empty());
    }

    public ResponseEntity<Mono<CountryResponse>> findByIso3(String iso3) {
        Mono<Country> country = repository.findByIso3(iso3.toUpperCase());
        if(country.share().block()!=null){
            return ResponseEntity.ok(CountryResponse.from(country));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<CountryResponse>> findByIso2(String iso2) {
        Mono<Country> country = repository.findByIso2(iso2.toUpperCase());
        if(country.share().block()!=null){
            return ResponseEntity.ok(CountryResponse.from(country));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
