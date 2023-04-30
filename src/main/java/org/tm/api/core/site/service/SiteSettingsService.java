package org.tm.api.core.site.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.tm.api.core.site.entity.Site;
import org.tm.api.core.site.entity.SiteSettings;
import org.tm.api.core.site.mappers.SiteSettingsCreateRequest;
import org.tm.api.core.site.mappers.SiteSettingsResponse;
import org.tm.api.core.site.mappers.SiteSettingsUpdateRequest;
import org.tm.api.core.site.repository.SiteRepository;
import org.tm.api.core.site.repository.SiteSettingsRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SiteSettingsService {

    private SiteSettingsRepository repository;

    private SiteRepository siteRepository;

    public SiteSettingsService(SiteSettingsRepository repository,SiteRepository siteRepository){
        this.repository = repository;
        this.siteRepository = siteRepository;
    }

    public ResponseEntity<Mono<SiteSettingsResponse>> save(SiteSettingsCreateRequest request){
        Mono<Site> siteMono = siteRepository.findById(request.getSiteId());
        if(siteMono.share().block()!=null){
            Mono<SiteSettings> siteSettings = repository.findBySiteId(request.getSiteId());
            if(siteSettings.share().block()==null){
                Mono<SiteSettings> savedSiteSettings = repository.save(SiteSettingsCreateRequest.from(request));
                return ResponseEntity.ok(SiteSettingsResponse.from(siteMono,savedSiteSettings));
            }else{
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Mono<SiteSettingsResponse>> findById(Integer siteSettingsId){
        Mono<SiteSettings> siteSettings = repository.findById(siteSettingsId);
        if(siteSettings.share().block()!=null){
            Mono<Site> siteMono = siteRepository.findById(siteSettings.share().block().getSiteId());
            return ResponseEntity.ok(SiteSettingsResponse.from(siteMono,siteSettings));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<SiteSettingsResponse>> update(SiteSettingsUpdateRequest updateRequest){
        if(updateRequest.getId()==null && updateRequest.getSiteId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            Mono<SiteSettings> siteSettings = repository.findById(updateRequest.getId());
            Mono<Site> siteMono = siteRepository.findById(updateRequest.getSiteId());
            if (siteSettings.share().block() != null && siteMono.share().block()!=null) {
                SiteSettings sSettings = SiteSettingsUpdateRequest.from(updateRequest);
                return ResponseEntity.ok(SiteSettingsResponse.from(siteMono, repository.save(sSettings)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    public ResponseEntity<Mono> delete(Integer siteSettingsId){
        Mono<SiteSettings> siteSettings = repository.findById(siteSettingsId);
        if(siteSettings.share().block()!=null){
            return ResponseEntity.ok(repository.deleteById(siteSettingsId));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public Flux<SiteSettings> findAll(){
        return repository.findAll();
    }

    public ResponseEntity<Mono<SiteSettingsResponse>> findBySiteId(Integer siteId){
        Mono<Site> siteMono = siteRepository.findById(siteId);
        if(siteMono.share().block()!=null){
            Mono<SiteSettings> siteSettings = repository.findBySiteId(siteId);
            if(siteSettings.share().block()!=null){
                return ResponseEntity.ok(SiteSettingsResponse.from(siteMono,siteSettings));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
