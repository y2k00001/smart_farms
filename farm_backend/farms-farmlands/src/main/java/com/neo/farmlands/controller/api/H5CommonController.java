package com.neo.farmlands.controller.api;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.neo.common.annotation.Log;
import com.neo.common.core.domain.AjaxResult;
import com.neo.common.core.domain.R;
import com.neo.common.core.redis.RedisService;
import com.neo.common.enums.BusinessType;
import com.neo.common.utils.OssUtils;
import com.neo.farmlands.domain.dto.AddressDTO;
import com.neo.farmlands.domain.entity.Address;
import com.neo.farmlands.domain.entity.Feedback;
import com.neo.farmlands.mapper.AddressMapper;
import com.neo.farmlands.service.impl.FeedbackService;
import com.neo.system.service.ISysConfigService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class H5CommonController {

  @Autowired
  private OssUtils ossUtils;
  @Resource
  private AddressMapper addressMapper;
  @Autowired
  private RedisService redisService;
  @Autowired
  private FeedbackService feedbackService;
  @Autowired
  private ISysConfigService sysConfigService;

  @ApiOperation("新增意见反馈")
  @Log(title = "意见反馈", businessType = BusinessType.INSERT)
  @PostMapping("/h5/feedback/create")
  public ResponseEntity<Integer> add(@RequestBody Feedback feedback) {
    return ResponseEntity.ok(feedbackService.insert(feedback));
  }


  @GetMapping("/h5/area")
  public AjaxResult getAddressList() {
    String addresses = redisService.getAddressList();
    if (StringUtils.isNotEmpty(addresses)) {
      return AjaxResult.success(JSON.parseArray(addresses, AddressDTO.class));
    }
    QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
    addressQueryWrapper.in("level", Arrays.asList(0,1,2));
    List<Address> addressList = addressMapper.selectList(addressQueryWrapper);
    Map<Long, List<Address>> cityMap = addressList.stream().filter(it -> it.getLevel() == 1).collect(Collectors.groupingBy(it -> it.getParentCode()));
    Map<Long, List<Address>> districtMap = addressList.stream().filter(it -> it.getLevel() == 2).collect(Collectors.groupingBy(it -> it.getParentCode()));
    List<AddressDTO> result = new ArrayList<>();
    addressList.stream().filter(it -> it.getLevel() == 0).forEach(it -> {
      AddressDTO dto = new AddressDTO();
      dto.setId(it.getCode());
      dto.setLevel("province");
      dto.setName(it.getName());
      dto.setPid(0L);
      //获取城市列表
      List<AddressDTO> child = new ArrayList<>();
      if (cityMap.containsKey(it.getCode())) {
        cityMap.get(it.getCode()).forEach(city -> {
          AddressDTO cityDto = new AddressDTO();
          cityDto.setId(city.getCode());
          cityDto.setLevel("city");
          cityDto.setName(city.getName());
          cityDto.setPid(city.getParentCode());
          cityDto.setChildren(districtMap.containsKey(city.getCode()) ?
            districtMap.get(city.getCode()).stream().map(district -> {
              AddressDTO districtDto = new AddressDTO();
              districtDto.setId(district.getCode());
              districtDto.setLevel("district");
              districtDto.setName(district.getName());
              districtDto.setPid(district.getParentCode());
              return districtDto;
            }).collect(Collectors.toList()) : Collections.EMPTY_LIST);
          child.add(cityDto);
        });
      }
      dto.setChildren(child);
      result.add(dto);
    });
    redisService.setAddressList(JSON.toJSONString(result));
    return AjaxResult.success(result);
  }

  @PostMapping("/h5/file/upload")
  public R<String> uploadFile(MultipartFile file) {
    String url = ossUtils.uploadOneFile(file);
    return R.ok(url);
  }

  @GetMapping("/no-auth/config/get")
  public R<String> getSysConfig(String configKey) {
    String s = sysConfigService.selectConfigByKey(configKey);
    return R.ok(s);
  }

}
