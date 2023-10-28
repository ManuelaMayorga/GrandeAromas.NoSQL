package com.uao.GrandeAromas.Controller;

import com.uao.GrandeAromas.Exceptions.RecursoNoEncontradoException;
import com.uao.GrandeAromas.Model.AffiliationModel;
import com.uao.GrandeAromas.Model.MembershipModel;
import com.uao.GrandeAromas.Model.UsuariosModel;
import com.uao.GrandeAromas.Service.IAffiliationService;
import com.uao.GrandeAromas.Service.IMembershipService;
import com.uao.GrandeAromas.Service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GrandeAromas/affiliations")
public class AffiliationController {
    @Autowired
    IAffiliationService affiliationService;

    @Autowired
    IMembershipService membershipService;

    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/guardarAffiliation")
    public ResponseEntity<String> guardarAffiliation(@RequestBody AffiliationModel affiliation) {
        Date fechaActual = new Date();
        affiliation.setStart_date(fechaActual);

        Optional<UsuariosModel> userId = this.usuarioService.obtenerUsuariosPorId(affiliation.getUserId());
        if(!userId.isPresent()){
            return new ResponseEntity<String>("El usuario que intentas afiliar no existe", HttpStatus.NOT_FOUND);
        }
        Optional<MembershipModel> membershipId = this.membershipService.obtenerMembershipPorId(affiliation.getMembershipId());
        if(!membershipId.isPresent()){
            return new ResponseEntity<String>("La membresía que intentas afiliar no existe", HttpStatus.NOT_FOUND);
        }
        // Calcular la fecha de fin, 1 mes después
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.add(Calendar.MONTH, 1);
        Date fechaFinal = calendar.getTime();
        affiliation.setFinish_date(fechaFinal);
 
        UsuariosModel usuario = usuarioService.encontrarIdyEmail(affiliation.getUserId());
        affiliation.setEmail(usuario.getEmail());
        affiliationService.guardarAffiliation(affiliation);
        return new ResponseEntity<>("Affiliation guardada", HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{affiliationId}")
    public ResponseEntity<String> eliminarAffiliationPorId(@PathVariable int affiliationId) {
        Optional<AffiliationModel> optionalAffiliation = affiliationService.obtenerAffiliationPorId(affiliationId);

        if (!optionalAffiliation.isPresent()) {
            return new ResponseEntity<>("Affiliation no encontrada", HttpStatus.NOT_FOUND);
        }

        affiliationService.eliminarAfiliacionPorId(affiliationId);
        return new ResponseEntity<>("Affiliation con ID " + affiliationId + " eliminada", HttpStatus.OK);
    }

   @PutMapping("/actualizar/{affiliationId}")
    public ResponseEntity<String> actualizarAffiliationPorId(@PathVariable int affiliationId, @RequestBody AffiliationModel affiliationDetail) {
    AffiliationModel affiliation = affiliationService.obtenerAffiliationPorId(affiliationId).orElseThrow(() -> new RecursoNoEncontradoException("Afiliacion no encontrada con el id: " + affiliationId));

        affiliation.setUserId(affiliationDetail.getUserId());
        affiliation.setMembershipId(affiliationDetail.getMembershipId());
        affiliation.setEmail(affiliationDetail.getEmail());
        // AffiliationModel existingAffiliation = optionalAffiliation.get();

        if (affiliationDetail.getUserId() != 0 && affiliationDetail.getMembershipId() != 0 && !affiliationDetail.getEmail().isEmpty()) {
            return new ResponseEntity<>("Affiliation con ID " + affiliationId + " actualizada", HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("No se ha actualizado la Affiliation con ID " + affiliationId + ", faltan campos por llenar o son invalidos", HttpStatus.BAD_REQUEST);
        }
        // // Continúa con los demás campos de actualización

        // affiliationService.actualizarAfiliacionPorId(affiliationId);
        // return new ResponseEntity<>("Affiliation con ID " + affiliationId + " actualizada", HttpStatus.OK);
    }

    @GetMapping("/obtenerAffiliation/{affiliationId}")
    public ResponseEntity<?> obtenerAffiliationPorId(@PathVariable int affiliationId) {
        Optional<AffiliationModel> affiliation = affiliationService.obtenerAffiliationPorId(affiliationId);

        if (affiliation.isPresent()) {
            return new ResponseEntity<>(affiliation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("La Affiliation con ID " + affiliationId + " no existe.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/obtenerAffiliations")
    public ResponseEntity<List<AffiliationModel>> obtenerAffiliations() {
        List<AffiliationModel> affiliations = affiliationService.obtenerAffiliation();
        return new ResponseEntity<>(affiliations, HttpStatus.OK);
    }
}
