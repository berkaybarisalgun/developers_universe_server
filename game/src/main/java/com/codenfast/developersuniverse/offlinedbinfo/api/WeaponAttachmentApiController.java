package com.codenfast.developersuniverse.offlinedbinfo.api;

import com.codenfast.developersuniverse.entitydto.game.weapon.WeaponAttachmentDto;
import com.codenfast.developersuniverse.entitydto.game.weapon.WeaponDto;
import com.codenfast.developersuniverse.model.CodenfastSecurityException;
import com.codenfast.developersuniverse.model.RequestGrid;
import com.codenfast.developersuniverse.offlinedbinfo.controller.WeaponAttachmentController;
import com.codenfast.developersuniverse.offlinedbinfo.controller.WeaponController;
import com.codenfast.developersuniverse.service.EncryptService;
import com.codenfast.developersuniverse.utils.StringConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/weapon-attachment", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
@RestController
@lombok.RequiredArgsConstructor
@Slf4j
public class WeaponAttachmentApiController {

    private final WeaponAttachmentController weaponAttachmentController;
    private final EncryptService encryptService;

    @PostMapping("save")
    public String save(@RequestHeader(StringConstant.AHEADER) String data, @RequestBody String encryptData) throws JsonProcessingException, CodenfastSecurityException {
        return encryptService.writeValue(data, weaponAttachmentController.save(encryptService.readValue(data, encryptData, WeaponAttachmentDto.class)));
    }

    @PutMapping("update")
    public String update(@RequestHeader(StringConstant.AHEADER) String data, @RequestBody String encryptData) throws JsonProcessingException, CodenfastSecurityException {
        return encryptService.writeValue(data, weaponAttachmentController.update(encryptService.readValue(data, encryptData, WeaponAttachmentDto.class)));
    }

    @DeleteMapping("delete/{id}")
    public String delete(@RequestHeader(StringConstant.AHEADER) String data, @PathVariable String id) throws JsonProcessingException, CodenfastSecurityException {
        return encryptService.writeValue(data, weaponAttachmentController.delete(id));
    }

    @PostMapping("grid")
    public String grid(HttpServletResponse response, @RequestHeader(StringConstant.AHEADER) String data, @RequestBody String encryptData) throws JsonProcessingException, CodenfastSecurityException {
        response.addHeader(StringConstant.AHEADER, data);
        return encryptService.writeValue(data, weaponAttachmentController.grid(encryptService.readValue(data, encryptData, RequestGrid.class)));
    }

    @PostMapping("/grid-table-model")
    public String gridTableModel(@RequestHeader(StringConstant.AHEADER) String data, @RequestBody String encryptData) throws JsonProcessingException, CodenfastSecurityException {
        return encryptService.writeValue(data, weaponAttachmentController.gridTableModel(encryptService.readValue(data, encryptData, RequestGrid.class)));
    }
}
