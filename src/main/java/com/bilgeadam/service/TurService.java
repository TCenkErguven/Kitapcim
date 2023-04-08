package com.bilgeadam.service;

import com.bilgeadam.repository.ITurRepository;
import com.bilgeadam.repository.entity.Tur;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurService extends ServiceManager<Tur,Long> {
    private final ITurRepository repository;
    private TurService(ITurRepository repository){
        super(repository);
        this.repository=repository;
    }

    /**
     * tur adı benzersiz olmalı bu uygulamada
     * roman, Roman, ROMAN
     * @param turAdi
     */
    public void save(String turAdi){
        Optional<Tur> tur = repository.findOptionalByAdIgnoreCase(turAdi);
        if (tur.isEmpty()) {
            save(Tur.builder().ad(turAdi).build()); // serviceManagerdan geldi
        }
    }
    public Long findIdByName(String ad){
        Optional<Tur> tur = repository.findOptionalByAdIgnoreCase(ad);
        return tur.get().getId();
    }
}
