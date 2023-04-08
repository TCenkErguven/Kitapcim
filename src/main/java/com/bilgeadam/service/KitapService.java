package com.bilgeadam.service;

import com.bilgeadam.dto.request.SaveKitapRequestDto;
import com.bilgeadam.dto.response.FindByYazarAdiResponseDto;
import com.bilgeadam.mapper.IKitapMapper;
import com.bilgeadam.repository.IKitapRepository;
import com.bilgeadam.repository.entity.Kitap;
import com.bilgeadam.repository.entity.Yazar;
import com.bilgeadam.repository.view.VwYazarKitap;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitapService extends ServiceManager<Kitap,Long> {
    static List<Yazar> yazarList;
    private final IKitapRepository repository;
    private final YazarKitapService yazarKitapService;
    private final TurKitapService turKitapService;
    private final YazarService yazarService;
    private final TurService turService;
    public KitapService(IKitapRepository repository,TurService turService,YazarService yazarService, YazarKitapService yazarKitapService, TurKitapService turKitapService) {
        super(repository);
        this.repository = repository;
        this.yazarKitapService=yazarKitapService;
        this.turKitapService=turKitapService;
        this.yazarService=yazarService;
        this.turService=turService;
    }
    public Kitap save(SaveKitapRequestDto dto){
        //repository.save(IK...) gibi de olurdu ServiceManager sayesinde
        Kitap kitap = save(IKitapMapper.INSTANCE.toKitap(dto));
        /**
         * 1- Kitap hangi yazar yada yazarlara ait?
         */
        /**
         * List<Long> yazarlist = [1,4,2]
         * yazarList.get(0)=1
         * yazarList.get(1)=4
         * yazarList.get(2)=2
         */
        //Long yazarid = dto.getYazarids().get(0);
        /**
         * döngüyle listeyi tek tek kaydettik
         */
        dto.getYazarids().forEach(yazarid -> {
            yazarKitapService.save(yazarid,kitap.getId());
        });
        /**
         * 1- Kitap hangi tür yada türlere ait olduğunu belirtiyoruz
         */
        Long turid = dto.getTurids().get(0);
        turKitapService.save(turid, kitap.getId());
        return kitap;
    }
    public List<String> kitapAdiToYazarList(String kitapName){
       /* Kitap kitap = repository.findByAd(kitapName);
        List<YazarKitap> yazarKitaps = yazarKitapService.findYazarListByKitapId(kitap.getId());
        yazarKitaps.forEach(ids ->{
            yazarList.add(yazarService.findAllByYazarId(ids.getYazarid()));
        });*/
        List<String> yazarAdList = repository.findByAd(kitapName);
        if (yazarAdList.isEmpty()){
            throw new NotFoundException("aradığınız isim de kitap bulunamadı");
        }
        return yazarAdList;
    }
    public List<String> findKitapByTurId(String ad){
        List<String> kitapList = repository.findKitapByTurId(turService.findIdByName(ad));
        if (kitapList.isEmpty()){
            throw new NotFoundException("aradığınız isim de kitap bulunamadı");
        }
        return kitapList;
    }
    public List<VwYazarKitap> findByYazarkitap(String ad){
        List<VwYazarKitap> kitapList = repository.findByYazarkitap(ad);
        if (kitapList.isEmpty()){
            throw new NotFoundException("aradığınız isim de kitap bulunamadı");
        }
        return kitapList;
    }
    public List<FindByYazarAdiResponseDto> findByYazarAdi(String yazarAdi){
        List<FindByYazarAdiResponseDto> result = new ArrayList<>();
        /**
         * 1. Yazar adından arama yaparak eşleşen kayıtları listeliyoruz
         */
        List<Yazar> yazarList = yazarService.findAllByContaining(yazarAdi);
        yazarList.forEach(yazar -> {
            /**
             * 2. YazarKitapdan yazar id verip kitap idlerini çekicez
             */
            List<Long> kitapIds = yazarKitapService.getAllByYazarid(yazar.getId());
            /**
             * 3. Kitap id leri verilen liste kullanılarak kitap tablosundaki ilgili tüm kayıtlar listelenir
             * burada işlem yapılan kod. select * from kitap where id in (1,45,33,29); //şeklindedir.
             */
            List<Kitap> kitapList = repository.findAllById(kitapIds);
            /**
             * 4. yazar ve kitaplarının bilgileri DTO içine girilerek bir liste haline getirilmelidir.
             */
            FindByYazarAdiResponseDto dto = FindByYazarAdiResponseDto.builder()
                    .yazarAdi(yazar.getAd())
                    .kitapList(kitapList)
                    .build();
            result.add(dto);
        });
       return result;
    }

}
