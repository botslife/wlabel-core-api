package org.tm.api.core.site.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.tm.api.core.country.entity.Country;
import org.tm.api.core.country.repository.CountryRepository;
import org.tm.api.core.site.entity.Site;
import org.tm.api.core.site.mappers.SiteCreateRequest;
import org.tm.api.core.site.mappers.SiteResponse;
import org.tm.api.core.site.mappers.SiteUpdateRequest;
import org.tm.api.core.site.repository.SiteRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class SiteService {

    private SiteRepository repository;
    private CountryRepository countryRepository;

    public SiteService(SiteRepository repository,CountryRepository countryRepository){
        this.repository = repository;
        this.countryRepository = countryRepository;
    }

    public ResponseEntity<Mono<SiteResponse>> create(SiteCreateRequest request){
        Mono<Country> country = countryRepository.findByIso3(request.getCountryIso3().toUpperCase());
        if(country.share().block()!=null){
            Mono<Site> siteCheck = repository.findByDomain(request.getDomain());
            if(siteCheck.share().block()==null){
                Site site = SiteCreateRequest.from(request,country.share().block());
                return ResponseEntity.status(HttpStatus.CREATED).body(SiteResponse.from(repository.save(site),country.share().block()));
            }else{
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Mono<SiteResponse>> findById(@RequestParam Integer id) {
        Mono<Site> site = repository.findById(id);
        if(site.share().block()!=null){
            Mono<Country> country = countryRepository.findById(site.share().block().getCountryId());
            return ResponseEntity.ok(SiteResponse.from(site,country.share().block()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<SiteResponse>> findByDomain(@RequestParam String domain) {
        Mono<Site> site = repository.findByDomain(domain);
        if(site.share().block()!=null){
            Mono<Country> country = countryRepository.findById(site.share().block().getCountryId());
            return ResponseEntity.ok(SiteResponse.from(site,country.share().block()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono> delete(@RequestParam Integer id) {
        Mono<Site> venue = repository.findById(id);
        if(venue.share().block()!=null){
            return ResponseEntity.status(HttpStatus.OK).body(repository.deleteById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<SiteResponse>> update(@RequestBody SiteUpdateRequest request){
        if(request.getId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            Mono<Site> siteCheck = repository.findById(request.getId());
            if(siteCheck.share().block()!=null){
                Mono<Country> country = countryRepository.findByIso3(request.getCountryIso3().toUpperCase());
                if(country.share().block()!=null){
                    Site site = SiteUpdateRequest.from(request,country.share().block());
                    return ResponseEntity.status(HttpStatus.OK).body(SiteResponse.from(repository.save(site),country.share().block()));
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    public Flux<Site> all() {
        return repository.findAll().switchIfEmpty(Flux.<Site>empty());
    }

    public Flux<Site> findByCountryId(Integer id){
        return repository.findByCountryId(id);
    }
}
