package com.example.simpleboard.crud;

import com.example.simpleboard.board.common.Api;
import com.example.simpleboard.board.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
*  dto -> entity -> dto
* */
public abstract class CRUDAbstractService<DTO,ENTITY> implements CRUDInterface<DTO> {

    @Autowired(required = false)
    private JpaRepository<ENTITY, Long> jpaRepository;

    @Autowired(required = false)
    private Converter<DTO,ENTITY> converter;

    @Override
    public DTO create(DTO dto) {

        //dto -> entity
        ENTITY entity = converter.toENTITY(dto);
        //entity -> save
        jpaRepository.save(entity);
        //save -> dto
        DTO returnDto = converter.toDTO(entity);

        return returnDto;
    }

    @Override
    public Optional<DTO> read(Long id) {

        Optional<ENTITY> optionalEntity = jpaRepository.findById(id);

        DTO dto = optionalEntity.map(
                it -> {
                    return converter.toDTO(it);
                }
        ).orElseGet(() -> null);

        return optionalEntity.ofNullable(dto);
    }

    @Override
    public DTO update(DTO dto) {
        ENTITY entity = converter.toENTITY(dto);
        jpaRepository.save(entity);
        DTO returnDto = converter.toDTO(entity);

        return returnDto;
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) {
        Page<ENTITY> list = jpaRepository.findAll(pageable);

        Pagination pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        List<DTO> dtoList = list.stream().
                map(it -> {
                            return converter.toDTO(it);
                        }
                ).collect(Collectors.toList());

        Api<List<DTO>> response = Api.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();

        return response;
    }
}
