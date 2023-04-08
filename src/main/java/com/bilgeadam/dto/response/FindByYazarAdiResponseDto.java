package com.bilgeadam.dto.response;

import com.bilgeadam.repository.entity.Kitap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindByYazarAdiResponseDto {
    String yazarAdi;
    List<Kitap> kitapList;
}
