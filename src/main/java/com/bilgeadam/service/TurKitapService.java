package com.bilgeadam.service;


import com.bilgeadam.repository.ITurKitapRepository;
import com.bilgeadam.repository.entity.TurKitap;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class TurKitapService extends ServiceManager<TurKitap,Long> {
    private ITurKitapRepository repository;
    public TurKitapService(ITurKitapRepository repository){
        super(repository);
        this.repository=repository;
    }
    public void save(Long turId, Long kitapId){
        save(TurKitap.builder()
                .turid(turId).kitapid(kitapId).build());
    }
}
