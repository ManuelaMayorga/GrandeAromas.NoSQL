package com.uao.GrandeAromas.Controller;

import com.uao.GrandeAromas.Model.MembershipModel;
import com.uao.GrandeAromas.Service.IMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GrandeAromas/memberships")
public class MembershipController {
    @Autowired
    private IMembershipService membershipService;

    @PostMapping("/guardarMembership")
    public ResponseEntity<String> guardarMembership(@RequestBody MembershipModel membership) {
        membershipService.guardarMembership(membership);
        return new ResponseEntity<>("Membership guardada", HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{membershipId}")
    public ResponseEntity<String> eliminarMembershipPorId(@PathVariable int membershipId) {
        String message = membershipService.eliminarMembershipPorId(membershipId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{membershipId}")
    public ResponseEntity<String> actualizarMembershipPorId(@PathVariable int membershipId, @RequestBody MembershipModel membership) {
        String message = membershipService.actualizarMembershipPorId(membershipId, membership);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/obtenerMembership/{membershipId}")
    public ResponseEntity<?> obtenerMembershipPorId(@PathVariable int membershipId) {
        Optional<MembershipModel> membership = membershipService.obtenerMembershipPorId(membershipId);

        if (membership.isPresent()) {
            return new ResponseEntity<>(membership, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Membership con ID " + membershipId + " no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/obtenerMemberships")
    public ResponseEntity<List<MembershipModel>> obtenerMemberships() {
        List<MembershipModel> memberships = membershipService.obtenerMemberships();
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }
}

