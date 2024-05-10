package com.neo.farmlands.controller.api;


import com.neo.farmlands.domain.vo.MemberAddressForm;
import com.neo.farmlands.domain.vo.MemberAddressVO;
import com.neo.farmlands.service.impl.H5MemberAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/h5/member/address")
public class H5MemberAddressController {

  @Autowired
  private H5MemberAddressService h5MemberAddressService;

  @GetMapping("/list")
  public ResponseEntity<List<MemberAddressVO>> getList(){
    return ResponseEntity.ok(h5MemberAddressService.selectList());
  }

  @GetMapping("/default")
  public ResponseEntity<MemberAddressVO> getDefault(){
    return ResponseEntity.ok(h5MemberAddressService.getDefault());
  }

  @PostMapping("/create")
  public ResponseEntity<Integer> create(@RequestBody MemberAddressForm memberAddressForm){
    return ResponseEntity.ok(h5MemberAddressService.insert(memberAddressForm));
  }

  @PutMapping("/update")
  public ResponseEntity<Integer> update(@RequestBody MemberAddressForm memberAddressForm){
    return ResponseEntity.ok(h5MemberAddressService.update(memberAddressForm));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemberAddressVO> getInfo(@PathVariable Long id){
    return ResponseEntity.ok(h5MemberAddressService.selectById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Integer> remove(@PathVariable Long id) {
    return ResponseEntity.ok(h5MemberAddressService.deleteById(id));
  }
}
