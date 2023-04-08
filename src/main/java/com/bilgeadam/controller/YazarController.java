package com.bilgeadam.controller;

import com.bilgeadam.dto.request.SaveYazarRequestDto;
import com.bilgeadam.repository.entity.Yazar;
import com.bilgeadam.service.YazarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.bilgeadam.constants.EndPointList.*;

@RestController
@RequestMapping(YAZAR)
@RequiredArgsConstructor
public class YazarController {
    private final YazarService yazarService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody @Valid SaveYazarRequestDto dto){
        yazarService.save(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(FIND_ALL)
    public ResponseEntity<List<Yazar>> findAll(){
        return ResponseEntity.ok(yazarService.findAll());
    }
}
