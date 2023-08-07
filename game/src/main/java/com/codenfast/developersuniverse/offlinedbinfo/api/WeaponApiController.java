package com.codenfast.developersuniverse.offlinedbinfo.api;

import com.codenfast.developersuniverse.entitydto.game.weapon.WeaponDto;
import com.codenfast.developersuniverse.model.CodenfastSecurityException;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.offlinedbinfo.controller.WeaponController;
import com.codenfast.developersuniverse.service.EncryptService;
import com.codenfast.developersuniverse.utils.StringConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/weapon", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
@RestController
@lombok.RequiredArgsConstructor
@Slf4j
public class WeaponApiController {

    private final WeaponController weaponController;
    private final EncryptService encryptService;

    @PostMapping("save")
    public String save(@RequestHeader(StringConstant.AHEADER) String data, @RequestBody String encryptData) throws JsonProcessingException, CodenfastSecurityException {
        return encryptService.writeValue(data, weaponController.save(encryptService.readValue(data, encryptData, WeaponDto.class)));
    }

    @PutMapping("update")
    public String update(@RequestHeader(StringConstant.AHEADER) String data, @RequestBody String encryptData) throws JsonProcessingException, CodenfastSecurityException {
        return encryptService.writeValue(data, weaponController.update(encryptService.readValue(data, encryptData, WeaponDto.class)));
    }

    @DeleteMapping("delete/{id}")
    public String delete(@RequestHeader(StringConstant.AHEADER) String data, @PathVariable String id) throws JsonProcessingException, CodenfastSecurityException {
        return encryptService.writeValue(data, weaponController.delete(id));
    }

    @PostMapping("grid")
    public String grid(HttpServletResponse response, @RequestHeader(StringConstant.AHEADER) String data, @RequestBody String encryptData) throws JsonProcessingException, CodenfastSecurityException {
        response.addHeader(StringConstant.AHEADER, data);
        return encryptService.writeValue(data, weaponController.grid(encryptService.readValue(data, encryptData, RequestGrid.class)));
    }

    @PostMapping("/grid-table-model")
    public String gridTableModel(@RequestHeader(StringConstant.AHEADER) String data, @RequestBody String encryptData) throws JsonProcessingException, CodenfastSecurityException {
        return encryptService.writeValue(data, weaponController.gridTableModel(encryptService.readValue(data, encryptData, RequestGrid.class)));
    }
}
