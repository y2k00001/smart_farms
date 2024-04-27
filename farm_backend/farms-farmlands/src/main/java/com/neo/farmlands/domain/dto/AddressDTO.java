package com.neo.farmlands.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 【请填写功能名称】 DTO 对象
 *
 * @author sjm
 */
@Data
public class AddressDTO {
    private Long id;
    private Long pid;
    private String name;
    private String level;
    private List<AddressDTO> children;
}
