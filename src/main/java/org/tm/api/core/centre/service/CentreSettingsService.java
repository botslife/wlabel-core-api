package org.tm.api.core.centre.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.tm.api.core.centre.entity.Centre;
import org.tm.api.core.centre.entity.CentreSettings;
import org.tm.api.core.centre.mappers.CentreSettingsCreateRequest;
import org.tm.api.core.centre.mappers.CentreSettingsResponse;
import org.tm.api.core.centre.mappers.CentreSettingsUpdateRequest;
import org.tm.api.core.centre.repository.CentreRepository;
import org.tm.api.core.centre.repository.CentreSettingsRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CentreSettingsService {

    private CentreSettingsRepository repository;
    private CentreRepository centreRepository;

    public CentreSettingsService(CentreRepository centreRepository, CentreSettingsRepository repository){
        this.repository = repository;
        this.centreRepository = centreRepository;
    }

    public ResponseEntity<Mono<CentreSettingsResponse>> create(@RequestBody CentreSettingsCreateRequest request){
        Mono<Centre> centreMono = centreRepository.findById(request.getCentreId());
        if(centreMono.share().block()!=null){
            Mono<CentreSettings> centreSettingsMonoCheck = repository.findByCentreId(request.getCentreId());
            if(centreSettingsMonoCheck.share().block()==null){
                CentreSettings centreSettingsMono = CentreSettingsCreateRequest.from(request);
                return ResponseEntity.status(HttpStatus.CREATED).body(CentreSettingsResponse.from(centreMono,repository.save(centreSettingsMono)));
            }else{
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<Mono<CentreSettingsResponse>> findById(@RequestParam Integer id) {
        Mono<CentreSettings> centreSettingsMono = repository.findById(id);
        if(centreSettingsMono.share().block()!=null){
            Mono<Centre> centreMono = centreRepository.findById(centreSettingsMono.share().block().getCentreId());
            return ResponseEntity.ok(CentreSettingsResponse.from(centreMono,centreSettingsMono));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Mono<CentreSettingsResponse>> update(@RequestBody CentreSettingsUpdateRequest request){
        if(request.getId()==null&&request.getCentreId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            Mono<CentreSettings> centreSettingsCheck = repository.findById(request.getId());
            if(centreSettingsCheck.share().block()!=null){
                Mono<Centre> centreMono = centreRepository.findById(request.getCentreId());
                if(centreMono.share().block()!=null){
                    try{
                        CentreSettings centreSettings = CentreSettingsUpdateRequest.from(request);
                        return ResponseEntity.status(HttpStatus.CREATED).body(CentreSettingsResponse.from(centreMono,repository.save(centreSettings)));
                    }catch(DataIntegrityViolationException uniqueViolation){
                        return ResponseEntity.status(HttpStatus.CONFLICT).build();
                    }
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

    public ResponseEntity<Mono> delete(@RequestParam Integer id) {
        Mono<CentreSettings> centreSettingsMono = repository.findById(id);
        if(centreSettingsMono.share().block()!=null){
            return ResponseEntity.status(HttpStatus.OK).body(repository.deleteById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



    public Flux<CentreSettings> all() {
        return repository.findAll().switchIfEmpty(Flux.<CentreSettings>empty());
    }

    public ResponseEntity<Mono<CentreSettingsResponse>> findByCentreId(Integer centreId) {
        Mono<CentreSettings> centreSettingsMono = repository.findByCentreId(centreId);
        if(centreSettingsMono.share().block()!=null){
            Mono<Centre> centreMono = centreRepository.findById(centreSettingsMono.share().block().getCentreId());
            return ResponseEntity.status(HttpStatus.OK).body(CentreSettingsResponse.from(centreMono,centreSettingsMono));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
