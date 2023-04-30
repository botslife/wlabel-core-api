package org.tm.api.core.centre.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.tm.api.core.centre.entity.Centre;
import org.tm.api.core.centre.entity.CentreVenue;
import org.tm.api.core.centre.mappers.CentreVenueCreateRequest;
import org.tm.api.core.centre.mappers.CentreVenueResponse;
import org.tm.api.core.centre.mappers.CentreVenueUpdateRequest;
import org.tm.api.core.centre.repository.CentreRepository;
import org.tm.api.core.centre.repository.CentreVenueRepository;
import org.tm.api.core.venue.entity.Venue;
import org.tm.api.core.venue.repository.VenueRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CentreVenueService {

    private CentreRepository centreRepository;
    private VenueRepository venueRepository;
    private CentreVenueRepository repository;

    public CentreVenueService(CentreRepository centreRepository, VenueRepository venueRepository, CentreVenueRepository repository){
        this.repository = repository;
        this.venueRepository = venueRepository;
        this.centreRepository = centreRepository;
    }

    public ResponseEntity<Mono<CentreVenueResponse>> create(@RequestBody CentreVenueCreateRequest request){
        if(request.getCentreId()==null && request.getVenueId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            Mono<CentreVenue> centreVenueCheck = repository.findByCentreAndVenue(request.getCentreId(), request.getVenueId());
            if(centreVenueCheck.share().block()!=null){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }else{
                Mono<Centre> centre = centreRepository.findById(request.getCentreId());
                Mono<Venue> venue = venueRepository.findById(request.getVenueId());
                if(centre.share().block() !=null && venue.share().block()!=null){
                    CentreVenue centreVenue = CentreVenueCreateRequest.from(request);
                    Mono<CentreVenue> centreVenueMono = repository.save(centreVenue);
                    return ResponseEntity.status(HttpStatus.OK).body(CentreVenueResponse.from(centre,venue,centreVenueMono));
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }
        }
    }

    public ResponseEntity<Mono<CentreVenueResponse>> findById(@RequestParam Integer id) {
        Mono<CentreVenue> centreVenue = repository.findById(id);
        if(centreVenue.share().block()!=null){
            Mono<Centre> centre = centreRepository.findById(centreVenue.share().block().getCentreId());
            Mono<Venue> venue = venueRepository.findById(centreVenue.share().block().getVenueId());
            if(centre.share().block() !=null && venue.share().block()!=null){
                return ResponseEntity.status(HttpStatus.OK).body(CentreVenueResponse.from(centre,venue,centreVenue));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<CentreVenueResponse>> update(@RequestBody CentreVenueUpdateRequest request) {
        if (request.getId() == null && request.getCentreId() == null && request.getVenueId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            Mono<CentreVenue> centreVenue = repository.findById(request.getId());
            if (centreVenue.share().block() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                Mono<CentreVenue> centreVenueCheck = repository.findByCentreAndVenue(request.getCentreId(), request.getVenueId());
                if (centreVenueCheck.share().block() == null) {
                    Mono<Centre> centre = centreRepository.findById(request.getCentreId());
                    Mono<Venue> venue = venueRepository.findById(request.getVenueId());
                    if (centre.share().block() != null && venue.share().block() != null) {
                        return ResponseEntity.status(HttpStatus.OK).body(CentreVenueResponse.from(centre, venue, centreVenue));
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }
        }

    }

    public ResponseEntity<Mono> delete(@RequestParam Integer id) {
        Mono<CentreVenue> centreVenue = repository.findById(id);
        if(centreVenue.share().block()!=null){
            return ResponseEntity.status(HttpStatus.OK).body(repository.deleteById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public Flux<CentreVenue> all() {
        return repository.findAll().switchIfEmpty(Flux.<CentreVenue>empty());
    }

    public Flux<CentreVenue> findByCentreId(Integer centreId) {return repository.findByCentreId(centreId); }

    public Flux<CentreVenue> findByVenueId(Integer venueId) {return repository.findByVenueId(venueId); }

    public Mono<CentreVenue> findByVenueAndCentre(Integer centreId,Integer venueId){
        return repository.findByCentreAndVenue(centreId,venueId);
    }

}
