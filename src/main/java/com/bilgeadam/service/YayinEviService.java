package com.bilgeadam.service;

import com.bilgeadam.dto.request.SaveYayinEviRequestDto;
import com.bilgeadam.mapper.IYayinEviMapper;
import com.bilgeadam.repository.IYayinEviRepository;
import com.bilgeadam.repository.entity.YayinEvi;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class YayinEviService extends ServiceManager<YayinEvi,Long> {
    private final IYayinEviRepository repository;
    public YayinEviService(IYayinEviRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public void save(SaveYayinEviRequestDto dto){
        repository.save(IYayinEviMapper.INSTANCE.toYayinEvi(dto));
    }
}
