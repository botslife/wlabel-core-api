package org.tm.api.core.venue.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.tm.api.core.country.entity.Country;
import org.tm.api.core.country.repository.CountryRepository;
import org.tm.api.core.venue.entity.Venue;
import org.tm.api.core.venue.mappers.VenueRequest;
import org.tm.api.core.venue.mappers.VenueResponse;
import org.tm.api.core.venue.mappers.VenueUpdateRequest;
import org.tm.api.core.venue.repository.VenueRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VenueService {

    private VenueRepository repository;

    private CountryRepository countryRepository;

    public VenueService(VenueRepository repository,CountryRepository countryRepository){
        this.repository = repository;
        this.countryRepository = countryRepository;
    }

    public ResponseEntity<Mono<VenueResponse>> save(@RequestBody VenueRequest request){
        Mono<Country> country = countryRepository.findByIso3(request.getCountryIso3().toUpperCase());
        if(country.share().block()!=null){
            Mono<Venue> venueCheck = repository.findByName(request.getName());
            if(venueCheck.share().block()==null){
                Venue venue = VenueRequest.from(request,country.share().block().getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(VenueResponse.from(repository.save(venue),country.share().block()));
            }else{
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Mono<VenueResponse>> findById(@RequestParam Integer id) {
        Mono<Venue> venue = repository.findById(id);
        if(venue.share().block()!=null){
            Mono<Country> country = countryRepository.findById(venue.share().block().getCountryId());
            return ResponseEntity.ok(VenueResponse.from(venue,country.share().block()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<VenueResponse>> findByName(@RequestParam String name) {
        Mono<Venue> venue = repository.findByName(name);
        if(venue.share().block()!=null){
            Mono<Country> country = countryRepository.findById(venue.share().block().getCountryId());
            return ResponseEntity.ok(VenueResponse.from(venue,country.share().block()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono> delete(@RequestParam Integer id) {
        Mono<Venue> venue = repository.findById(id);
        if(venue.share().block()!=null){
            return ResponseEntity.status(HttpStatus.OK).body(repository.deleteById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<VenueResponse>> update(@RequestBody VenueUpdateRequest request){
        if(request.getId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            Mono<Venue> isVenue = repository.findById(request.getId());
            if(isVenue.share().block()!=null){
                Mono<Country> country = countryRepository.findByIso3(request.getCountryIso3());
                if(country.share().block()!=null){
                    Venue venue = VenueUpdateRequest.from(request,country.share().block().getId());
                    return ResponseEntity.status(HttpStatus.OK).body(VenueResponse.from(repository.save(venue),country.share().block()));
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    public Flux<Venue> all() {
        return repository.findAll().switchIfEmpty(Flux.<Venue>empty());
    }

    public Flux<Venue> findByCountryId(Integer id){
        return repository.findByCountryId(id);
    }

}
