package ezenweb.web.service;

import ezenweb.web.domain.File.FileDto;
import ezenweb.web.domain.product.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {       /* 주요기능과 DB처리 요청 역찰[ Transactional ] */

    @Autowired private ProductEntityRepository productEntityRepository;
    @Autowired private FileService fileService;

    @Autowired private ProductImgEntityRepository productImgEntityRepository;


    // 1.
    @Transactional      public List<ProductDto> get(){ log.info("get : ");
        // 1.모든 엔티티 호출
        List<ProductEntity> productEntityList = productEntityRepository.findAll();
        // 2. 모든 엔티티를 dto로 변환 [ 리스트명.stream().map( o -> 실행문 ).collect(Collectors.toList() ); ]
        List<ProductDto> productDtoList = productEntityList.stream().map( o -> o.toAdminDto() ).collect( Collectors.toList() );
        return productDtoList;
    }
    // 2.
    @Transactional      public boolean post(ProductDto productDto){ log.info("post : " + productDto);

        // 1. id 생성 [ 등록 오늘날짜 + 밀리초 + 난수]
        String number = ""; for( int i = 0 ; i<3; i++ ){ number+= new Random().nextInt(10); } // 737 , 831 , 001
        String pid = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddSSS")) + number;
        // 2. dto에 id 넣기
        productDto.setId( pid );
        // 3. db 저장
        ProductEntity productEntity =  productEntityRepository.save( productDto.toSaveEntity() );

        if(productDto.getPimgs().size() != 0){

            productDto.getPimgs().forEach((img)->{

               FileDto fileDto =  fileService.fileupload( img );


                ProductImgEntity productImgEntity =
                        productImgEntityRepository.save( ProductImgEntity.builder()
                        .originalFilename(fileDto.getOriginalFilename())
                        .uuidFile(fileDto.getUuidFile())
                        .build());

                productImgEntity.setProductEntity(productEntity);
                productEntity.getProductImgEntityList().add(productImgEntity);
            });
        }
        return true;

    }
    // 3.
    @Transactional      public boolean put(ProductDto productDto){ log.info("put : " + productDto);
        // 1. 업데이트 할 엔티티 찾기
        Optional<ProductEntity> entityOptional  = productEntityRepository.findById( productDto.getId() );
        // 2. 해당 엔티티가 존재하면 각 필드별 set 이용한 수정 처리
        if( entityOptional.isPresent() ){
            ProductEntity entity = entityOptional.get();
            entity.setPcategory( productDto.getPcategory() );
            entity.setPcommnet( productDto.getPcommnet() );
            entity.setPname( productDto.getPname());
            entity.setPprice( productDto.getPprice() );
            entity.setPmanufacturer( productDto.getPmanufacturer() );
            entity.setPstock( productDto.getPstock() );
            entity.setPstate( productDto.getPstate() );
            return true;
        }
        return false;
    }
    // 4.
    @Transactional      public boolean delete( String id ){ log.info("delete : " + id);
        // 1. 삭제 할 엔티티 찾기
        Optional<ProductEntity> entityOptional  = productEntityRepository.findById( id );
        // 2. 해당 엔티티가 존재하면
        entityOptional.ifPresent( o -> {
            o.getProductImgEntityList().forEach((img)->{
               File file = new File(fileService.path+img.getUuidFile());
               if( file.exists() ){file.delete();}
            });

            productEntityRepository.delete( o ); } );
        /* vs
            if( entityOptional.isPresent() ){ productEntityRepository.delete( entityOptional.get() ); } );
         */



        return true;
    }
}

/*
    // 1.
    // js : 리스트명.forEach( o => 실행문 );      리스트명.map( o => 실행문 );
    // java : 리스트명.forEach( o -> 실행문 );    리스트명.stream().map( o -> 실행문 ).collect(Collectors.toList() );


    // 2.
    {
     "pname" : "사이다 제로" ,
     "pprice" : 3000 ,
     "pcategory" : "음료",
     "pcommnet" : "맛있는 탄산음료",
     "pmanufacturer": "롯데" ,
     "pstate" : 0 ,
     "pstock" : 100
    }




 */